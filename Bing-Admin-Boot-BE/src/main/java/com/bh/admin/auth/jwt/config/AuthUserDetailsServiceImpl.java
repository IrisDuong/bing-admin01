package com.bh.admin.auth.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bh.admin.account.dao.AccountDao;
import com.bh.admin.account.entity.Account;

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AccountDao accountDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = accountDao.findByUserName(username)
				.orElseThrow(()-> new UsernameNotFoundException(String.format("User not found with username is %s", username)));
		UserDetailsCoreImpl userDetailsCoreImpl = UserDetailsCoreImpl.build(account);
		return userDetailsCoreImpl;
	}

}
