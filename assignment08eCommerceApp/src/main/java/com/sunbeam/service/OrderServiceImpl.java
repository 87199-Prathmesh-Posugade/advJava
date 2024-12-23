package com.sunbeam.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sunbeam.dao.OrderDao;
import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.DateReqDto;
import com.sunbeam.dto.OrderReqDto;
import com.sunbeam.dto.OrderResDto;
import com.sunbeam.entities.Orders;
import com.sunbeam.entities.Product;
import com.sunbeam.entities.UserE;
import com.sunbeam.entities.UserRole;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> addOrder(OrderReqDto orderReqDto) {
		UserE u = userDao.findById(orderReqDto.getCustomer_id()).orElseThrow();
		Product p = productDao.findByIdAndStatusTrue(orderReqDto.getProduct_id()).orElseThrow();
		if (u.getRole() != UserRole.CUSTOMER) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(new ApiResponse("Only customer can add product...!"));
		}
		if (p.getStock() >= orderReqDto.getQuantity()) {
			p.setStock(p.getStock() - orderReqDto.getQuantity());
			Orders o = orderDao.save(new Orders(u, p, orderReqDto.getQuantity()));
			o.setSubTotal(p.getPrice() * o.getQuantity());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse("Order placed by id: " + o.getId() + " amount: " + o.getSubTotal()));
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse("Stock not available...!"));
	}

	@Override
	public List<OrderResDto> getOrderRange(DateReqDto dto) {
		List<Orders> l = orderDao.fetchOrderBetweeen(LocalDate.parse(dto.getStartDate()),
				LocalDate.parse(dto.getEndDate()));
		List<OrderResDto> r = new ArrayList<>();
		for (Orders o : l) {
			OrderResDto obj = modelMapper.map(o, OrderResDto.class);
//			r.add(new OrderResDto(o.getCustomer().getId(), o.getProduct().getId(), o.getQuantity(), o.getSubTotal()));
			obj.setCustomer_id(o.getCustomer().getId());
			obj.setProduct_id(o.getProduct().getId());
			r.add(obj);
		}
		if(r.isEmpty()) {
			throw new RuntimeException("No orders placed within this range...!");
		}
		return r;
	}

}
