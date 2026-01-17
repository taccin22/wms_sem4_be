package com.example.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.UserInsertDTO;
import com.example.demo.dtos.UserUpdateDTO;
import com.example.demo.entities.Users;

public interface UserService extends UserDetailsService{

	public List<UserDTO> findAll();
	
	public Users findByUsername(String username);
	
	public UserDTO findById(Long id);
	
	public boolean createCompanyAdmin(UserInsertDTO userInsertDTO);
	
	public boolean createCompanyStaff(UserInsertDTO userInsertDTO);
	
	public boolean update(UserUpdateDTO userUpdateDTO);
}
