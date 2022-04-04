package com.ss.Catalog.Controller.form;

import java.util.Optional;
import java.util.Random;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ss.Catalog.model.Products;
import com.ss.Catalog.model.Variations;
import com.ss.Catalog.repository.ProductRepository;
import com.ss.Catalog.repository.VariationRepository;

public class variationsForm {

	private Long id;
	@NotNull
	@NotEmpty
	private String color;
	@NotNull
	private char size;
	@NotNull
	private float price;
	@NotNull
	private int quantity;
	@NotNull
	private Long product_id;
	private static Random idR = new Random();

	public variationsForm(String color, char size, float price, int quantity, Long product_id) {
		super();
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

	public Variations converter(ProductRepository productRepository) {
		this.id = -1l;
		while (id <= 0) {
			id = idR.nextLong();
		}
		System.out.println("ID: " + id + "PRA REGISTRAR");

		
		
		return ProductId(productRepository);
		
		
	}

	private Variations ProductId(ProductRepository productRepository) {
		
		Long busca=product_id;
	
		Optional<Products> findById = productRepository.findById(busca);
		
		
		if (findById.isPresent()) {
			Variations variations = new Variations(id, color, size, price, quantity, findById.get().getId());
			findById.get().setVariations(variations);
			productRepository.save(findById.get());
			return variations;
		}
		return null;
	}

	public Variations actualizar(Long id2, VariationRepository variationRepository, ProductRepository productRepository) {
		Optional<Variations> variante = variationRepository.findById(id2);
		if (variante.isPresent()) {
			variante.get().setColor(color);
			variante.get().setPrice(price);
			
			
			Optional<Products> findById = productRepository.findById(product_id);
			if(findById.isPresent()) {
			variante.get().setProduct_id(findById.get().getId());
			}
			
			
			variante.get().setQuantity(quantity);
			variante.get().setSize(size);

			return variante.get();
		}

		return null;
	
	}
	
}
