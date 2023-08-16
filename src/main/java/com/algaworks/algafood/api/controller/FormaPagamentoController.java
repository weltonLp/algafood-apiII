package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

@RequestMapping("/formaPagamentos")
@RestController
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository; 
	
	@Autowired
	CadastroFormaPagamentoService formaPagamentoService;
	
	
	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long id){
		FormaPagamento formPg = formaPagamentoRepository.findById(id).orElse(null);
		if(formPg == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(formPg);
	}


	@PostMapping
	public ResponseEntity<FormaPagamento> inserir(@RequestBody FormaPagamento formaPagamento){
		formaPagamentoService.salvar(formaPagamento);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long id, 
			@RequestBody FormaPagamento forma){

		Optional<FormaPagamento> fp = formaPagamentoRepository.findById(id);
		
		if(fp.isEmpty()) {
			
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(forma, fp.get(), "id");
		
		formaPagamentoService.salvar(fp.get());
		
		return ResponseEntity.ok(fp.get());
		
		
	}
}


































