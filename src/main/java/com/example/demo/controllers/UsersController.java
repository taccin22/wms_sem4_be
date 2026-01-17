package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.UserInsertDTO;
import com.example.demo.dtos.UserUpdateDTO;
import com.example.demo.services.CompanyService;
import com.example.demo.services.UserService;


@RestController()
@RequestMapping("wms_sem4/users")
public class UsersController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> findAll() {
		try {
			return new ResponseEntity<List<UserDTO>>(userService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<List<UserDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<UserDTO>(userService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PostMapping("/wms_sem4/users/loginProcess")
//	public ResponseEntity<?> login(@RequestBody UserInsertDTO user) {
//
//	    Authentication authentication =
//	        authenticationManager.authenticate(
//	            new UsernamePasswordAuthenticationToken(
//	                user.getUsername(),
//	                user.getPasswordHash()
//	            )
//	        );
//
//	    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	    return ResponseEntity.ok("Login success");
//	}
	
	
	@PostMapping(value = "createCompanyAdmin", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCompanyAdmin(@RequestBody UserInsertDTO userInsertDTO) {
			userInsertDTO.setPasswordHash(passwordEncoder.encode(userInsertDTO.getPasswordHash()));
			userService.createCompanyAdmin(userInsertDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@PostMapping(value = "createCompanyStaff", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCompanyStaff(@RequestBody UserInsertDTO userInsertDTO) {
		userInsertDTO.setPasswordHash(passwordEncoder.encode(userInsertDTO.getPasswordHash()));
		userService.createCompanyStaff(userInsertDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
			
	}
	
	@PutMapping(value = "update", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody UserUpdateDTO userUpdateDTO) {
		try {
			if (userService.update(userUpdateDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
