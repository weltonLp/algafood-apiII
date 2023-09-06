package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	

	public EntidadeNaoEncontradaException(String reason) {
		super(reason);
		
	}

	
}
