package com.ss.Customer.controller.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.Customer.model.User;




public class UserDto {

	private Long id;
	private String FirstName;
	private String LastName;
	private String sexo;
	private String birthdate;
	private String Email;
	private boolean activeUsuario;

	public UserDto(User user) {
		this.id = user.getId();
		this.FirstName = user.getFirstName();
		this.LastName = user.getLastName();
		this.sexo = user.getSexo().getName();
		Date dataIso = user.getBirthdate();
		this.birthdate = new SimpleDateFormat("dd-MM-yyyy").format(dataIso);
		this.Email = user.getEmail();
		this.activeUsuario=user.getActive();
	}

	
	
	
	public boolean isActiveUsuario() {
		return activeUsuario;
	}




	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() { 
		return LastName;
	}

	public String getSexo() {
		return sexo;
	}

	public String getbirthdate() {
		return birthdate;
	}

	public String getEmail() {
		return Email;
	}

	public static List<UserDto> convertir(List<User> user) {
		return user.stream().map(UserDto::new).collect(Collectors.toList());
	}
}