
package com.ss.Checkout.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.Checkout.controller.dto.InfoPedidoDTO;
import com.ss.Checkout.controller.dto.InfoUsuarioDTO;

@FeignClient("customer")
public interface CustomerClient {
	
	@RequestMapping("/v1/users/{id}")
	InfoUsuarioDTO detalhar(@PathVariable Long id);


}
