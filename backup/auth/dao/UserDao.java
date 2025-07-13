package com.bh.admin.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bh.admin.auth.entity.User;


public interface UserDao extends JpaRepository<User,String>{
	boolean existsByUserName(String userName);
	boolean existsByEmail(String email);
}
