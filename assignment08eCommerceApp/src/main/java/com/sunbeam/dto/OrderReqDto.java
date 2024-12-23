package com.sunbeam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderReqDto {
	private Long customer_id;
	private Long product_id;
	private int quantity;
}
