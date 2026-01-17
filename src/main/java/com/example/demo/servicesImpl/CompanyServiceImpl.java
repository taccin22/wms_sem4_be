package com.example.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;
import com.example.demo.entities.Companies;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CompaniesDTO> findAll() {
		List<Companies> companies = companyRepository.findAll();
		return modelMapper.map(companies, new TypeToken<List<CompaniesDTO>>() {}.getType());
	}

	@Override
	public CompaniesDTO findById(Long id) {
		Companies Company = companyRepository.findById(id).get();
		return modelMapper.map(Company, CompaniesDTO.class);
	}

	@Override
	public boolean create(CompaniesInsertDTO companiesInsertDTO) {
		try {
			Companies companies = modelMapper.map(companiesInsertDTO, Companies.class);
			companyRepository.save(companies);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CompaniesDTO companiesDTO) {
		try {
			Companies companies = modelMapper.map(companiesDTO, Companies.class);
			companyRepository.save(companies);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
