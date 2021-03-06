package com.ss.Checkout.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.Checkout.controller.dto.InfoProductoDTO;

@FeignClient("catalog")
public interface CatalogClient {

	@RequestMapping("/v1/products/{id}")
	InfoProductoDTO detalhar(@PathVariable Long id);
	
}
