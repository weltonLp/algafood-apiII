package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> listar(){
		return manager.createQuery("from Permissao",Permissao.class)
				.getResultList();
	}
	
	@Override
	@Transactional
	public Permissao salvar(Permissao cozinha) {
		return manager.merge(cozinha);
	}
	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}
	@Override
	@Transactional
	public void remover (Permissao cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}
	
}
