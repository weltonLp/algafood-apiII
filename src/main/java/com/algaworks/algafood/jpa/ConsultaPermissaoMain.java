package com.algaworks.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApi2Application;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class ConsultaPermissaoMain {
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgafoodApi2Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
	PermissaoRepository cad = context.getBean(PermissaoRepository.class);
		
		List<Permissao> coz = cad.listar();
		
		for(Permissao c: coz) {
			System.out.println("Permissao......: " + c.getNome());
		}
	}
}
