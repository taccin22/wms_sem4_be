package com.example.demo.dtos;

import java.sql.Timestamp;


public class UserUpdateDTO {

	private Long id;
	private Long rolesId;
	private Long companiesId;
	private String username;
	private String passwordHash;
	private String fullName;
	private Byte status;
	private Timestamp createdAt;
	
	public UserUpdateDTO() {
		super();
	}
	
	public UserUpdateDTO(Long id, Long rolesId,String rolesName, Long companiesId, String companiesName, String username, String passwordHash,
			String fullName, Byte status, Timestamp createdAt) {
		super();
		this.id = id;
		this.rolesId = rolesId;
		this.companiesId = companiesId;
		this.username = username;
		this.passwordHash = passwordHash;
		this.fullName = fullName;
		this.status = status;
		this.createdAt = createdAt;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRolesId() {
		return rolesId;
	}

	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}

	public Long getCompaniesId() {
		return companiesId;
	}

	public void setCompaniesId(Long companiesId) {
		this.companiesId = companiesId;
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
