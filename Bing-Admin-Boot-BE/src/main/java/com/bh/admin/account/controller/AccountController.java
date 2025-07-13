package com.bh.admin.account.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.admin.account.dto.AccountDto;
import com.bh.admin.account.service.AccountService;
import com.bh.admin.util.ApiResponse;

@RestController
@RequestMapping("/accountManagement")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createAccount")
	public ResponseEntity<ApiResponse> createAccount(@RequestBody AccountDto dto){
		try {
				accountService.createAccount(dto);
				return new ResponseEntity<ApiResponse>(new ApiResponse("Create Admin account successfully!"), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse>(new ApiResponse("Create Admin account failes!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
