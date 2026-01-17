package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CategoriesDTO;

public interface CategoryService {
	
	public List<CategoriesDTO> findAll();
	public CategoriesDTO findById(Long id);
	
	public boolean update(CategoriesDTO categoriesDTO);

}
