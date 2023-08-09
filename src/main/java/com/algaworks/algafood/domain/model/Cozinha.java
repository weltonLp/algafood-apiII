package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("titulo")//ALTERA O NOME DO ATRIBUTO NA REPRESENTAÇÃO
	@Column(nullable = false)
	private String nome;
	
//	@OneToMany
//	private List<Restaurante> restaurante = new ArrayList<>();
	
	
	public Cozinha() {
		super();
	}
	
	public Cozinha(String nome) {
		super();
		this.nome = nome;
	}




	
	
}
