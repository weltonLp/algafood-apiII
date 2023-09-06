package com.algaworks.algafood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.exceptionhandler.Problema;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CadastroCidadeService cidadeService;
	
	@Autowired
	private EstadoRepository estadoRepository; 
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade>listar(){
		return cidadeRepository.findAll();
	}
	

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
			return cidadeService.salvar(cidade);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id){
		
		return cidadeService.buscarouFalhar(id);
		
	}
	
	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		
			try {
				Cidade cid = cidadeService.buscarouFalhar(id);
				
				BeanUtils.copyProperties(cidade, cid, "id");
				return cidadeService.salvar(cidade);
				
			}catch(EstadoNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
				
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id){
		
		cidadeService.excluir(id);

	}
	
	
}









