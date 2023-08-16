package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;

@RequestMapping("/permissoes")
@RestController
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private CadastroPermissaoService permissaoService;
	
	
	@GetMapping
	public List<Permissao> listar(){
		return permissaoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Permissao> buscar(@PathVariable Long id){
		
		Optional<Permissao> obj = permissaoRepository.findById(id);
		if(obj.isEmpty()) {
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não existe permissão de id %d", id));
		return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj.get());
	}
	
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Permissao permissao){
		try {
			Permissao per = permissaoService.salvar(permissao);
			return ResponseEntity.status(HttpStatus.CREATED).body(per);			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Permissao>atualizar(@PathVariable Long id, @RequestBody Permissao permissao){
		Optional<Permissao> obj = permissaoRepository.findById(id);
		
		if (obj.isEmpty()) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		BeanUtils.copyProperties(permissao, obj.get(), "id");
		Permissao salvo = permissaoService.salvar(obj.get()); 
		
		return ResponseEntity.ok(obj.get());
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		
		Optional<Permissao> obj = permissaoRepository.findById(id);
		if (obj.isPresent()) {
			permissaoService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}





















