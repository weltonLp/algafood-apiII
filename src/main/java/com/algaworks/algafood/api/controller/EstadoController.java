package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	
	private EstadoRepository estadoRepository;

	public EstadoController(EstadoRepository estadoRepository) {
		super();
		this.estadoRepository = estadoRepository;
	}
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.listar();
	}
	
}
