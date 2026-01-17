package com.example.demo.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;
import com.example.demo.dtos.UserInsertDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.Companies;
import com.example.demo.entities.Users;

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
		
		Converter<Long, Companies> convertCompanyIdToCompany = new AbstractConverter<Long, Companies>(){

			@Override
			protected Companies convert(Long id) {
				// TODO Auto-generated method stub
				Companies company = new Companies();
				company.setId(id);
				return company;
			}
			
		};
		
		mapper.typeMap(UserInsertDTO.class, Users.class).addMappings(m -> {
			m.using(convertCompanyIdToCompany).map(UserInsertDTO::getCompaniesId, Users::setCompanies);
		});
		
		mapper.addMappings(new PropertyMap<UserInsertDTO, Users>(){

			@Override
			protected void configure() {
				map().setUsername(source.getUsername());
				map().setFullName(source.getFullName());
				map().setPasswordHash(source.getPasswordHash());
				map().setStatus(source.getStatus());
				map().setCreatedAt(source.getCreatedAt());
			}
	
		});
		
		mapper.addMappings(new PropertyMap<Users, UserDTO>(){

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCompaniesId(source.getCompanies().getId());
				map().setRolesId(source.getRoles().getId());
				map().setCompaniesName(source.getCompanies().getCompanyName());
				map().setRolesName(source.getRoles().getRoleName());
			}
	
		});
		
		return mapper;
	}

}
