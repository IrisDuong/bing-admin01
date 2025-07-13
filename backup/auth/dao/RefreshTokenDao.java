package com.bh.admin.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.bh.admin.auth.entity.RefreshToken;
import com.bh.admin.auth.entity.User;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Integer>{

	Optional<RefreshToken> findByToken(String token);
	
	@Modifying
	int deleteByUser(User user);
}
