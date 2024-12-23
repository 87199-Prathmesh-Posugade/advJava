package com.sunbeam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ProductReqDto {
	private String name;
	private double price;
	private int stock;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean status = true;
	private Long adminid;
}
