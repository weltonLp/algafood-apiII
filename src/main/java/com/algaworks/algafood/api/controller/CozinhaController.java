package com.algaworks.algafood.api.controller;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha>listar (){
		return cozinhaRepository.listar();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public CozinhasXmlWrapper listarXML (){
		return new CozinhasXmlWrapper(cozinhaRepository.listar());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		if(cozinha != null) {
			
			return ResponseEntity.ok(cozinha); 
		}else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Cozinha cozinha) {
		cozinhaRepository.salvar(cozinha);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
		Cozinha novo = cozinhaRepository.buscar(id);
//		novo.setId(cozinha.getId());
//		novo.setNome(cozinha.getNome());
		if(novo != null) {
			BeanUtils.copyProperties(cozinha, novo, "id");
			cozinhaRepository.salvar(novo);			
			return ResponseEntity.ok(novo);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> deletar(@PathVariable Long id) {
		try {
			Cozinha remove = cozinhaRepository.buscar(id);
			if(remove != null) {
				cozinhaRepository.remover(remove);			
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}










