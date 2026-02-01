package com.example.demo.dtos;

public class CategoriesInsertDTO {

	private Long companyId;
	private String name;
	private Long parentId;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public CategoriesInsertDTO(Long companyId, String name, Long parentId) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.parentId = parentId;
	}

	public CategoriesInsertDTO() {
		super();
	}

}
