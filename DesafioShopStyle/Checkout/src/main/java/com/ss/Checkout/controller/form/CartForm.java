package com.ss.Checkout.controller.form;

public class CartForm {

	private Long variant_id;
	private int quantity;

	public Long getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(Long variant_id) {
		this.variant_id = variant_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
