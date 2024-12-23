package com.sunbeam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "products")
public class Product extends BaseEntity {
	private String name;
	private double price;	
	private int stock;
	private boolean status;
	@JoinColumn(name = "admin_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserE admin;
}
