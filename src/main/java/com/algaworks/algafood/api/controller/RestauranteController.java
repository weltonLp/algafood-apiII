package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
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
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable Long id){
		
		return restauranteService.buscarOuFalhar(id);
		
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
	public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		
		Restaurante res = restauranteService.buscarOuFalhar(id);
		restaurante.setId(id);
		
		return restauranteService.salvar(restaurante);
	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		
		restauranteService.excluir(id);
		
	}
	
//	@PatchMapping("/{id}")
//	public ResponseEntity<?>atualizarParcial(@PathVariable Long id, 
//			@RequestBody Map<String, Object>campos ){
//		
//		Optional<Restaurante> res = restauranteRepository.findById(id);
//		
//		if(res.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		merge(campos, res.get());
//		
//		return atualizar(id, res.get());
//	}

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




















