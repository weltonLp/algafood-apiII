package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(nullable = false)
	private BigDecimal subTotal;
	
	private BigDecimal taxaFrete;
	
	@JoinColumn(nullable = false)
	private BigDecimal valorTotal;
	
	@JoinColumn(nullable = false)
	private LocalDateTime dataCriacao;
	
	@JoinColumn(nullable = false)
	private LocalDateTime dataConfirmacao;
	
	@JoinColumn(nullable = false)
	private LocalDateTime dataCancelamento;
	
	@JoinColumn(nullable = false)
	private LocalDateTime dataEntrega;
	
	@Enumerated(EnumType.STRING)
//	@JoinColumn(name="status_pedido")
	private StatusPedido status;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario cliente;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
	@Embedded
	private Endereco endereco;
	
	
	@ManyToOne
	@JoinColumn(name="restaurante_id")
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name="forma_pagamento_id")
	private FormaPagamento formaPagamento;
	
}


















