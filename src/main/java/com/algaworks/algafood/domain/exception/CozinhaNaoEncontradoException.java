package com.algaworks.algafood.domain.exception;


public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public CozinhaNaoEncontradoException(Long id) {
		this(String.format("Cozinha id %d não foi localizado", id));
		
	}

}
