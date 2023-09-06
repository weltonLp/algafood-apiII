package com.algaworks.algafood.domain.exception;


public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public CidadeNaoEncontradoException(Long id) {
		this(String.format("Cidade id %d não foi localizado", id));
		
	}

}
