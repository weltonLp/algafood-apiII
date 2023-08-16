package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	
	
}
