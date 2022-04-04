package com.ss.Catalog.Controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ss.Catalog.model.Categories;

public class CategoriesDto {

	private String name;
	private boolean active;

	public CategoriesDto(Categories categories) {
		this.name = categories.getName();
		this.active = categories.getActive();
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public static List<CategoriesDto> convertir(List<Categories> findAll) {
		return findAll.stream().map(CategoriesDto::new).collect(Collectors.toList());
	}
}
