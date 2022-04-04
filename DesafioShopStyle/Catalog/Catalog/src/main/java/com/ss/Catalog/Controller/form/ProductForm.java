package com.ss.Catalog.Controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ss.Catalog.model.Categories;
import com.ss.Catalog.model.Products;
import com.ss.Catalog.repository.CategoriesRepository;
import com.ss.Catalog.repository.ProductRepository;
import com.ss.Catalog.repository.VariationRepository;

public class ProductForm {

	private Long id;
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String description;
	@NotNull
	@AssertTrue
	private boolean active;
	@NotNull
	@NotEmpty
	private List<Long> category_ids;
	private static Random idR = new Random();

	public ProductForm(String name, String description, boolean active, List<Long> category_ids) {
		this.name = name;
		this.description = description;
		this.active = active;
		this.category_ids = category_ids;
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

	public List<Long> getCategorias() {
		return category_ids;
	}

	public void setCategorias(List<Long> categorias) {
		this.category_ids = categorias;
	}

	public Products converter(CategoriesRepository categoriesRepository, VariationRepository variationRepository) {
		this.id = -1l;
		while (id <= 0) {
			id = idR.nextLong();
		}
		System.out.println("ID: " + id + "PRA REGISTRAR");

		List<Categories> categories = idCategorias(categoriesRepository);

		
		
		
		return new Products(id, name, description, active, categories);
	}

	private List<Categories> idCategorias(CategoriesRepository categoriesRepository) {
		List<Categories> categories = new ArrayList<Categories>();

		for (Long ids : category_ids) {
			Optional<Categories> findById = categoriesRepository.findById(ids);
			if (findById.isPresent() && findById.get().getActive()) {
				categories.add(findById.get());
			}

		}
		return categories;
	}

	public Products actualizar(Long id2, ProductRepository productRepository,
			CategoriesRepository categoriesRepository) {

		Optional<Products> product = productRepository.findById(id2);
		if (product.isPresent()) {
			product.get().setName(this.name);
			product.get().setDescription(this.description);
			product.get().setActive(this.active);

			List<Categories> categories = idCategorias(categoriesRepository);

			product.get().setCategory_ids(categories);

			return product.get();
		}

		return null;
	}



}
