package com.sunbeam.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.DateReqDto;
import com.sunbeam.dto.OrderReqDto;
import com.sunbeam.dto.OrderResDto;
import com.sunbeam.entities.Orders;

public interface OrderService {

	ResponseEntity<?> addOrder(OrderReqDto orderReqDto);

	List<OrderResDto> getOrderRange(DateReqDto dto);
	
}
