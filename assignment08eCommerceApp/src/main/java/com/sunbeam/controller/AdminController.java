package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.DateReqDto;
import com.sunbeam.dto.ProductReqDto;
import com.sunbeam.service.OrderService;
import com.sunbeam.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductService pServ;

	@Autowired
	private OrderService oServ;

	@PostMapping("/addproduct")
	public ResponseEntity<?> postMethodName(@RequestBody ProductReqDto pDto) {
		try {
			return pServ.addProduct(pDto);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteproduct/{pId}/{uId}")
	public ResponseEntity<?> postMethodName(@PathVariable Long pId, @PathVariable Long uId) {
		try {
			ResponseEntity<?> ref = pServ.deleteProduct(pId, uId);
			return ref;
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping("/orders")
	public ResponseEntity<?> postMethod(@RequestBody DateReqDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(oServ.getOrderRange(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		}
	}

}
