package com.ss.Checkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.Checkout.controller.dto.InfoPedidoDTO;
import com.ss.Checkout.controller.form.PurchasesForm;
import com.ss.Checkout.service.PurchasesService;

@RestController
@RequestMapping("/v1/purchases")
public class PurchasesController {

	@Autowired
	PurchasesService purchasesService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<InfoPedidoDTO> postPurchases(@RequestBody PurchasesForm form) {
		System.out.println("IdUsuario: " + form.getUser_id());
		InfoPedidoDTO realizaCompra = purchasesService.realizaCompra(form);
		if (realizaCompra != null) {
			return ResponseEntity.ok().body(realizaCompra);
		}
		return ResponseEntity.notFound().build();

	}
	
	
}
