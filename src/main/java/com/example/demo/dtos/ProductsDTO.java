package com.example.demo.dtos;

import java.math.BigDecimal;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Companies;
import com.example.demo.entities.Units;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProductsDTO {
	
	private Long id;

    
    private Long categoryId;
    private String categoryName;

    
    private Long unitId;
    private String unitName;

    
    private Long companyId;
    private String companyName;

    
    private String sku;

    
    private String barcode;

    
    private String name;

    
    private BigDecimal costPrice;

    
    private BigDecimal salePrice;


    private Byte status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Long getUnitId() {
		return unitId;
	}


	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
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


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}


	public BigDecimal getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}


	public Byte getStatus() {
		return status;
	}


	public void setStatus(Byte status) {
		this.status = status;
	}


	public ProductsDTO(Long id, Long categoryId, String categoryName, Long unitId, String unitName, Long companyId,
			String companyName, String sku, String barcode, String name, BigDecimal costPrice, BigDecimal salePrice,
			Byte status) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.unitId = unitId;
		this.unitName = unitName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.sku = sku;
		this.barcode = barcode;
		this.name = name;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.status = status;
	}


	public ProductsDTO() {
		super();
	}


	

    
    
}
