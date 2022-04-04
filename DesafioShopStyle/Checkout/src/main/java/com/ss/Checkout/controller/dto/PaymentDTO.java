package com.ss.Checkout.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ss.Checkout.model.Payment;

public class PaymentDTO {

	private Long id;
	private String type;
	private int discount;
	private boolean status;

	public PaymentDTO(Payment payment) {
		this.id = payment.getId();
		this.type = payment.getType();
		this.discount = payment.getDiscount();
		this.status = payment.isStatus();
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getDiscount() {
		return discount;
	}

	public boolean isStatus() {
		return status;
	}

	public static List<PaymentDTO> convertir(List<Payment> findAll) {
		return findAll.stream().map(PaymentDTO::new).collect(Collectors.toList());
	}
}
