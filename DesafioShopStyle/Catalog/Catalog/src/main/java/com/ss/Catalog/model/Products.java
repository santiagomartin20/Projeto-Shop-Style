package com.ss.Catalog.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Products {
	
	@Id
	private Long id;

	@NotEmpty
	@NotNull
	private String name;
	@NotNull
	@NotEmpty
	private String description;
	@NotNull
	@NotEmpty
	private boolean active;
	@NotNull
	@NotEmpty
	@DBRef
	private List<Categories> category = new ArrayList<>();
	@DBRef
	private List<Variations> variations = new ArrayList<>();

	


	public Products() {
	}

	public Products(Long id2, String name2, String description2, boolean active2, List<Categories> categorias) {
		this.id=id2;
		this.name = name2;
		this.description = description2;
		this.active = active2;
		this.category =categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Categories> getCategory_ids() {
		return category;
	}

	public void setCategory_ids(List<Categories> category) {
		this.category = category;
	}

	public void setVariations(List<Variations> variations) {
		this.variations = variations;
	}

	public List<Categories> getCategory() {
		return category;
	}

	public void setCategory(List<Categories> category) {
		this.category = category;
	}
	
	public List<Variations> getVariations() {
		return variations;
	}

	public void setVariations(Variations variations) {
		this.variations.add(variations);
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
				+ ", category=" + category + ", variations=" + variations + "]";
	}



}
