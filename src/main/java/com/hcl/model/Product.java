package com.hcl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long prdId;
	@Column(name = "prdname")
	private String prdName;
	@Column(name = "prdprice")
	private double prdPrice;
	@Column (name = "prdquantity")
	private int prdQuantity;
	
	public double calculateTotal() {
		return prdPrice * prdQuantity;
	}
	
}
