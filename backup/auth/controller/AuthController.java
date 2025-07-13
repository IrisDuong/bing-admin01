package com.bh.admin.auth.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.admin.auth.dto.AuthResultDto;
import com.bh.admin.auth.dto.ParamLoginDto;
import com.bh.admin.auth.dto.ParamRefreshTokenDto;
import com.bh.admin.auth.dto.RefreshTokenDto;
import com.bh.admin.auth.entity.RefreshToken;
import com.bh.admin.auth.service.RefreshTokenService;
import com.bh.admin.auth.service.TokenProvider;
import com.bh.admin.auth.service.UserPrincipal;
import com.bh.admin.util.exception.InternalErrorException;
import com.bh.admin.util.exception.UnauthorizationException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	

	@PostMapping("/login")
	public ResponseEntity<AuthResultDto> login(@RequestBody ParamLoginDto param){
		UsernamePasswordAuthenticationToken authenParam
			= new UsernamePasswordAuthenticationToken(param.getUserName(), param.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenParam);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		String jwToken = tokenProvider.generateToken(authentication);
		try {
			refreshTokenService.deleteByUserName(userPrincipal.getUsername());
		} catch (Exception e) {
			// TODO: handle exception
		}
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userPrincipal.getUsername());
				
		String fullName = userPrincipal.getFirstName() != null ? userPrincipal.getFirstName(): "";
		String lastName = userPrincipal.getLastName() != null? userPrincipal.getLastName() : "";
		AuthResultDto authResult
			= new AuthResultDto(jwToken, refreshToken.getToken(), userPrincipal.getEmail(), userPrincipal.getUsername(), (fullName.concat(" ".concat(lastName)).trim()));
		return new ResponseEntity<AuthResultDto>(authResult, HttpStatus.OK);
	}
	
//	@GetMapping("/logout")
//	public ResponseEntity<?> handleLogout(@AuthenticationPrincipal UserPrincipal userPrincipal){
//		if(Objects.nonNull(userPrincipal)) {
//			refreshTokenService.deleteByUserName(userPrincipal.getUsername());
//			SecurityContextHolder.getContext().setAuthentication(null);
//			return ResponseEntity.ok().build();
//		}
//		 throw new UsernameNotFoundException("User does not exit. Please contact to Adminstrator !");
//	}
	@PostMapping("/refreshToken")
	public ResponseEntity<RefreshTokenDto> refreshToken(@Valid @RequestBody ParamRefreshTokenDto param){
		return refreshTokenService.findByToken(param.getRefreshToken())
				.map(refreshTokenService::verifyRefreshToken)
				.map(refreshToken->{
					String newAccessToken = tokenProvider.generateTokenFromUserName(refreshToken.getUser().getUserName());
					return new ResponseEntity<RefreshTokenDto>(
							new RefreshTokenDto(newAccessToken, refreshToken.getToken()),
							HttpStatus.CREATED
					);
				})
				.orElseThrow(()-> new InternalErrorException("This refresh token is not in database !"));
	}
	
	@GetMapping("/getCurrentLoggedInfo")
	public ResponseEntity<?> getCurrentLoggedInfo(@AuthenticationPrincipal UserPrincipal userPrincipal){
		if(Objects.nonNull(userPrincipal)) {
			String fullName = userPrincipal.getFirstName() != null ? userPrincipal.getFirstName(): "";
			String lastName = userPrincipal.getLastName() != null? userPrincipal.getLastName() : "";
			AuthResultDto authResult
				= new AuthResultDto("", "", userPrincipal.getEmail(), userPrincipal.getUsername(), (fullName.concat(" ".concat(lastName)).trim()));
			return new ResponseEntity<>(authResult, HttpStatus.OK);
		}else {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
