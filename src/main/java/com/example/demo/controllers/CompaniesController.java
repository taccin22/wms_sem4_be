package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;
import com.example.demo.services.CompanyService;


@RestController()
@RequestMapping("wms_sem4/companies")
public class CompaniesController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompaniesDTO>> findAll() {
		try {
			return new ResponseEntity<List<CompaniesDTO>>(companyService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<List<CompaniesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompaniesDTO> findById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<CompaniesDTO>(companyService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<CompaniesDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "create", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody CompaniesInsertDTO companiesInsertDTO) {
		try {
			if (companyService.create(companiesInsertDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "update", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody CompaniesDTO companiesDTO) {
		try {
			if (companyService.update(companiesDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
