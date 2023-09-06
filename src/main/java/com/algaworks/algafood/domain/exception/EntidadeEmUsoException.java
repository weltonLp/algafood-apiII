package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Entidade está em uso e não poderá ser removida")
public class EntidadeEmUsoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}

	

}
