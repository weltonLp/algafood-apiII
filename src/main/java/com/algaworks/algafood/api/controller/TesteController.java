package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha>buscaPorNome(@RequestParam String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	
	@GetMapping("/restaurantes/por-taxa")
	public List<Restaurante>porTaxa(BigDecimal taxaInicial,BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante>porTaxa(String nome, Long id ){
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);
	}
	
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante>porTaxa(String nome){
		return restauranteRepository.findFirstByNomeContaining(nome);
	}
	@GetMapping("/restaurantes/existe-por-nome")
	public boolean existsNome(String nome){
		return restauranteRepository.existsByNome(nome);
	}
	@GetMapping("/restaurantes/contar-por-cozinha")
	public int contarRestaurante(Long id){
		return restauranteRepository.countByCozinhaId(id);
	}
	
	
}
