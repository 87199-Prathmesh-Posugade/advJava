package com.sunbeam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Orders extends BaseEntity {
//	customer id , product id  , quantity
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private UserE customer;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;

	@Column(name = "sub_total")
	private double subTotal;
	
	public Orders(UserE customer, Product product, int quantity) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}
	
}
