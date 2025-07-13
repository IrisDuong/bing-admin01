package com.bh.admin.account.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.admin.account.dao.AccountDao;
import com.bh.admin.account.dto.AccountDto;
import com.bh.admin.account.dto.RoleDao;
import com.bh.admin.account.entity.Account;
import com.bh.admin.account.entity.ERole;
import com.bh.admin.account.entity.Role;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDao accountDao;

	@Autowired
	RoleDao roleDao;
	
	@Override
	public void createAccount(AccountDto dto) throws Exception{
		// TODO Auto-generated method stub
			Set<Role> roles =  dto.getRoles().stream()
			.map(roleName->{
				 Role role = null;
				try {
					role = roleDao.findByName(ERole.valueOf(roleName))
							.orElseThrow(()-> new Exception("Role doesn't exist"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return role;
			}).collect(Collectors.toSet());
			
			Account account = Account.builder()
					.userName(dto.getUserName())
					.password(dto.getPassword())
					.email(dto.getEmail())
					.phoneNo(dto.getPhoneNo())
					.address(dto.getAddress())
					.roles(roles)
					.build();
			accountDao.save(account);
	}

}
