package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "ENTIDADE NÃP ENCONTRADA"),
	ENTIDADE_EM_USO("/entidade-em-uso", "ENTIDADE USADA"),
	USO("/problemas-uso", "PROBLEMAS NO NEGOCIO");
	
	private String titulo;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://algafood.com.br/" + path;
		this.titulo = titulo;
	}
	
	
}
