package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;



@RequestMapping("/grupos")
@RestController
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	
	@GetMapping
	public List<Grupo>listar(){
		return grupoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Grupo> buscar(@PathVariable Long id){
		Optional<Grupo> g = grupoRepository.findById(id);
		
		if(g.isPresent()) {
			
			return ResponseEntity.ok(g.get()); 
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody Grupo grupo){
		try {
			cadastroGrupoService.salvar(grupo);
			
			return ResponseEntity.ok(grupo);
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizer(@PathVariable Long id, @RequestBody Grupo group){
		try {
			Optional<Grupo> g = grupoRepository.findById(id);
			
			if(g.isPresent()) {
				BeanUtils.copyProperties(group, g.get(), "id");
				cadastroGrupoService.salvar(g.get());
				return ResponseEntity.ok(g.get());
			}
			
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Grupo> deletar(@PathVariable Long id) {
		Optional<Grupo> obj = grupoRepository.findById(id);
		
		if(obj.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroGrupoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}






