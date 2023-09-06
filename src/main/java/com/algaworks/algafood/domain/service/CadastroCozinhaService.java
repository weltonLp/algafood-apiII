package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	private static final String COZINHA_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso";
	private static final String COZINHA_NAO_EXISTE = "A Cozinha de ID: %d Não Existe no Sistema!";
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha salvar) {
		
		return cozinhaRepository.save(salvar);
	}
	
	
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException( 
					String.format(COZINHA_NAO_EXISTE, id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException( 
					String.format(COZINHA_EM_USO, id));
		}
	}

	public Cozinha buscarOuFalhar(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format(COZINHA_NAO_EXISTE, id)
							));
	}
	
}
























