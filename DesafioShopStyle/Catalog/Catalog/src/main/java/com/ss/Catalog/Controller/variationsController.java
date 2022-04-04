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

import com.ss.Catalog.Controller.dto.variationsDto;
import com.ss.Catalog.Controller.form.variationsForm;
import com.ss.Catalog.model.Variations;
import com.ss.Catalog.repository.ProductRepository;
import com.ss.Catalog.repository.VariationRepository;

@RestController
@RequestMapping("/v1/variation")
public class variationsController {

	@Autowired
	private VariationRepository variationRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping
	private ResponseEntity<variationsDto> cadastrar(@Valid @RequestBody variationsForm form,
			UriComponentsBuilder uriBuilder) {
		Variations variation = form.converter(productRepository);
		if (variation != null) {
			variationRepository.save(variation);
			return new ResponseEntity<variationsDto>(new variationsDto(variation), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<variationsDto>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public List<variationsDto> ListProducts() {
		List<Variations> findAll = variationRepository.findAll();
		return variationsDto.convertir(findAll);

	}

	@GetMapping("/{id}")
	public ResponseEntity<variationsDto> getid(@PathVariable Long id) {
		Optional<Variations> variante = variationRepository.findById(id);
		if (variante.isPresent()) {
			return new ResponseEntity<variationsDto>(new variationsDto(variante.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<variationsDto>(HttpStatus.NO_CONTENT);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<variationsDto> delete(@PathVariable Long id) {
		Optional<Variations> variante = variationRepository.findById(id);
		if (variante.isPresent()) {
			variationRepository.deleteById(id);
			return new ResponseEntity<variationsDto>(new variationsDto(variante.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<variationsDto>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<variationsDto> Atualizar(@PathVariable Long id, @Valid @RequestBody variationsForm form) {

		Optional<Variations> optional = variationRepository.findById(id);
		if (optional.isPresent()) {
			Variations variante = form.actualizar(id, variationRepository, productRepository);
			variationRepository.save(variante);
			return ResponseEntity.ok(new variationsDto(variante));
		}

		return new ResponseEntity<variationsDto>(HttpStatus.NO_CONTENT);
	}

//
//	}

}
