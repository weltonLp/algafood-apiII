package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroPedidoService cadastroPedidoService;
	
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
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Pedido pedido){
		
		try {
			
			pedido = cadastroPedidoService.salvar(pedido);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
		
		
	}
	
}





















