package com.example.demo.dtos;

import java.sql.Timestamp;

public class UsersDTO {

	private Long id;
	private Long companyId;
	private String companyName;
	private String username;
	private String passwordHash;
	private String fullName;
	private Long roleId;
	private String roleName;
	private Timestamp createdAt;

	public UsersDTO() {
		super();
	}

	public UsersDTO(Long id, Long companyId, String companyName, String username, String passwordHash, String fullName,
			Long roleId, String roleName, Timestamp createdAt) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.username = username;
		this.passwordHash = passwordHash;
		this.fullName = fullName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
