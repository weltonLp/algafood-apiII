package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException( EntidadeEmUsoException e, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType type = ProblemType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		Problema problema = createProblemaBuilder(status, type, detail).build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleTratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException e, WebRequest request){

		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ProblemType type = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = e.getMessage();
		
		Problema problema = createProblemaBuilder(status, type, detail).build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
		

	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleTratarNegocioException(
			NegocioException e, WebRequest request){

		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ProblemType type = ProblemType.USO;
		String detail = e.getMessage();
		
		Problema problema = createProblemaBuilder(status, type, detail).build();
		
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problema.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.build();
		}else if(body instanceof String) {
			body = Problema.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problema.ProblemaBuilder createProblemaBuilder(HttpStatus status,
			ProblemType type, String detail){
		
		
		return Problema.builder()
				.status(status.value())
				.type(type.getUri())
				.title(type.getTitulo())
				.detail(detail);
	}
	
}














