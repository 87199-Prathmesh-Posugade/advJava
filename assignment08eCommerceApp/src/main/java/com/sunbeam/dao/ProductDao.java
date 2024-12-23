package com.sunbeam.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	List<Product> findByStatusTrue();
	Optional<Product> findByIdAndAdmin_IdAndStatusTrue(Long pId, Long aId);
	Optional<Product> findByIdAndStatusTrue(Long pId);
}
