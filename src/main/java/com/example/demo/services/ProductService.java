package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.dtos.ProductsDTO;

public interface ProductService {
	
	public List<ProductsDTO> filterProductComp(
			Long companyId, 
			String sku,
			String barcode, 
			String name, 
			Long categoryId, 
			Long unitId, 
			BigDecimal costPrice, 
			BigDecimal salePrice, 
			Byte status
			);

}
