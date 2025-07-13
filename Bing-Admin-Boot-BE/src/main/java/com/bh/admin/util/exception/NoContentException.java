package com.bh.admin.util.exception;

import java.util.StringJoiner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bh.admin.util.CustomStringUtils;
import com.bh.admin.util.FieldDef;

public class NoContentException extends RuntimeException{
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	public NoContentException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoContentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoContentException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format( "Can not find out %s with %s is %s", resourceName , fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

//	public NoContentException(FieldDef[] fields) {
//		super(fields != null ? String.format( "Can not find out %s with %s", fields[0].resourceName , CustomStringUtils.buildJoinnerField(fields)):"");
//		this.fields = fields;
//	}

	
}
