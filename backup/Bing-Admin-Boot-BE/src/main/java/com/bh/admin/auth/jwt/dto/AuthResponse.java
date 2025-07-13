package com.bh.admin.auth.jwt.dto;

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
public class AuthResponse {
	private String accessToken;
	private String type = "Bearer";
	private String refreshToken;
	private String email;
	private String userName;
	private String fullName;
}
