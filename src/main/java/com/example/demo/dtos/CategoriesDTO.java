package com.example.demo.dtos;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Companies;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CategoriesDTO {
	
	private Long id;
    private Long companyId;
    private String companyName;
    private String name;
    private Long parentId;
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
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public CategoriesDTO(Long id, Long companyId, String companyName, String name, Long parentId) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.name = name;
		this.parentId = parentId;
	}
	public CategoriesDTO() {
		super();
	}
    
    


}
