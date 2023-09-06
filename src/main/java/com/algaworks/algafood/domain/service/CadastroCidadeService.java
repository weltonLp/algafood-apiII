package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	private static final String CIDADE_NAO_LOCALIZADO = "Cidade de id %d não foi localizado NO SISTEMA";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService estadoService;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow( ()-> new EstadoNaoEncontradoException(
						String.format("Não existe Estado de ID: %d", estadoId)));
		cidade.setEstado(estado);
//		try {
			
			return cidadeRepository.save(cidade);
			
//		}catch(EntidadeNaoEncontradaException e) {
//			throw new NegocioException(e.getMessage());
//		}
		
	}
	
	
	public void excluir(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(CIDADE_NAO_LOCALIZADO, id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida pois está em uso", id));
		}
	}
	
	public Cidade buscarouFalhar(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow( () -> new CidadeNaoEncontradoException(
						String.format(CIDADE_NAO_LOCALIZADO, id)));
	}
	
}
























