package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.services.CategoryService;

@RestController()
@RequestMapping("wms_sem4/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoriesDTO>> findAll() {
		try {
			return new ResponseEntity<List<CategoriesDTO>>(categoryService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategoriesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriesDTO> findById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<CategoriesDTO>(categoryService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<CategoriesDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
