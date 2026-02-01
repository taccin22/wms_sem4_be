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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CategoriesDTO;
//import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.ProductsDTO;
import com.example.demo.dtos.ProductsInsertDTO;
import com.example.demo.dtos.UserInsertDTO;
//import com.example.demo.entities.Categories;
//import com.example.demo.repositories.CategoryRepository;
//import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController()
@RequestMapping("wms_sem4/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
//	private CategoryRepository categoryRepository;
	
	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductsDTO>> findAll() {
		try {
			return new ResponseEntity<List<ProductsDTO>>(productService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductsDTO> findById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<ProductsDTO>(productService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<ProductsDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "filter-product-comp", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductsDTO>> filterProductComp(
//			@RequestParam("companyId") Long companyId, 
			HttpServletRequest httpRequest,
			
			@RequestBody ProductsDTO req
			){
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth);
			Long companyId = Long.parseLong(httpSession.getAttribute("companyId").toString());
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
	
	@GetMapping(value = "find-all-comp", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductsDTO>> findAllComp(
//			@RequestParam("companyId") Long companyId, 
//			HttpServletRequest httpRequest
			){
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					System.out.println(auth);
					Long companyId = Long.parseLong(httpSession.getAttribute("companyId").toString());
		//			Categories category = categoryRepository.findByName(categoryName);
					return new ResponseEntity<List<ProductsDTO>>(productService.findAllComp(companyId),HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<List<ProductsDTO>>(HttpStatus.BAD_REQUEST);
				}
			}
	
	@GetMapping(value = "find-by-id-comp/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductsDTO> findAllComp(@PathVariable("id") Long id) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth);
			Long companyId = Long.parseLong(httpSession.getAttribute("companyId").toString());
			return new ResponseEntity<ProductsDTO>(productService.findByIdComp(companyId, id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ProductsDTO>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping(value = "createProduct", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createProduct(@RequestBody ProductsInsertDTO productsInsertDTO) {
			
			productService.createProduct(productsInsertDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);

	}
	

	@PutMapping(value = "updateProduct", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateProduct(@RequestBody ProductsDTO productsDTO) {
			
			productService.updateProduct(productsDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
