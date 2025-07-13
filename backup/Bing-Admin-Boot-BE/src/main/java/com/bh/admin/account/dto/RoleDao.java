package com.bh.admin.account.dto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bh.admin.account.entity.ERole;
import com.bh.admin.account.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(ERole name);
}
