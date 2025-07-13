package com.bh.admin.auth.config;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.bh.admin.auth.service.RestAuthenticationEntryPoint;
import com.bh.admin.auth.service.TokenFilter;
import com.bh.admin.auth.service.UserDetailsServiceImpl;
import com.bh.admin.auth.service.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	RestAuthenticationEntryPoint entryPoint;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	TokenFilter tokenFilter;
	
	@Autowired
	LogoutSuccessfullyHandlerImpl logoutSuccessHandlerImpl;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

//	@Bean
//	public CorsFilter corsFilter() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true); // Important for cookies (if used)
//	    config.addAllowedOrigin("http://localhost:3000"); // Replace with your origin
//	    config.addAllowedMethod("*"); // Allow all methods (adjust based on your API needs)
//	    config.addAllowedHeader("*"); // Allow all headers (adjust based on your API needs)
//	    source.registerCorsConfiguration("/**", config);
//	    source.registerCorsConfiguration("/logout", config);
//	    return new CorsFilter(source);
//	}
	
	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setExposedHeaders(Arrays.asList("Authorization"));
				config.setMaxAge(3600L);
				return config;
			}
		};
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf-> csrf.disable())
					.cors(cors-> cors.configurationSource(corsConfigurationSource()))
//					.cors(cors->cors.disable())
					.authorizeHttpRequests(auth->{
						auth.requestMatchers("/auth/**","/**","/static/**").permitAll();
//						auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
						auth.anyRequest().authenticated();
					})
					.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.exceptionHandling(ex-> ex.authenticationEntryPoint(entryPoint))
					.formLogin(form-> form.disable())
					.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
					.authenticationProvider(authenticationProvider())
					.logout(logout->{
						logout.logoutUrl("/logout");
						logout.logoutSuccessHandler(logoutSuccessHandlerImpl);
					});
		return httpSecurity.build();
	}
}
