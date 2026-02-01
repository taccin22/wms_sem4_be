package com.example.demo.servicesImpl;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.ProductsDTO;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Products;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private HttpServletRequest httpRequest;

	@Override
	public List<ProductsDTO> filterProductComp(Long companyId, String sku, String barcode, String name, Long categoryId,
			Long unitId, BigDecimal costPrice, BigDecimal salePrice, Byte status) {
		List<Products> products = productRepository.filterProductComp(companyId, sku, barcode, name, categoryId,
				unitId, costPrice, salePrice, status);
		return modelMapper.map(products, new TypeToken<List<ProductsDTO>>() {}.getType());
	}

	@Override
	public List<ProductsDTO> findAll() {
		List<Products> products = productRepository.findAll();
		return modelMapper.map(products, new TypeToken<List<ProductsDTO>>() {}.getType());
	}

	@Override
	public ProductsDTO findById(Long id) {
		Products products = productRepository.findById(id).get();
		return modelMapper.map(products, ProductsDTO.class);
	}

	@Override
	public List<ProductsDTO> findAllComp(Long companyId) {
		List<Products> products = productRepository.findAllComp(companyId);
		return modelMapper.map(products, new TypeToken<List<ProductsDTO>>() {}.getType());
	}

	@Override
	public ProductsDTO findByIdComp(Long companyId, Long id) {
		Products products = productRepository.findByIdComp(companyId, id);
		return modelMapper.map(products, ProductsDTO.class);
	}

}
