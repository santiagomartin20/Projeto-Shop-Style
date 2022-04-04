package com.ss.Customer.controller;


import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ss.Customer.controller.dto.UserDto;
import com.ss.Customer.controller.form.UserForm;
import com.ss.Customer.model.User;
import com.ss.Customer.repository.UserRepository;




@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping
	private ResponseEntity<UserDto> cadastrar(@Valid @RequestBody UserForm form, UriComponentsBuilder uriBuilder) {
		System.out.println(form.toString());
		Optional<User> optional = userRepository.findByEmail(form.getEmail());
		if(optional.isPresent()) {
			return ResponseEntity.badRequest().build();		}
		else {
		User user = form.converter();
		user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
		System.out.println(user.toString());
		userRepository.save(user);
		URI uri = uriBuilder.path("/User/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> detalhar(@PathVariable Long id) {

		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new UserDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	
}
