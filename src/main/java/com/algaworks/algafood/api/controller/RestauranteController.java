package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/restaurantes")
@RestController
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
		
		Restaurante rest = restauranteRepository.buscar(id);
		
		if(rest != null) {
			return ResponseEntity.ok(rest);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		try {
			
			Restaurante res = restauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){

		try {
			Restaurante novo = restauranteRepository.buscar(id);
			
			
			if(novo != null) {
				
				BeanUtils.copyProperties(restaurante, novo, "id");			
				novo = restauranteService.salvar(novo);
				
				return ResponseEntity.ok(novo);
			}
			
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
			
	
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
		try {
			Restaurante res = restauranteRepository.buscar(id);
			if(res != null) {
				
				restauranteRepository.remover(id);
				return ResponseEntity.ok(res);
			}
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().build();
		}
	
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?>atualizarParcial(@PathVariable Long id, 
			@RequestBody Map<String, Object>campos ){
		
		Restaurante res = restauranteRepository.buscar(id);
		
		if(res == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, res);
		
		return atualizar(id, res);
	}

	private void merge(Map<String, Object> campos, Restaurante destino) {
		ObjectMapper mapper = new ObjectMapper();
		Restaurante rest = mapper.convertValue(campos, Restaurante.class);
		
		campos.forEach((nomePropriedade, valorPropriedade) -> {
			
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novo = ReflectionUtils.getField(field, rest);

			ReflectionUtils.setField(field, destino, novo);
			
		});
	}
	
}




















