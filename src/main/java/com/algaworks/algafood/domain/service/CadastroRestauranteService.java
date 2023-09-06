package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String RESTAURANTE_NAO_LOCALIZADO = "Restaurante de id %d não foi localizado";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		
		
//		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
//				.orElseThrow(()->new EntidadeNaoEncontradaException(
//						String.format("Não existe cozinha com o código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(
					String.format(RESTAURANTE_NAO_LOCALIZADO, id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("RESTAURANTE de código %d não pode ser removido pois está em uso", id));
		}
	}
	
	public Restaurante buscarOuFalhar(Long id) {
		
		Restaurante res =  restauranteRepository.findById(id)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format(RESTAURANTE_NAO_LOCALIZADO, id)));
		
		Long idCozinha = res.getCozinha().getId();
//		Cozinha coz = cozinhaRepository.findById(idCozinha)
//				.orElseThrow(()-> new CozinhaNaoEncontradoException(
//						String.format("Não Existe Cozinha de Id %d", idCozinha)));
		
		Cozinha coz = cozinhaService.buscarOuFalhar(idCozinha);
		
		res.setCozinha(coz);
		return res;
	}
	
}
























