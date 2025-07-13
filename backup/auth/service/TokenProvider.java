package com.bh.admin.auth.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider {

	private Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	
	@Value("${app.auth.token.secretKey}")
	private String secretKey;
	
	@Value("${app.auth.token.expInMilSec}")
	private long expiry;
	
	private SecretKey keys() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
	
	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		return generateTokenFromUserName(userPrincipal.getUsername());
	}
	
	public String generateTokenFromUserName(String userName) {
		Date now = new Date();
		return Jwts.builder().setSubject(userName)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime()+expiry))
				.signWith(keys())
				.compact();
	}

	public String getUserNameFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(keys()).build().parseClaimsJws(token).getBody();
		logger.info(">>>>>>>>>>> Jwt token expiry = "+claims.getExpiration().toString());
		return claims.getSubject();
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(keys()).build().parseClaimsJws(token).getBody();
		logger.info(">>>>>>>>>>> Jwt token expiry = "+claims.getExpiration().toString());
        return Long.parseLong(claims.getSubject());
    }
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(keys()).build().parseClaimsJws(token);
			return true;
		 } catch (SignatureException ex) {
             logger.error("Error Log :::::::: Invalid JWT signature");
         } catch (MalformedJwtException ex) {
             logger.error("Error Log :::::::: Invalid JWT token");
         } catch (ExpiredJwtException ex) {
             logger.error("Error Log :::::::: Expired JWT token");
         } catch (UnsupportedJwtException ex) {
             logger.error("Error Log :::::::: Unsupported JWT token");
         } catch (IllegalArgumentException ex) {
             logger.error("Error Log :::::::: JWT claims string is empty.");
         }
    	return false;
	}
}
