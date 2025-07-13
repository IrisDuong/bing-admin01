package com.bh.admin.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.bh.admin.auth.jwt.config.UserDetailsCoreImpl;


public class BaseAuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		UserDetailsCoreImpl userPrincipal = (UserDetailsCoreImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<String> userName = Optional.empty();
		if(userPrincipal != null)
			if(StringUtils.hasText(userPrincipal.getUsername())) userName = Optional.of(userPrincipal.getUsername());
		return userName;
	}

}
