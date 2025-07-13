package com.bh.admin.auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.admin.auth.dao.UserDao;
import com.bh.admin.auth.dto.UserDto;
import com.bh.admin.auth.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	@Override
	public UserDto getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		User user = userDao.findById(userName).orElseThrow(()-> new UsernameNotFoundException(String.format("Not Found User with user name", userName)));
		String fullName = user.getFirstName() != null ? user.getFirstName(): "";
		String lastName = user.getLastName() != null? user.getLastName() : "";
		
		return UserDto.builder()
				.userName(user.getUserName())
				.email(user.getEmail())
				.fullName((fullName.concat(" ".concat(lastName)).trim()))
				.phoneNo(user.getPhoneNo())
				.build();
	}

	@Override
	public boolean existsByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.existsByUserName(userName);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.existsByEmail(email);
	}

	@Override
	public UserDto saveUer(User user) {
		// TODO Auto-generated method stub
		 user =  userDao.save(user);
		 String fullName = user.getFirstName() != null ? user.getFirstName(): "";
			String lastName = user.getLastName() != null? user.getLastName() : "";
			
		return null;
	}
}
