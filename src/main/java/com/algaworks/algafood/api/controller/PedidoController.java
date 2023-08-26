package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido> listar(){	
			
		return pedidoRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		if(pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		EntidadeNaoEncontradaException e = new EntidadeNaoEncontradaException("Não Existe Pedido Informado");
		return ResponseEntity.badRequest().body(e.getMessage());
		
	}
	
	
	
}





















