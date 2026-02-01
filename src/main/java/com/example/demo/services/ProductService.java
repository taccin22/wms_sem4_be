package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.example.demo.dtos.ProductsDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface ProductService {
	
	public List<ProductsDTO> findAll();
	public ProductsDTO findById(Long id);
	
	
	public List<ProductsDTO> findAllComp(Long companyId);
	public ProductsDTO findByIdComp(Long companyId, Long id);
	
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
