package com.ss.Checkout.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.Checkout.controller.dto.InfoPaymentDTO;
import com.ss.Checkout.controller.dto.InfoUsuarioDTO;

@FeignClient("checkout")
public interface CheckoutClient {

	@RequestMapping("v1/payments/{id}")
	InfoPaymentDTO detalhar(@PathVariable Long id);

}

