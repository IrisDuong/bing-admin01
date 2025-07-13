package com.bh.admin.util;

import java.time.Instant;

import com.bh.admin.util.exception.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldDef {

	public static String resourceName;
	private String fieldName;
	private Object value;
	
	
}
