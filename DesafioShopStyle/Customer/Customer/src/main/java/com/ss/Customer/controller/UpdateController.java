package com.ss.Customer.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.Customer.controller.dto.UserDto;
import com.ss.Customer.controller.form.UserForm;
import com.ss.Customer.model.User;
import com.ss.Customer.repository.UserRepository;

@RestController
@RequestMapping("/v1/users")
public class UpdateController {

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> Atualizar(@PathVariable Long id, @Valid @RequestBody UserForm form) {
		System.out.println(id);

		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = form.actualizar(id, userRepository);
			user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
			return ResponseEntity.ok(new UserDto(user));
		}

		return ResponseEntity.notFound().build();
	}
}
