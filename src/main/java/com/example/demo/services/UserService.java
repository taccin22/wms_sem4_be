package com.example.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dtos.UsersDTO;
import com.example.demo.dtos.UserInsertDTO;
import com.example.demo.dtos.UserUpdateDTO;
import com.example.demo.entities.Users;

public interface UserService extends UserDetailsService{

	public List<UsersDTO> findAll();
	
	public Users findByUsername(String username);
	
	public UsersDTO findById(Long id);
	
	public UsersDTO findByUsernameDTO(String username);
	
	public boolean createCompanyAdmin(UserInsertDTO userInsertDTO);
	
	public boolean createCompanyStaff(UserInsertDTO userInsertDTO);
	
	public boolean update(UserUpdateDTO userUpdateDTO);
}
