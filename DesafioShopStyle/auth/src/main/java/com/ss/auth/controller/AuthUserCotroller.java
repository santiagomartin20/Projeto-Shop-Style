package com.ss.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.auth.controller.dto.AuthUserDto;
import com.ss.auth.controller.dto.TokenDto;
import com.ss.auth.model.AuthUser;
import com.ss.auth.service.AuthUserService;

@RestController
@RequestMapping("/auth")
public class AuthUserCotroller {
	
	@Autowired
	AuthUserService authUserService;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login (@RequestBody AuthUserDto dto){
		TokenDto tokenDto = authUserService.login(dto);
		if(tokenDto == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(tokenDto);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<TokenDto> validate (@RequestParam String token){
		System.out.println("VALIDANDO");
		TokenDto tokenDto = authUserService.Validate(token);
		System.out.println(tokenDto);
		if(tokenDto == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(tokenDto);
	}	
	
	@PostMapping("/create")
	public ResponseEntity<AuthUser> create (@RequestBody AuthUserDto dto){
		
		AuthUser authUser = authUserService.save(dto);
		if(authUser == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(authUser);
		
	}
	

}
