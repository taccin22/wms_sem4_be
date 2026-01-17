package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.ProductsDTO;
import com.example.demo.entities.Categories;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

@RestController()
@RequestMapping("wms_sem4/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping(value = "filter-product-comp/{companyId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductsDTO>> filterProductComp(
			@PathVariable("companyId") Long companyId, 
			@RequestBody ProductsDTO req
			){
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth);
//			Categories category = categoryRepository.findByName(categoryName);
			return new ResponseEntity<List<ProductsDTO>>(productService.filterProductComp(
					companyId, 
					req.getSku(),
					req.getBarcode(), 
					req.getName(), 
					req.getCategoryId(), 
					req.getUnitId(), 
					req.getCostPrice(), 
					req.getSalePrice(), 
					req.getStatus()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

}
