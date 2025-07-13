package com.bh.admin.auth.jwt.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class BingSecurityConfig {

	@Autowired
	AuthUserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	ResAuthenticationEntryPoint entryPoint;
	
	@Autowired
	AuthenticationFilter filter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
//		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	private CorsConfigurationSource configurationSource() {
		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration cors = new CorsConfiguration();
				cors.setAllowedHeaders(Collections.singletonList("*"));
				cors.setAllowedMethods(Collections.singletonList("*"));
				cors.setAllowCredentials(true);
				cors.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
				cors.setMaxAge(3600L);
				return cors;
			}
		};
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.cors(cors->cors.configurationSource(configurationSource()))
		.authorizeHttpRequests(auth->{
			auth.requestMatchers("/auth/**","/**").permitAll()
			.anyRequest().authenticated();
		})
		.exceptionHandling(ex-> ex.authenticationEntryPoint(entryPoint))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider())
		.formLogin(form->form.disable())
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
