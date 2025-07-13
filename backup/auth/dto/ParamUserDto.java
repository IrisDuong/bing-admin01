package com.bh.admin.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParamUserDto {

	@NotNull
	private String userName;
	
	@NotNull
	@Email
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNo;
}
