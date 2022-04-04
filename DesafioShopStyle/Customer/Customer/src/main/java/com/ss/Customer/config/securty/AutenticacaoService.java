package com.ss.Customer.config.securty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ss.Customer.model.User;
import com.ss.Customer.repository.UserRepository;








@Service
public class AutenticacaoService  implements UserDetailsService{
	
	@Autowired
	private UserRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usuario = usuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return (UserDetails) usuario.get();
		}
		throw new UsernameNotFoundException("Dados ivalidos");
	}

}
