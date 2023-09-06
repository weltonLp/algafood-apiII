package com.algaworks.algafood.domain.exception;


public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public EstadoNaoEncontradoException(Long id) {
		this(String.format("Estado id %d não foi localizado", id));
		
	}

}
