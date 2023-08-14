package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cidade;


@Component
public interface CidadeRepository {
	
	List<Cidade>listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cozinha);
	void remover(Long id);
	
}
