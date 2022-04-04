package com.ss.Catalog.Controller.form;

import java.util.Optional;
import java.util.Random;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ss.Catalog.model.Categories;
import com.ss.Catalog.repository.CategoriesRepository;

public class CategoriesForm {

	private Long id;
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	private boolean active;
	private static Random idR = new Random();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Categories converter() {
		this.id = -1l;
		while (id <= 0) {
			id = idR.nextLong();
		}
		System.out.println("ID: " + id + "PRA REGISTRAR");

		return new Categories(id, name, active);
	}
	
	public Categories actualizar(Long id2, CategoriesRepository categoriesRepository) {
		Optional<Categories> categories = categoriesRepository.findById(id2);
		if (categories.isPresent()) {
			categories.get().setActive(active);
			categories.get().setName(name);
			return categories.get();
		}

		return null;

	}

}
