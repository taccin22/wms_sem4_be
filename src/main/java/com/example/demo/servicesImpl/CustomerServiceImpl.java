package com.example.demo.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CategoriesDTO;
import com.example.demo.dtos.CustomersDTO;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Companies;
import com.example.demo.entities.Customers;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CustomersDTO> findAll() {
		List<Customers> customers = customerRepository.findAll();
		return modelMapper.map(customers, new TypeToken<List<CustomersDTO>>() {}.getType());
	}

	@Override
	public CustomersDTO findById(Long id) {
		Customers category = customerRepository.findById(id).get();
		return modelMapper.map(category, CustomersDTO.class);
	}
	
	//create

	@Override
	public boolean update(CustomersDTO customersDTO) {
		try {
			Customers customers = modelMapper.map(customersDTO, Customers.class);
			customerRepository.save(customers);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

}
