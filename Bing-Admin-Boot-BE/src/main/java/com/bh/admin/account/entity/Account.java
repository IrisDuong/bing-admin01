package com.bh.admin.account.entity;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bh.admin.util.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "acc_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

	@Id
	@GeneratedValue(generator = "account_pk_gen")
	@GenericGenerator(strategy = AppConstants.PK_GENERATOR_BY_DATE_LOCATION, name = "account_pk_gen", parameters = @Parameter(name = "prefix", value = "01"))
	@Column(name = "account_id", nullable = false, length = 20)
	private String id;
	
	@Column(name = "username", unique = true)
	private String userName;
	private String password;
	private String fullName;
	
	@Column(name = "email", unique = true)
	private String email;
	private String phoneNo;
	private String address;
	
	@ManyToMany
	@JoinTable(name = "account_role_mapping",
		inverseJoinColumns = @JoinColumn(name="role_id"), 
		joinColumns = @JoinColumn(name="account_id")
	)
	private Set<Role> roles;
	public Account(String userName, String password, String email, String phoneNo, String address) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	public Account(String userName, String password, String email, String phoneNo, String address, Set<Role> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.roles = roles;
	}
	
	
}
