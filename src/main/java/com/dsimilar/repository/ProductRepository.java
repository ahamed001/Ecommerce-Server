package com.dsimilar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsimilar.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p " +
            "WHERE (p.category.name = :category OR :category = '') " +
            "AND (:minPrice IS NULL OR :maxPrice IS NULL OR p.discountPrice BETWEEN :minPrice AND :maxPrice) " +
            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountPrice ELSE 0 END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountPrice ELSE 0 END DESC")
	
	public List<Product>filterProducts(@Param("category") String category,
			@Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice,
			@Param("minDiscount") Integer minDiscount,
			@Param("sort") String sort);
	
}
