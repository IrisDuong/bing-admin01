package com.bh.admin.auth.jwt.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
@Slf4j
public class JwtTokenProvider {

	@Value("${app.auth.token.secretKey}")
	private String secret;

	@Value("${app.auth.token.expInMilSec}")
	private long exp;
	
	private Key keys() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public String genTokenFromUsername(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime()+exp))
				.signWith(keys())
				.compact();
	}
	
	public String genTokenFromAuthen(Authentication authentication) {
		UserDetailsCoreImpl userDetailsCoreImpl = (UserDetailsCoreImpl) authentication.getPrincipal();
		return genTokenFromUsername(userDetailsCoreImpl.getUsername());
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(keys()).build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(keys()).build().parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
            log.error("Error Log :::::::: Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Error Log :::::::: Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Error Log :::::::: Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Error Log :::::::: Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("Error Log :::::::: JWT claims string is empty.");
        }
		return false;
	}
}
