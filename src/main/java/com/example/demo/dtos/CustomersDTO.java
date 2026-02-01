package com.example.demo.dtos;

public class CustomersDTO {

	private Long id;
	private Long companyId;
    private String companyName;
    private String Name;
    private String phone;
    private String address;
    
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public CustomersDTO(Long id, Long companyId, String companyName, String name, String phone, String address) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		Name = name;
		this.phone = phone;
		this.address = address;
	}
	public CustomersDTO() {
		super();
	}
    
    
	
}
