package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CustomersDTO;

public interface CustomerService {
	
	public List<CustomersDTO> findAll();
	public CustomersDTO findById(Long id);
	
	public boolean update(CustomersDTO customersDTO);

}
