package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo salvar(Grupo grupo) {
		
		return grupoRepository.save(grupo);
	}
	
	public void deletar(Long id) {
		try {
			grupoRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Entidade %d não foi localizada", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida pois está em uso", id));
		}
	}
	
}
