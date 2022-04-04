package com.ss.Checkout.controller;

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

import com.ss.Checkout.controller.dto.PaymentDTO;
import com.ss.Checkout.controller.form.PaymentForm;
import com.ss.Checkout.model.Payment;
import com.ss.Checkout.repository.PaymentRepository;



@RestController
@RequestMapping("/v1/payments")
public class paymentController {
	
	@Autowired
	PaymentRepository paymentRepository;

	@PostMapping
	public ResponseEntity<PaymentDTO> save(@RequestBody @Valid PaymentForm form){
		Payment payment = form.converter();
		paymentRepository.save(payment);
		return new ResponseEntity<PaymentDTO>(new PaymentDTO(payment), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PaymentDTO> ListPatment() {
		List<Payment> findAll = paymentRepository.findAll();
		return PaymentDTO.convertir(findAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDTO> getid(@PathVariable Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			return new ResponseEntity<PaymentDTO>(new PaymentDTO(payment.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<PaymentDTO>(HttpStatus.NO_CONTENT);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PaymentDTO> delete(@PathVariable Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			paymentRepository.deleteById(id);
			return new ResponseEntity<PaymentDTO>(new PaymentDTO(payment.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<PaymentDTO>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<PaymentDTO> Atualizar(@PathVariable Long id, @Valid @RequestBody PaymentForm form) {

		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			Payment payments = form.actualizar(payment.get());
			paymentRepository.save(payments);
			return ResponseEntity.ok(new PaymentDTO(payments));
		}

		return new ResponseEntity<PaymentDTO>(HttpStatus.NO_CONTENT);
	}

}


