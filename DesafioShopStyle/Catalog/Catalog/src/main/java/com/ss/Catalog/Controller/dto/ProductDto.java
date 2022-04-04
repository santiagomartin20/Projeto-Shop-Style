package com.ss.Catalog.Controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.Catalog.model.Categories;
import com.ss.Catalog.model.Products;
import com.ss.Catalog.model.Variations;

public class ProductDto {
	private String name;
	private String description;
	private boolean active;
	private List<Categories> categories= new ArrayList<>();
	private List<Variations> variations = new ArrayList<>();

	public ProductDto(Products porduct) {
		this.variations= porduct.getVariations();
		this.name =porduct.getName();
		this.description= porduct.getDescription();
		this.active=porduct.getActive();
		this.categories= porduct.getCategory_ids();
		this.variations= porduct.getVariations();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Categories> getCategory_ids() {
		return categories;
	}

	public void setCategory_ids(List<Categories> category_ids) {
		this.categories = category_ids;
	}
	
	

	public List<Variations> getVariations() {
		return variations;
	}

	public void setVariations(List<Variations> variations) {
		this.variations = variations;
	}

	public static List<ProductDto> convertir(List<Products> productos) {
		return productos.stream().map(ProductDto::new).collect(Collectors.toList());
	}
}
