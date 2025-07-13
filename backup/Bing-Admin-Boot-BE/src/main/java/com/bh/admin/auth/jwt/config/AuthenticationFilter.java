package com.bh.admin.auth.jwt.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bh.admin.util.exception.InternalErrorException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter{

	private JwtTokenProvider tokenProvider;
	private AuthUserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String authHeaders = request.getHeader("Authorization");
			String authPrefix = "Bearer ";
			if(StringUtils.hasText(authHeaders) && authHeaders.startsWith(authPrefix)) {
				String token = authHeaders.substring(authPrefix.length(), authHeaders.length()).trim();
				if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
					String userName = tokenProvider.getUsernameFromToken(token);
					
					UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new InternalErrorException(e.getMessage());
		}
		filterChain.doFilter(request, response);
		
	}
	
	 
}
