package com.bh.admin.auth.service;

import java.util.Optional;

import com.bh.admin.auth.dto.UserDto;
import com.bh.admin.auth.entity.User;

public interface UserService {

	UserDto getUserByUserName(String userName);
	boolean existsByUserName(String userName);
	boolean existsByEmail(String email);
	UserDto saveUer(User user);
}
