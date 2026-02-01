package com.example.demo.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.CategoriesInsertDTO;
import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;
import com.example.demo.dtos.ProductsDTO;
import com.example.demo.dtos.ProductsInsertDTO;
import com.example.demo.dtos.UsersDTO;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Companies;
import com.example.demo.entities.Products;
import com.example.demo.entities.Units;
import com.example.demo.entities.Users;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Converter<Long, Companies> convertCompanyIdToCompany = new AbstractConverter<Long, Companies>() {

			@Override
			protected Companies convert(Long companyId) {
				Companies company = new Companies();
				company.setId(companyId);
				return company;
			}
			
		};

		Converter<Long, Categories> convertCategoryIdToCategory = new AbstractConverter<Long, Categories>() {

			@Override
			protected Categories convert(Long categoryId) {
				if (categoryId == null) return null;
				Categories category = new Categories();
				category.setId(categoryId);
				return category;
			}
			
		};

		Converter<Long, Units> convertUnitIdToUnit = new AbstractConverter<Long, Units>() {

			@Override
			protected Units convert(Long unitId) {
				if (unitId == null) return null;
				Units unit = new Units();
				unit.setId(unitId);
				return unit;
			}
			
		};
		
	    /* ================= Companies ================= */
		
		mapper.addMappings(new PropertyMap<CompaniesDTO, Companies>() {

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

		mapper.addMappings(new PropertyMap<CompaniesInsertDTO, Companies>() {

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

	    /* ================= Products ================= */
		
		mapper.addMappings(new PropertyMap<Products, ProductsDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setName(source.getName());
				map().setSku(source.getSku());
				map().setBarcode(source.getBarcode());
				map().setSalePrice(source.getSalePrice());
				map().setCostPrice(source.getCostPrice());
				map().setStatus(source.getStatus());
				map().setCompanyId(source.getCompanies().getId());
				map().setCompanyName(source.getCompanies().getCompanyName());
				map().setCategoryId(source.getCategories().getId());
				map().setCategoryName(source.getCategories().getName());
				map().setUnitId(source.getUnits().getId());
				map().setUnitName(source.getUnits().getUnitName());
			}
	
		});

		mapper.typeMap(ProductsInsertDTO.class, Products.class).addMappings(m -> {
			m.using(convertCompanyIdToCompany).map(ProductsInsertDTO::getCompanyId, Products::setCompanies);
			m.using(convertCategoryIdToCategory).map(ProductsInsertDTO::getCategoryId, Products::setCategories);
			m.using(convertUnitIdToUnit).map(ProductsInsertDTO::getUnitId, Products::setUnits);
		});

		mapper.addMappings(new PropertyMap<ProductsInsertDTO, Products>() {

			@Override
			protected void configure() {
				map().setName(source.getName());
				map().setSku(source.getSku());
				map().setBarcode(source.getBarcode());
				map().setSalePrice(source.getSalePrice());
				map().setCostPrice(source.getCostPrice());
				map().setStatus(source.getStatus());
			}
	
		});

		mapper.typeMap(ProductsDTO.class, Products.class).addMappings(m -> {
			m.using(convertCompanyIdToCompany).map(ProductsDTO::getCompanyId, Products::setCompanies);
			m.using(convertCategoryIdToCategory).map(ProductsDTO::getCategoryId, Products::setCategories);
			m.using(convertUnitIdToUnit).map(ProductsDTO::getUnitId, Products::setUnits);
		});

		mapper.addMappings(new PropertyMap<ProductsDTO, Products>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setName(source.getName());
				map().setSku(source.getSku());
				map().setBarcode(source.getBarcode());
				map().setSalePrice(source.getSalePrice());
				map().setCostPrice(source.getCostPrice());
				map().setStatus(source.getStatus());
			}
	
		});

	    /* ================= Categories ================= */
		
		mapper.addMappings(new PropertyMap<Categories, CategoriesDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCompanyId(source.getCompanies().getId());
				map().setCompanyName(source.getCompanies().getCompanyName());
				map().setName(source.getName());
				map().setParentId(source.getCategories().getId());
			}
	
		});
		
		mapper.typeMap(CategoriesInsertDTO.class, Categories.class).addMappings(m -> {
			m.using(convertCompanyIdToCompany).map(CategoriesInsertDTO::getCompanyId, Categories::setCompanies);
			m.using(convertCategoryIdToCategory).map(CategoriesInsertDTO::getParentId, Categories::setCategories);
		});

		mapper.addMappings(new PropertyMap<CategoriesInsertDTO, Categories>() {

			@Override
			protected void configure() {
				map().setName(source.getName());
			}
	
		});

		mapper.typeMap(CategoriesDTO.class, Categories.class).addMappings(m -> {
			m.using(convertCompanyIdToCompany).map(CategoriesDTO::getCompanyId, Categories::setCompanies);
			m.using(convertCategoryIdToCategory).map(CategoriesDTO::getParentId, Categories::setCategories);
		});

		mapper.addMappings(new PropertyMap<CategoriesDTO, Categories>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setName(source.getName());
			}
	
		});

	    /* ================= Users ================= */

		mapper.addMappings(new PropertyMap<Users, UsersDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCompanyId(source.getCompanies().getId());
				map().setCompanyName(source.getCompanies().getCompanyName());
				map().setUsername(source.getUsername());
				map().setPasswordHash(source.getPasswordHash());
				map().setRoleId(source.getRoles().getId());
				map().setRoleName(source.getRoles().getRoleName());
				map().setCreatedAt(source.getCreatedAt());
			}
	
		});
		
		
		return mapper;
	}

}
