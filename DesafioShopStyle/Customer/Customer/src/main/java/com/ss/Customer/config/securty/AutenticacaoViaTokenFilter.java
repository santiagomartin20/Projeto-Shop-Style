package com.ss.Customer.config.securty;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ss.Customer.model.User;
import com.ss.Customer.repository.UserRepository;




	public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private GeraTokenService tokenService;

	private UserRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(GeraTokenService tokenService, UserRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);

		boolean valido = false;

		valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {

		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<User> usuario = usuarioRepository.findById(idUsuario);
		if (usuario.isPresent()) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.get().getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			System.out.println("Error de Autenticacion");
		}

	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;

		}
		return token.substring(7, token.length());
	}

}
