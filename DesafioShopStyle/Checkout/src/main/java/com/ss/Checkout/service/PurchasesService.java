package com.ss.Checkout.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ss.Checkout.client.CatalogClient;
import com.ss.Checkout.client.CheckoutClient;
import com.ss.Checkout.client.CustomerClient;
import com.ss.Checkout.controller.dto.InfoPaymentDTO;
import com.ss.Checkout.controller.dto.InfoPedidoDTO;
import com.ss.Checkout.controller.dto.InfoProductoDTO;
import com.ss.Checkout.controller.dto.InfoUsuarioDTO;
import com.ss.Checkout.controller.form.CartForm;
import com.ss.Checkout.controller.form.PurchasesForm;

@Service
public class PurchasesService {

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private CheckoutClient checkoutClient;

	@Autowired
	private CatalogClient catalogClient;
	
	
	@HystrixCommand(fallbackMethod = "postPurchasesFallback", threadPoolKey = "realizaCompraThreadPool")
	public InfoPedidoDTO realizaCompra(PurchasesForm form) {

		InfoPedidoDTO detalharCompra = new InfoPedidoDTO();

		InfoUsuarioDTO detalharUsuario = customerClient.detalhar(form.getUser_id());
		
		System.out.println("ID Payment :"+form.getPayment_id());

		InfoPaymentDTO datalharPago = checkoutClient.detalhar( form.getPayment_id());
					
		System.out.println("STATUS DE USER:  " + detalharUsuario.isActiveUsuario());

		System.out.println("STATUS DE PAYMENT: " + datalharPago.isStatus());


		
		for (CartForm productos : form.getCart()) {
			System.out.println("ID Da Variante: " +productos.getVariant_id());
			InfoProductoDTO datalharProducto = catalogClient.detalhar(productos.getVariant_id());
			System.out.println(datalharProducto.toString());
			detalharCompra.getActiveProducto().add(datalharProducto.isActiveProducto());
			
		}

		detalharCompra.setActiveFormaDePago(datalharPago.isStatus());
		detalharCompra.setActiveUsuario(detalharUsuario.isActiveUsuario());
	
		return detalharCompra;

	}

	
	public InfoPedidoDTO postPurchasesFallback(PurchasesForm form) {
		InfoPedidoDTO realizaCompraFallback = new InfoPedidoDTO();
		realizaCompraFallback.setActiveFormaDePago(false);
		realizaCompraFallback.setActiveUsuario(false);
		realizaCompraFallback.setActiveProducto(new ArrayList<>());
		return realizaCompraFallback;
		}
}
