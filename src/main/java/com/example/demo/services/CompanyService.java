package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;

public interface CompanyService {

	public List<CompaniesDTO> findAll();
	
	public CompaniesDTO findById(Long id);
	
	public boolean create(CompaniesInsertDTO companiesInsertDTO);
	
	public boolean update(CompaniesDTO companiesDTO);
	
}
