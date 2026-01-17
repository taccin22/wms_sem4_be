package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;

public interface RoleRepository extends JpaRepository<Roles, Long>{

	@Query("From Roles where roleName = :name")
	public Roles findByRoleName(@Param("name")String name);
	

}
