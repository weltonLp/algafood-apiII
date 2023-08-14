package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RequestMapping("/estados")
@RestController
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService estadoService;
	
	
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?>buscar(@PathVariable Long id){
		Estado estado = estadoRepository.buscar(id);
		if (estado != null) {
			return ResponseEntity.ok(estado);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado){
		try {
			
			Estado novo = estadoRepository.salvar(estado);
			return ResponseEntity.ok(novo);
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>atualizar(@PathVariable Long id ,@RequestBody Estado estado){
		
		Estado novo = estadoRepository.buscar(id);
		
		if(novo != null) {
			BeanUtils.copyProperties(estado, novo, "id");
			novo = estadoService.salvar(novo);
			return ResponseEntity.ok(novo);
		
		}
		
		return ResponseEntity.notFound().build();
		
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		try {
		
			estadoRepository.remover(id);
			return ResponseEntity.noContent().build();
		
		
			
		}catch(EntidadeNaoEncontradaException e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(e.getMessage());
			
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
	}

}




























