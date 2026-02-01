package com.example.demo.dtos;

import java.math.BigDecimal;

public class ProductsInsertDTO {
    
    private Long categoryId;
    private Long unitId;
    private String unitName;
    private Long companyId;
    private String sku;
    private String barcode;
    private String name;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private Byte status;


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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


	public ProductsInsertDTO(Long categoryId, Long unitId, String unitName, Long companyId, String sku, String barcode,
			String name, BigDecimal costPrice, BigDecimal salePrice, Byte status) {
		super();
		this.categoryId = categoryId;
		this.unitId = unitId;
		this.unitName = unitName;
		this.companyId = companyId;
		this.sku = sku;
		this.barcode = barcode;
		this.name = name;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.status = status;
	}

	public ProductsInsertDTO() {
		super();
	}


	

    
    
}
