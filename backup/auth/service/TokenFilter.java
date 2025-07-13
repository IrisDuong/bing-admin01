package com.bh.admin.auth.service;

import java.io.IOException;

import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bh.admin.util.RequestUtils;
import com.bh.admin.util.exception.InternalErrorException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class TokenFilter extends OncePerRequestFilter{

	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
			String token = RequestUtils.getTokenFromRequest(request);
			try {
				if(token != null && tokenProvider.validateToken(token)) {
					String userName = tokenProvider.getUserNameFromToken(token);
					UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
					
					UsernamePasswordAuthenticationToken authentication
						= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new InternalErrorException(e.getMessage());
			}
		filterChain.doFilter(request, response);
	}

}
