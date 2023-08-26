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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	
	@GetMapping
	public List<Cozinha>listar (){
		return cozinhaRepository.findAll();
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		
		if(cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get()); 
		}else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {
		try {
			Cozinha coz = cozinhaService.salvar(cozinha);
			return ResponseEntity.status(HttpStatus.CREATED).body(coz);
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){

		
		if(!cozinhaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cozinha.setId(id);
		cozinhaService.salvar(cozinha);
		return ResponseEntity.ok(cozinha);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
		
			cozinhaService.excluir(id);
			return ResponseEntity.noContent().build();
			
			
		}catch(EntidadeNaoEncontradaException e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(e.getMessage());
			
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
}










