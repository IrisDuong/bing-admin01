package com.bh.admin.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.admin.auth.dao.UserDao;
import com.bh.admin.auth.entity.User;
import com.bh.admin.util.exception.NoContentException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			User user =  userDao.findById(username).orElseThrow(()-> new UsernameNotFoundException(String.format("Not Found User with user name", username)));
			return UserPrincipal.create(user);
	}
}
