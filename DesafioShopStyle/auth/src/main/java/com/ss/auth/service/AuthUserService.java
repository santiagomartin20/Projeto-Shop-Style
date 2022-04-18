package com.ss.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ss.auth.controller.dto.AuthUserDto;
import com.ss.auth.controller.dto.TokenDto;
import com.ss.auth.model.AuthUser;
import com.ss.auth.repository.AuthUserRepository;
import com.ss.auth.security.JwtProvider;

@Service
public class AuthUserService {
	
	@Autowired
	AuthUserRepository authUserRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtProvider jwtProvider;
	
	public AuthUser save(AuthUserDto dto) {
		Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
		if(user.isPresent()) {
			return null;}
		String password = passwordEncoder.encode(dto.getPassword());
		AuthUser authUser = new AuthUser(dto.getUserName(), password); 
		return authUserRepository.save(authUser);
	}
	
	public TokenDto login (AuthUserDto dto) {
		Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
		if(!user.isPresent()) {
			return null;}
		if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
			return new TokenDto(jwtProvider.createToken(user.get()));
		}
		return null;
	}
	
	public TokenDto  Validate(String token) {
		if (!jwtProvider.validate(token)) {
			return null;
		}
		String username = jwtProvider.getUserNameFromToken(token);
		if(!authUserRepository.findByUserName(username).isPresent()) {
			return null;
		}
		return new TokenDto(token);
	}
}
