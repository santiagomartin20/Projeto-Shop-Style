package com.ss.Customer.controller.form;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.Customer.model.Sexo;
import com.ss.Customer.model.User;
import com.ss.Customer.repository.UserRepository;

public class UserForm {
	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	@Size(min = 3, message = "firstName is not valid")
	private String firstName;

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	@Size(min = 3, message = "lastName is not valid")
	private String lastName;

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	private String sexo;

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	@CPF(message = "CPF is not valid")
	private String cpf;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private String birthdate;

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	@Email(message = "Your Email is not valid")
	private String email;

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	@Size(min = 8, message = "password nao valido, debe conter min 8 caracteres")
	private String password;
	
	@NotNull()
	private boolean active;

	

	Date date;

	public UserForm(String firstName, String lastName, String sexo, String cpf, String birthdate, String email,
			String password, boolean active ) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.sexo = sexo;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.email = email;
		this.password = password;
		this.active=active;
	}
	
	

	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Sexo formatar(String sex) {
		Sexo sexo = Sexo.NULL;
		System.out.println(sex);
		if (sex.equalsIgnoreCase("MASCULINO")) {
			sexo = Sexo.MASCULINO;
		} else if (sex.equalsIgnoreCase("Feminino")) {
			sexo = Sexo.FEMININO;
		} else {
			throw new IllegalArgumentException("Valor Invalido");
		}

		return sexo;
	}

	public User converter() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		this.birthdate = this.birthdate.replaceAll("/", "-");
		System.out.println(birthdate);

		try {

			date = formatter.parse(birthdate);
			System.out.println(date + "DATE date");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(this.sexo);
		Sexo sexo = formatar(this.sexo);

		return new User(firstName, lastName, sexo, cpf, date, email, password,active);
	}

	@Override
	public String toString() {
		return "UserForm [firstName=" + firstName + ", lastName=" + lastName + ", sexo=" + sexo + ", cpf=" + cpf
				+ ", birthdate=" + birthdate + ", email=" + email + ", password=" + password + "]";
	}

	public User actualizar(Long id, UserRepository userRepository) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			user.get().setFirstName(this.firstName);
			user.get().setLastName(this.lastName);
			user.get().setPassword(this.password);
			user.get().setEmail(this.email);
			Sexo sex = formatar(this.sexo);
			user.get().setSexo(sex);
			user.get().setActive(active);
			return user.get();
		}
		return null; 
	}

}
