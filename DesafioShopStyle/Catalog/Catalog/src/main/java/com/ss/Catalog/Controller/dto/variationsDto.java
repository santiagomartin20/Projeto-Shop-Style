package com.ss.Catalog.Controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ss.Catalog.model.Variations;

public class variationsDto {

	private String color;
	private char size;
	private float price;
	private int quantity;
	private Long product_id;

	public variationsDto(Variations variations) {
		this.color = variations.getColor();
		this.size = variations.getSize();
		this.price = variations.getPrice();
		this.quantity = variations.getQuantity();
		this.product_id = variations.getProduct_id();
	}

	public String getColor() {
		return color;
	}

	public char getSize() {
		return size;
	}

	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Long getProduct_id() {
		return product_id;
	}

	
	public static List<variationsDto> convertir(List<Variations> variantes) {
		return variantes.stream().map(variationsDto::new).collect(Collectors.toList());
	}


}
