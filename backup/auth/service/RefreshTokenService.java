package com.bh.admin.auth.service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.admin.auth.dao.RefreshTokenDao;
import com.bh.admin.auth.dao.UserDao;
import com.bh.admin.auth.entity.RefreshToken;
import com.bh.admin.auth.entity.User;
import com.bh.admin.util.exception.InternalErrorException;

@Service
public class RefreshTokenService {

	@Value("${app.auth.token.expInMilSec}")
	private long tokenExpInMilSec;
	
	@Value("${app.auth.token.refreshInMilSec}")
	private long refreshInMilSec;
	
	@Autowired
	public RefreshTokenDao refreshTokenDao;
	
	@Autowired
	public UserDao userDao;
	
	public Optional<RefreshToken> findByToken(String token){
		return refreshTokenDao.findByToken(token);
	}
	
	public RefreshToken createRefreshToken(String userName) {
		User user = userDao.findById(userName)
				.orElseThrow(()-> new UsernameNotFoundException(String.format("Not Found User with user name", userName)));
		RefreshToken refreshToken = RefreshToken.builder()
				.user(user)
				.expiryDate(Instant.now().plusMillis(tokenExpInMilSec + refreshInMilSec))
				.token(UUID.randomUUID().toString())
				.build();
		refreshToken = refreshTokenDao.save(refreshToken);
		return refreshToken;
	}
	
	public RefreshToken verifyRefreshToken(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenDao.delete(token);
			throw new InternalErrorException(String.format("Failed for [%s]: %s",token, "Refresh token was expired. Please make a new signin request"));
		}
		return token;
	}
	
	@Transactional
	public int deleteByUserName(String userName) {
		return refreshTokenDao.deleteByUser(
					userDao.findById(userName)
						.orElseThrow(()-> new UsernameNotFoundException(String.format("Not Found User with user name", userName)))
				);
	}
}
