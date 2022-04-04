package com.ss.Customer.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	@NotEmpty(message = "Email Empty") 
	@NotNull(message = "Email Null")
	@Email(message = "Your Email is not valid")
	private String email;
	
	@NotEmpty(message = "password Empty") 
	@NotNull(message = "password Null")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken coverter() {
		
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
