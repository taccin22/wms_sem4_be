package com.example.demo.dtos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


public class UserDTO {

	private Long id;
	private Long rolesId;
	private String rolesName;
	private Long companiesId;
	private String companiesName;
	private String username;
	private String fullName;
	private Byte status;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Timestamp createdAt;
	
	public UserDTO() {
		super();
	}

	public UserDTO(Long id, Long rolesId, String rolesName, Long companiesId, String companiesName, String username,
			 String fullName, Byte status, Timestamp createdAt) {
		super();
		this.id = id;
		this.rolesId = rolesId;
		this.rolesName = rolesName;
		this.companiesId = companiesId;
		this.companiesName = companiesName;
		this.username = username;
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

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	public Long getCompaniesId() {
		return companiesId;
	}

	public void setCompaniesId(Long companiesId) {
		this.companiesId = companiesId;
	}

	public String getCompaniesName() {
		return companiesName;
	}

	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
