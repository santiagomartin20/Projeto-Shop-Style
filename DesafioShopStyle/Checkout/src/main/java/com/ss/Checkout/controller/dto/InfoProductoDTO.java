package com.ss.Checkout.controller.dto;

public class InfoProductoDTO {
	private  boolean active;

	public boolean isActiveProducto() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "InfoProductoDTO [active=" + active + "]";
	}
	
	

}
