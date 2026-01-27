package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dtos.CompaniesDTO;
import com.example.demo.dtos.CompaniesInsertDTO;
import com.example.demo.dtos.LoginRequest;
import com.example.demo.dtos.LoginResult;
import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.UserInsertDTO;
import com.example.demo.dtos.UserUpdateDTO;
import com.example.demo.entities.Users;
import com.example.demo.services.CompanyService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


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
	
	@GetMapping(value = "find-by-username/{username}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("AUTH = " + auth);

	    return ResponseEntity.ok(userService.findByUsernameDTO(username));
	}
	
	@PostMapping(value = "login", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResult> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest) {

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
     //  Create SecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
        
        Users user = userService.findByUsername(request.getUsername());

        session.setAttribute("userId", user.getId());
        session.setAttribute("roleId", user.getRoles().getId());

        Long companyId = null;
        if (user.getCompanies() != null && user.getCompanies().getId() != null) {
        	companyId =  user.getCompanies().getId();
            session.setAttribute("companyId", user.getCompanies().getId());
        }

        return ResponseEntity.ok(
            new LoginResult(true, user.getId(), user.getRoles().getRoleName(), companyId)
        );
    }
	
	@GetMapping("/debug/auth")
	public ResponseEntity<?> debugAuth() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    return ResponseEntity.ok(
	        Map.of(
	            "authClass", auth.getClass().getSimpleName(),
	            "authenticated", auth.isAuthenticated(),
	            "principal", auth.getPrincipal(),
	            "authorities", auth.getAuthorities()
	        )
	    );
	}
	
	
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
