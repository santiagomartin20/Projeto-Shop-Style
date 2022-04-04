package com.ss.Catalog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "variations")
public class Variations {

	@Id
	private Long id;
	private String color;
	private char size;
	private float price;
	private int quantity;
	private Long product_id;

	public Variations(Long id, String color, char size, float price, int quantity, Long product_id) {
		this.id = id;
		this.color = color;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
		this.product_id = product_id;
	}



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

}
