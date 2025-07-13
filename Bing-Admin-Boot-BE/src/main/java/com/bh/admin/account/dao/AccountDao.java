package com.bh.admin.account.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bh.admin.account.entity.Account;

public interface AccountDao extends JpaRepository<Account, String>{

	Optional<Account> findByUserName(String userName);
	Boolean existsByEmail(String email);
	Boolean existsByUserName(String userName);
}
