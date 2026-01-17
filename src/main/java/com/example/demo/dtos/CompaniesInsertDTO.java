package com.example.demo.dtos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CompaniesInsertDTO {

    private String companyCode;
    private String companyName;
    private String taxCode;
    private String address;
    private String phone;
    private Byte status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Timestamp createdAt;
    
    public CompaniesInsertDTO() {
		super();
	}
    
	public CompaniesInsertDTO(String companyCode, String companyName, String taxCode, String address, String phone,
			Byte status, Timestamp createdAt) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.taxCode = taxCode;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
