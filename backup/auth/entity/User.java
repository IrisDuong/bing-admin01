package com.bh.admin.auth.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_user_details",uniqueConstraints = {
		@UniqueConstraint(columnNames = "user_name"),
		@UniqueConstraint(columnNames = "email")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@Column(name = "user_name")
	private String userName;
	
	@NotNull
	@Email
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "auth_user_roles"
		,inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
		,joinColumns = @JoinColumn(name="user_name")
	)
	private Set<Role> roles;

	public User(String userName, @NotNull @Email String email, String password, String firstName, String lastName,
			String phoneNo) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
	}
	
	
}
