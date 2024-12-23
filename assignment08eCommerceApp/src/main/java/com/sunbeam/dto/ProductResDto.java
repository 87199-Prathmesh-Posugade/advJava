package com.sunbeam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResDto extends BaseDto {
	private String name;
	private double price;
	private int stock;
}
