package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
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
		return estadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Estado buscar(@PathVariable Long id){
		return estadoService.buscarOuFalhar(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado){
		try {
			
			Estado novo = estadoService.salvar(estado);
			return ResponseEntity.ok(novo);
			
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id ,@RequestBody Estado estado){
		
		Estado novo = estadoService.buscarOuFalhar(id);
		
		estado.setId(id);
		try {
			return estadoService.salvar(estado);
		}catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id){
			
		estadoService.excluir(id);
		
	}

}




























