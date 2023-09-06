package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String ESTADO_EM_USO = "Estado de código %d não pode ser removida pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	
	public Estado salvar(Estado salvar) {
		
		return estadoRepository.save(salvar);
	}
	
	
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(ESTADO_EM_USO, id));
		}
	}

	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(()-> new EstadoNaoEncontradoException(id));
	}
}
























