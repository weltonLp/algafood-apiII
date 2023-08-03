package com.algaworks.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApi2Application;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class ConsultaEstadoaMain2 {
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgafoodApi2Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository cad = context.getBean(EstadoRepository.class);
		
		List<Estado> coz = cad.listar();
		
		for(Estado c: coz) {
			System.out.println("ESTADO......: " + c.getNome());
		}
	}
}
