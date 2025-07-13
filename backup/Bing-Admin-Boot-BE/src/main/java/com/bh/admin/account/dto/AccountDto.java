package com.bh.admin.account.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
private String id;
	
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phoneNo;
	private String address;
	private Set<String> roles;
	public AccountDto(String userName, String password,String fullName, String email, String phoneNo, String address) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	public AccountDto(String userName, String password,String fullName, String email, String phoneNo, String address, Set<String> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.roles = roles;
	}
	
	
}
