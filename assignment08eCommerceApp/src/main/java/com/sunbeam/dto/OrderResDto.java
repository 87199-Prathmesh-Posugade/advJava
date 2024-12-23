package com.sunbeam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.sunbeam.entities.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResDto extends BaseEntity {
	private Long customer_id;
	private Long product_id;
	private int quantity;
	private double subTotal;
}

