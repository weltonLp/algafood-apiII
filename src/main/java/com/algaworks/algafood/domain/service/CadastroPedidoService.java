package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private FormaPagamentoRepository formapagamentoRepository;
			
	
	
	public Pedido salvar(Pedido pedido) {
		Long usuario_id = pedido.getCliente().getId();
		Optional<Usuario> u = usuarioRepository.findById(usuario_id); 
		
		Long restaurante_id = pedido.getRestaurante().getId();
		Optional<Restaurante> r = restauranteRepository.findById(restaurante_id);
		
		Long forma_Pagamento_id = pedido.getFormaPagamento().getId();
		Optional<FormaPagamento> fp = formapagamentoRepository.findById(forma_Pagamento_id);
		
		boolean bSalvar = ((u.isEmpty() || r.isEmpty()) || fp.isEmpty()) ? false:true;
		
		if(!bSalvar) {
			throw new EntidadeNaoEncontradaException("Restaurante|Usuário ou Forma de pagamento não podem estar em branco");
		}
	
		pedido = pedidoRepository.save(pedido);
		return pedido;
	}
	
}
