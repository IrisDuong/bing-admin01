package com.bh.admin.util.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationException {

	@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalErrorException.class)
	public ResponseEntity<ErrorResponse> internalErrorException(Exception e){
		ErrorResponse error = ErrorResponse.builder()
				.message(e.getMessage())
				.errorDate(Instant.now())
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus( HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestException(Exception e){
		ErrorResponse error = ErrorResponse.builder()
				.message(e.getMessage())
				.errorDate(Instant.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.build();
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizationException.class)
	public ResponseEntity<ErrorResponse> unthorizationException(Exception e){
		ErrorResponse error = ErrorResponse.builder()
				.message(e.getMessage())
				.errorDate(Instant.now())
				.statusCode(HttpStatus.UNAUTHORIZED.value())
				.build();
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(NoContentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse noContentException(Exception e){
		ErrorResponse error = ErrorResponse.builder()
				.message(e.getMessage())
				.errorDate(Instant.now())
				.statusCode(HttpStatus.NOT_FOUND.value())
				.build();
		return error;
	}
}
