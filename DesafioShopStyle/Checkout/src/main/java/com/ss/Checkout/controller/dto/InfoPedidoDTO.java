package com.ss.Checkout.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class InfoPedidoDTO {

	private  boolean activeUsuario;
	private  boolean activeFormaDePago;
	private  List< Boolean> activeProducto = new ArrayList<Boolean>() ;
	
	
	
	public boolean isActiveUsuario() {
		return activeUsuario;
	}
	public void setActiveUsuario(boolean activeUsuario) {
		this.activeUsuario= activeUsuario;
	}
	public boolean isActiveFormaDePago() {
		return activeFormaDePago;
	}
	public void setActiveFormaDePago(boolean activeFormaDePago) {
		this.activeFormaDePago = activeFormaDePago;
	}
	public List<Boolean> getActiveProducto() {
		return activeProducto;
	}
	public void setActiveProducto(List<Boolean> activeProducto) {
		this.activeProducto = activeProducto;
	}

	
	
	
	
	


	
	
}
