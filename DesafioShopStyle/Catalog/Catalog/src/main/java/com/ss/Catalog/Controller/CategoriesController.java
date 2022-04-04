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

import com.ss.Catalog.Controller.dto.CategoriesDto;
import com.ss.Catalog.Controller.form.CategoriesForm;
import com.ss.Catalog.model.Categories;
import com.ss.Catalog.repository.CategoriesRepository;

@RestController
@RequestMapping("/v1/categories")
public class CategoriesController {

	@Autowired
	private CategoriesRepository categoriesRepository;

	@PostMapping
	private ResponseEntity<CategoriesDto> cadastrar(@Valid @RequestBody CategoriesForm form,
			UriComponentsBuilder uriBuilder) {
		Categories categories = form.converter();
		categoriesRepository.save(categories);
		return new ResponseEntity<CategoriesDto>(new CategoriesDto(categories), HttpStatus.CREATED);

	}

	@GetMapping
	public List<CategoriesDto> ListProducts() {
		List<Categories> findAll = categoriesRepository.findAll();
		return CategoriesDto.convertir(findAll);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriesDto> getid(@PathVariable Long id) {
		Optional<Categories> categories = categoriesRepository.findById(id);
		if (categories.isPresent()) {
			return new ResponseEntity<CategoriesDto>(new CategoriesDto(categories.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<CategoriesDto>(HttpStatus.NO_CONTENT);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoriesDto> delete(@PathVariable Long id) {
		Optional<Categories> categories = categoriesRepository.findById(id);
		if (categories.isPresent()) {
			categoriesRepository.deleteById(id);
			return new ResponseEntity<CategoriesDto>(new CategoriesDto(categories.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<CategoriesDto>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriesDto> Atualizar(@PathVariable Long id, @Valid @RequestBody CategoriesForm form) {

		Optional<Categories> categories = categoriesRepository.findById(id);
		if (categories.isPresent()) {
			Categories categorias = form.actualizar(id, categoriesRepository);
			categoriesRepository.save(categorias);
			return ResponseEntity.ok(new CategoriesDto(categorias));
		}

		return new ResponseEntity<CategoriesDto>(HttpStatus.NO_CONTENT);
	}

}
