package com.example.demo.servicesImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.WmsSem4BeApplication;
import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.UsersDTO;
import com.example.demo.dtos.UserInsertDTO;
import com.example.demo.dtos.UserUpdateDTO;
import com.example.demo.entities.Companies;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;
import com.example.demo.enums.Status;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private HttpSession httpSession;


	@Override
	public List<UsersDTO> findAll() {
		List<Users> users = userRepository.findAll();
		return modelMapper.map(users, new TypeToken<List<UsersDTO>>() {}.getType());
	}

	@Override
	public UsersDTO findById(Long id) {
		Users user = userRepository.findById(id).get();
		if(user != null) {
			return modelMapper.map(user, UsersDTO.class);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found");
		}
		
	}

	@Override
	public boolean createCompanyAdmin(UserInsertDTO userInsertDTO) {
		Users user = modelMapper.map(userInsertDTO, Users.class);
		
		if (user.getCompanies() == null || user.getCompanies().getId() == null) {
		    throw new ResponseStatusException(
		        HttpStatus.BAD_REQUEST,
		        "Company To cannot be null"
		    );
		}
		Roles adminRole = roleRepository
	            .findByRoleName("ROLE_COMPANY_ADMIN");
		if(adminRole == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found");
		}
		user.setStatus(Status.ACTIVE.getValue());
	    user.setRoles(adminRole);
//	    user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		userRepository.save(user);
		return true;

	}

	@Override
	public boolean createCompanyStaff(UserInsertDTO userInsertDTO) {
		Users user = modelMapper.map(userInsertDTO, Users.class);

		Long roleId = Long.parseLong(httpSession.getAttribute("roleId").toString());
		if(roleId == 1) {
			if (user.getCompanies() == null || user.getCompanies().getId() == null) {
			    throw new ResponseStatusException(
			        HttpStatus.BAD_REQUEST,
			        "Company To cannot be null"
			    );
			}
		}else {
			Long companyId = Long.parseLong(httpSession.getAttribute("companyId").toString());
			Companies company = companyRepository.findById(companyId).get();
			user.setCompanies(company);
		}
		
//		System.out.println("com id: " + companyId);
//		if(companyId != null) {
//			Companies company = companyRepository.findById(companyId).get();
//			user.setCompanies(company);
//		}
		Roles companyStaffRole = roleRepository
	            .findByRoleName("ROLE_COMPANY_STAFF");
		if(companyStaffRole == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found");
		}
	    user.setRoles(companyStaffRole);
	    user.setStatus(Status.ACTIVE.getValue());
//	    user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean update(UserUpdateDTO userUpdateDTO) {
		try {
			Users user = modelMapper.map(userUpdateDTO, Users.class);
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		System.out.println("AUTH TRY: " + username);
		System.out.println("ROLE FROM DB = " + user.getRoles().getRoleName());

		return new User(
		        user.getUsername(),
		        user.getPasswordHash(),
		        List.of(new SimpleGrantedAuthority(user.getRoles().getRoleName()))
		    );
	}
	
	
	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UsersDTO findByUsernameDTO(String username) {
		Users user = userRepository.findByUsername(username);
		if(user != null) {
			return modelMapper.map(user, UsersDTO.class);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found");
		}
	}
	
	
}
