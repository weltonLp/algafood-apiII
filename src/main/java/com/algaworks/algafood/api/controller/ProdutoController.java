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
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable Long id){
		Produto p = produtoRepository.findById(id).orElse(null);
		
		if(p != null) {
			
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Produto produto){

		try {
			cadastroProdutoService.salvar(produto);
			return ResponseEntity.status(HttpStatus.CREATED).body(produto);
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizer(@PathVariable Long id, @RequestBody Produto produto){
		try {
			
			Optional<Produto> p = produtoRepository.findById(id);
			
			if(p.isPresent()) {
				BeanUtils.copyProperties(produto, p.get(), "id");
				cadastroProdutoService.salvar(produto);
				return ResponseEntity.ok(p.get());
			}
			return ResponseEntity.notFound().build();
			
			
		}catch( EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		
		Optional<Produto> p = produtoRepository.findById(id);
		
		if(p.isPresent()) {
			cadastroProdutoService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
}










