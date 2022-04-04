package com.ss.Catalog.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ss.Catalog.Controller.dto.ProductDto;
import com.ss.Catalog.Controller.form.ProductForm;
import com.ss.Catalog.model.Products;
import com.ss.Catalog.repository.CategoriesRepository;
import com.ss.Catalog.repository.ProductRepository;
import com.ss.Catalog.repository.VariationRepository;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private VariationRepository variationRepository;

	@PostMapping
	private ResponseEntity<ProductDto> cadastrar(@Valid @RequestBody ProductForm form,
			UriComponentsBuilder uriBuilder) {
		Products porduct = form.converter(categoriesRepository,variationRepository);
		if (porduct.getCategory_ids().isEmpty()) {
			return new ResponseEntity<ProductDto>(HttpStatus.NO_CONTENT);
		} else {
			productRepository.save(porduct);
			return new ResponseEntity<ProductDto>(new ProductDto(porduct), HttpStatus.CREATED);

		}
	}

	@GetMapping
	public List<ProductDto> ListProducts() {
		List<Products> findAll = productRepository.findAll();
		return ProductDto.convertir(findAll);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getid(@PathVariable Long id) {
		Optional<Products> products = productRepository.findById(id);
		if (products.isPresent()) {
			return new ResponseEntity<ProductDto>(new ProductDto(products.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDto>(HttpStatus.NO_CONTENT);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
		Optional<Products> products = productRepository.findById(id);
		if (products.isPresent()) {
			productRepository.deleteById(id);
			return new ResponseEntity<ProductDto>(new ProductDto(products.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDto>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> Atualizar(@PathVariable Long id, @Valid @RequestBody ProductForm form)  {

		Optional<Products> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Products products = form.actualizar(id, productRepository, categoriesRepository);
			productRepository.save(products);
			return ResponseEntity.ok(new ProductDto(products));
		}

		return new ResponseEntity<ProductDto>(HttpStatus.NO_CONTENT);
	}

}
