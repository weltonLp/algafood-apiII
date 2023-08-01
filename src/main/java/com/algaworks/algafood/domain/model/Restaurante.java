package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restaurante {
	@Id
	private Long id;
	
	private String nome;
	private BigDecimal taxaFrete;
	
	
	public Restaurante() {
		super();
	}

	public Restaurante(String nome, BigDecimal taxaFrete) {
		super();
		
		this.nome = nome;
		this.taxaFrete = taxaFrete;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

	
	
}
