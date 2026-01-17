package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	@Query("from Users where username = :username")
	public Users findByUsername(@Param("username") String username);
	
}
