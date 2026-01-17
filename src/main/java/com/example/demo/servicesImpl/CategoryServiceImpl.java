package com.example.demo.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Companies;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategoriesDTO> findAll() {
		List<Categories> categories = categoryRepository.findAll();
		return modelMapper.map(categories, new TypeToken<List<CategoriesDTO>>() {}.getType());
	}

	@Override
	public CategoriesDTO findById(Long id) {
		Categories category = categoryRepository.findById(id).get();
		return modelMapper.map(category, CategoriesDTO.class);
	}
	
	//create

	@Override
	public boolean update(CategoriesDTO categoriesDTO) {
		try {
			Categories categories = modelMapper.map(categoriesDTO, Categories.class);
			categoryRepository.save(categories);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

}
