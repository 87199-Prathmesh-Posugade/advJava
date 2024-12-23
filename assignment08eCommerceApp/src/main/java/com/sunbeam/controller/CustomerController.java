package com.sunbeam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.OrderReqDto;
import com.sunbeam.dto.ProductResDto;
import com.sunbeam.service.OrderService;
import com.sunbeam.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	

	@GetMapping("/products")
	public ResponseEntity<?> allProducts() {
		List<ProductResDto> l = productService.getAllProducts();
		if (l.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Products not available...!");
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}

	@PostMapping("/order")
	public ResponseEntity<?> placeOrder(@RequestBody OrderReqDto orderReqDto) {
		try {
			return orderService.addOrder(orderReqDto);
		}
		catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("unable to place order...!"));
		}
	}

}
