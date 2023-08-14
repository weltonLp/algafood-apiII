package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
//	private Boolean ativo;
//	private LocalDateTime dataCadastro;
//	private LocalDateTime dataAtualização;
//	

	@ManyToOne
	@JoinColumn(name="cozinha_id")
	private Cozinha cozinha;
	
	public Restaurante() {
		super();
	}

	public Restaurante(String nome, BigDecimal taxaFrete) {
		super();
		
		this.nome = nome;
		this.taxaFrete = taxaFrete;
	
	}

	
	
}
