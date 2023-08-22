package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
}
