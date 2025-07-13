package com.bh.admin.auth.jwt.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bh.admin.account.entity.Account;
import com.bh.admin.account.entity.Role;

public class UserDetailsCoreImpl implements UserDetails{

	private String id;
	private String userName;
	private String password;
	private String email;
	private String phoneNo;
	private String address;
	private String fullName;
	private Collection<GrantedAuthority> authorities;
	
	public UserDetailsCoreImpl() {
		super();
	}
	public UserDetailsCoreImpl(String id, String userName, String password,String fullName, String email, String phoneNo,
			String address, Collection<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.authorities = authorities;
	}
	public static UserDetailsCoreImpl build(Account account) {
		List<GrantedAuthority> authorities
			= account.getRoles().stream()
			.map(role-> role.getName().name()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		UserDetailsCoreImpl core = new UserDetailsCoreImpl(account.getId(), account.getUserName(), account.getPassword(), account.getFullName(),account.getEmail(), account.getPhoneNo(), account.getAddress(), authorities);
		return core;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public String getFullName() {
		return fullName;
	}

}
