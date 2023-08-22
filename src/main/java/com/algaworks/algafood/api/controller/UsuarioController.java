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
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> salvar(){
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario){
		
		usuarioService.salvar(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		if(obj.isPresent()) {
			BeanUtils.copyProperties(usuario, obj.get(), "id");
			usuarioService.salvar(obj.get());
			return ResponseEntity.ok(obj.get());
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>delete(@PathVariable Long id){
		Usuario usu = usuarioRepository.findById(id).orElseThrow();
		if (usu != null) {
			
			usuarioService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.badRequest().body(new EntidadeNaoEncontradaException("Não Locallizada"));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id){
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		
		if(obj.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj.get());
		
	}
	
}























