package com.ss.Catalog.configValidacion;

public class ErroDeFormularioDto {
	private String campo;
	private String erro;

	public ErroDeFormularioDto(String field, String mensagem) {
		// TODO Auto-generated constructor stub
		this.campo = field;
		this.erro = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
