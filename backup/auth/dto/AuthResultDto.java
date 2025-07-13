package com.bh.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResultDto {
	private String accessToken;
	private String type;
	private String refreshToken;
	private String email;
	private String userName;
	private String fullName;
	
	public AuthResultDto(String accessToken, String refreshToken, String email, String userName,String fullName) {
		super();
		this.accessToken = accessToken;
		this.type = "Bearer";
		this.refreshToken = refreshToken;
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
	}
}
