package com.fsma.app.services;

import java.util.List;
import java.util.Optional;

import com.fsma.app.entities.Fornecedor;

public interface FornecedorService {

	Optional<Fornecedor> buscarPorCnpj(String cnpj);

	Optional<Fornecedor> buscarPorId(Long id);

	List<Fornecedor> buscarPorNome(String nome);

	Fornecedor persistir(Fornecedor forncedor);
	
	void remover(Long id);
}
