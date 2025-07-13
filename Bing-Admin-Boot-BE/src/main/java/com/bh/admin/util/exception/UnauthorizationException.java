package com.bh.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizationException extends AuthenticationException{

	public UnauthorizationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizationException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
