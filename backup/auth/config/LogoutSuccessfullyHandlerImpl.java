package com.bh.admin.auth.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bh.admin.auth.service.RefreshTokenService;
import com.bh.admin.auth.service.TokenProvider;
import com.bh.admin.auth.service.UserPrincipal;
import com.bh.admin.util.RequestUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutSuccessfullyHandlerImpl implements LogoutSuccessHandler{

	Logger logger = LoggerFactory.getLogger(LogoutSuccessfullyHandlerImpl.class);
//	
//	private ThreadLocal<String> userNameHolder = new ThreadLocal<>();
//	
//	public void setUserNameBeforeLogout(String userName) {
//		this.userNameHolder.set(userName);
//	}
	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	TokenProvider tokenProvider;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
//		logger.info(">>>> userNameHolder=  "+userNameHolder);

		String token = RequestUtils.getTokenFromRequest(request);
		if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
			String userName = tokenProvider.getUserNameFromToken(token);

			refreshTokenService.deleteByUserName(userName);
		}
		logger.info(">>>> Thuc hien logout from front-end");
	}
	


}
