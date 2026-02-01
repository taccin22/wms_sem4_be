package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CustomersDTO;
import com.example.demo.services.CustomerService;

@RestController()
@RequestMapping("wms_sem4/customers")
public class CustomersController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomersDTO>> findAll() {
		try {
			return new ResponseEntity<List<CustomersDTO>>(customerService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CustomersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomersDTO> findById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<CustomersDTO>(customerService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<CustomersDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
