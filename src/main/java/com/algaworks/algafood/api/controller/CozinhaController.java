package com.algaworks.algafood.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public Cozinha buscar(@PathVariable Long id) {
		
		return cozinhaService.buscarOuFalhar(id);
		
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {
//		try {
			Cozinha coz = cozinhaService.salvar(cozinha);
			return ResponseEntity.status(HttpStatus.CREATED).body(coz);
//			
//		}catch(EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//			
//		}
	}
	
	
	@PutMapping("/{id}")
	public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){

		Cozinha nova = cozinhaService.buscarOuFalhar(id);
		
		cozinha.setId(id);
		
		return cozinhaService.salvar(cozinha);
		
	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		
		cozinhaService.excluir(id);
			
	}
	
	
	
}










