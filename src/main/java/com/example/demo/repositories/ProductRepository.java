package com.example.demo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{
	
//	@Query("""
//		    SELECT p FROM Products p
//		    WHERE p.companies.id = :companyId
//			  AND (:sku IS NULL OR p.sku = :sku)
//		      AND (:barcode IS NULL OR p.barcode = :barcode)
//		      AND (:name IS NULL OR p.name = :name)
//		      AND (:categoryId IS NULL OR p.categories.id = :categoryId)
//		      AND (:unitId IS NULL OR p.units.id = :unitId)
//		      AND (:costPrice IS NULL OR p.costPrice = :costPrice)
//		      AND (:salePrice IS NULL OR p.salePrice = :salePrice)
//		      AND (:status IS NULL OR p.status = :status)
//		""")
	@Query("""
		    SELECT p FROM Products p
		    WHERE p.companies.id = :companyId
		      AND (
		            :sku IS NULL 
		            OR :sku = '' 
		            OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :sku, '%'))
		          )
		      AND (
		            :barcode IS NULL 
		            OR :barcode = '' 
		            OR p.barcode = :barcode
		          )
		      AND (
		            :name IS NULL 
		            OR :name = '' 
		            OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
		          )
		      AND (
		            :categoryId IS NULL 
		            OR p.categories.id = :categoryId
		          )
		      AND (
		            :unitId IS NULL 
		            OR p.units.id = :unitId
		          )
		      AND (
		            :costPrice IS NULL 
		            OR p.costPrice = :costPrice
		          )
		      AND (
		            :salePrice IS NULL 
		            OR p.salePrice = :salePrice
		          )
		      AND (
		            :status IS NULL 
		            OR p.status = :status
		          )
		""")
	public List<Products> filterProductComp(
			@Param("companyId") Long companyId, 
			@Param("sku") String sku, 
			@Param("barcode") String barcode, 
			@Param("name") String name, 
			@Param("categoryId") Long categoryId, 
			@Param("unitId") Long unitId, 
			@Param("costPrice") BigDecimal costPrice, 
			@Param("salePrice") BigDecimal salePrice, 
			@Param("status") Byte status
			);
	
	@Query("""
			SELECT p FROM Products p
		    WHERE p.companies.id = :companyId
			""")
	public List<Products> findAllComp(
			@Param("companyId") Long companyId
			);
	
	@Query("""
			SELECT p FROM Products p
		    WHERE p.companies.id = :companyId
			   AND p.id = :id
			""")
	public Products findByIdComp(
			@Param("companyId") Long companyId,
			@Param("id") Long id
			);
	
}
