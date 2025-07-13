package com.bh.admin.util;

import java.time.LocalDateTime;
import java.util.Set;

import com.bh.admin.account.dto.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
	private String message;
	private LocalDateTime date;
	private T body;
	
	public ApiResponse(String message) {
		super();
		this.message = message;
		this.date = LocalDateTime.now();
	}

	public ApiResponse(String message, T body) {
		this(message);
		this.body = body;
	}
	
	
}
