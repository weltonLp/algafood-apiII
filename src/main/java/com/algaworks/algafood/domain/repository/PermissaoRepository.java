package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Permissao;

@Component
public interface PermissaoRepository {
	
	List<Permissao>listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao cozinha);
	void remover(Permissao cozinha);
}
