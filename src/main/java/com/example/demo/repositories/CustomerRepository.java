package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long>{
	
	@Query("FROM Customers WHERE name = :name")
	public Customers findByName(String name);

}
