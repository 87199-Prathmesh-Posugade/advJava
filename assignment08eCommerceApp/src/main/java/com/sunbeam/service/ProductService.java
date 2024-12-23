package com.sunbeam.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sunbeam.dto.ProductReqDto;
import com.sunbeam.dto.ProductResDto;

public interface ProductService {

	ResponseEntity<?> addProduct(ProductReqDto pDto);

	ResponseEntity<?> deleteProduct(Long pId, Long uId);

	List<ProductResDto> getAllProducts();

}
