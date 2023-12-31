package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public Produto salvar(Produto produto) {
		
		Long id = produto.getRestaurante().getId();
		Restaurante rest = restauranteRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Não existe Restaurante com id %d", id)));
		
		produto.setRestaurante(rest);
		
		
		return produtoRepository.save(produto);
	}
	
	public void deletar(Long id) {
		try {
			produtoRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Entidade %d não foi localizada", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida pois está em uso", id));
		}
		
		
	}
	
	
}













