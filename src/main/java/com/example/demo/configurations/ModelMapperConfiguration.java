package com.example.demo.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;
import com.example.demo.dtos.ProductsDTO;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Companies;
import com.example.demo.entities.Products;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		mapper.addMappings(new PropertyMap<CompaniesInsertDTO, Companies>(){

			@Override
			protected void configure() {
				map().setCompanyName(source.getCompanyName());
				map().setCompanyCode(source.getCompanyCode());
				map().setAddress(source.getAddress());
				map().setPhone(source.getPhone());
				map().setStatus(source.getStatus());
				map().setTaxCode(source.getTaxCode());
			}
	
		});
		
		mapper.addMappings(new PropertyMap<CompaniesDTO, Companies>(){

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCompanyName(source.getCompanyName());
				map().setCompanyCode(source.getCompanyCode());
				map().setAddress(source.getAddress());
				map().setPhone(source.getPhone());
				map().setStatus(source.getStatus());
				map().setTaxCode(source.getTaxCode());
			}
	
		});
		
		mapper.addMappings(new PropertyMap<Products, ProductsDTO>(){

			@Override
			protected void configure() {
				map().setCompanyName(source.getCompanies().getCompanyName());
				map().setCompanyId(source.getCompanies().getId());
				map().setCategoryName(source.getCategories().getName());
				map().setUnitName(source.getUnits().getUnitName());
				map().setId(source.getId());
				map().setCategoryId(source.getCategories().getId());
				map().setUnitId(source.getUnits().getId());
				
			}
	
		});
		
		
		
		return mapper;
	}

}
