package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long>{
	
	@Query("FROM Categories WHERE name = :name")
	public Categories findByName(String name);

}
