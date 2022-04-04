package com.ss.Checkout.controller.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ss.Checkout.model.Payment;

public class PaymentForm {

	@NotNull
	@NotEmpty
	private String type;
	@NotNull
	private int discount;
	@NotNull
	@AssertTrue
	private boolean status;

	public Payment converter() {
		return new Payment(type, discount, status);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	public Payment actualizar(Payment payment) {
		payment.setType(type);
		payment.setDiscount(discount);
		payment.setStatus(status);
		return payment;
	}

}
