package com.bh.admin.auth.jwt.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.admin.account.dto.AccountDto;
import com.bh.admin.account.service.AccountService;
import com.bh.admin.auth.jwt.config.JwtTokenProvider;
import com.bh.admin.auth.jwt.config.UserDetailsCoreImpl;
import com.bh.admin.auth.jwt.dto.AuthResponse;
import com.bh.admin.auth.jwt.dto.SigninDto;
import com.bh.admin.util.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/createAccount/{adminFlag}")
	public ResponseEntity<ApiResponse> createAdminAccount(@RequestBody AccountDto dto, @PathVariable Optional<Integer> adminFlag){
		try {
			if(adminFlag.isPresent()&& adminFlag.get() == 10) {
				dto.setRoles(Collections.singleton("ROLE_ADMIN"));
				dto.setPassword(passwordEncoder.encode(dto.getPassword()));
				accountService.createAccount(dto);
				return new ResponseEntity<ApiResponse>(new ApiResponse("Create Admin account successfully!"), HttpStatus.CREATED);
			}
			return new ResponseEntity<ApiResponse>(new ApiResponse("You are unauthorized!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse>(new ApiResponse("Create Admin account failes!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> signin(@RequestBody SigninDto dto){
		try {
				UsernamePasswordAuthenticationToken signInParam = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
				Authentication authentication = authenticationManager.authenticate(signInParam);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				String accessToken = jwtTokenProvider.genTokenFromAuthen(authentication);
				UserDetailsCoreImpl userDetailsCoreImpl = (UserDetailsCoreImpl) authentication.getPrincipal();
				AuthResponse authResponse = AuthResponse.builder()
						.accessToken(accessToken)
						.refreshToken("")
						.email(userDetailsCoreImpl.getEmail())
						.userName(userDetailsCoreImpl.getUsername())
						.fullName(userDetailsCoreImpl.getFullName())
						.build();
				
				return new ResponseEntity<ApiResponse<AuthResponse>>(new ApiResponse<>("Login successfully!",authResponse), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse<AuthResponse>>(new ApiResponse<>("Login Admin account failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/test")
	public ResponseEntity<ApiResponse> test(){
		try {
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("GET :: Create Admin account successfully!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse>(new ApiResponse("Create Admin account failes!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getCurrentLoggedInfo")
	public ResponseEntity<ApiResponse> getCurrentLoggedInfo(){
		try {
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("GET :: Create Admin account successfully!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse>(new ApiResponse("Create Admin account failes!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
