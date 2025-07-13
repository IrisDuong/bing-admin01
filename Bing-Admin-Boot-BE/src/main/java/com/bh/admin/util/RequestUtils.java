package com.bh.admin.util;

import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String getTokenFromRequest(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
			return authorization.substring(7);
		}
		return null;
	}
	
	
}
