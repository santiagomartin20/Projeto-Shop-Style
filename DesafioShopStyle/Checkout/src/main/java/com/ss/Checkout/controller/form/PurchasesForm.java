package com.ss.Checkout.controller.form;

import java.util.List;

public class PurchasesForm {

	private Long id;
	private Long payment_id;
	private List<CartForm> cart;
	
	

	public Long getUser_id() {
		return id;
	}

	public void setUser_id(Long id) {
		this.id = id;
	}

	public Long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Long payment_id) {
		this.payment_id = payment_id;
	}

	public List<CartForm> getCart() {
		return cart;
	}

	public void setCart(List<CartForm> cart) {
		this.cart = cart;
	}

}
