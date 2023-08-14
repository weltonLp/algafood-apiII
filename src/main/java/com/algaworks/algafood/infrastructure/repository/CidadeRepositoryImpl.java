package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> listar(){
		return manager.createQuery("from Cidade",Cidade.class)
				.getResultList();
	}
	
	@Override
	@Transactional
	public Cidade salvar(Cidade cozinha) {
		return manager.merge(cozinha);
	}
	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}
	@Override
	@Transactional
	public void remover (Long id) {
		Cidade cidade = buscar(id);
		manager.remove(cidade);
	}
	
}
