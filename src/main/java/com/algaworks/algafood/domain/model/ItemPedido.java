package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@JoinColumn(nullable = false)
	private Integer quantidade;
	
	@JoinColumn(nullable = false)
	private BigDecimal precoUnitario;
	
	@JoinColumn(nullable = false)
	private BigDecimal precoTotal;
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
}









