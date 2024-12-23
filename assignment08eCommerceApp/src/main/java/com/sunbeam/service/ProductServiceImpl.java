package com.sunbeam.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ProductReqDto;
import com.sunbeam.dto.ProductResDto;
import com.sunbeam.entities.Product;
import com.sunbeam.entities.UserE;
import com.sunbeam.entities.UserRole;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ResponseEntity<?> addProduct(ProductReqDto pDto) {
		UserE entity = userDao.findById(pDto.getAdminid()).orElseThrow();
		if (entity.getRole() != UserRole.ADMIN) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse("Invalid access"));
		}
		Product pEntity = mapper.map(pDto, Product.class);
		pEntity.setAdmin(entity);
		Product perP = productDao.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse("Added Product with id: " + perP.getId()));
	}

	@Override
	public ResponseEntity<?> deleteProduct(Long pId, Long uId) {
		Product pEntity = productDao.findByIdAndAdmin_IdAndStatusTrue(pId, uId).orElseThrow();
		pEntity.setStatus(false);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted succeccfully"));
	}

	@Override
	public List<ProductResDto> getAllProducts() {
		List<Product> lP = productDao.findByStatusTrue();
		List<ProductResDto> resDto = new ArrayList<>();
		for (Product product : lP) {
			resDto.add(mapper.map(product, ProductResDto.class));
		}
		return resDto;
	}
}
