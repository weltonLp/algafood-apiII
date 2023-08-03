package com.algaworks.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApi2Application;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CidadeRepository;

public class ConsultaCidadeMain {
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgafoodApi2Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cad = context.getBean(CidadeRepository.class);
		
		List<Cidade> coz = cad.listar();
		
		for(Cidade c: coz) {
			System.out.printf("Cidade: %s  -  Estado: %s\n ", c.getNome(), c.getEstado().getNome());
			System.out.println("-----------------------------");
			
		}
	}
}
