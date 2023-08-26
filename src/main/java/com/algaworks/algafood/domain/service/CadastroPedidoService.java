package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Pedido salvar(Pedido pedido) {
		List<ItemPedido> lista = pedido.getItens();
		
		if(lista.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não Possui ítens no PEDIDO");
		}
			
		
		pedido = pedidoRepository.save(pedido);
		return pedido;
	}
	
}
