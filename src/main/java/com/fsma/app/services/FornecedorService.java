package com.fsma.app.services;

import java.util.Optional;

import com.fsma.app.entities.Fornecedor;

public interface FornecedorService {
	/**
	 * Retorna um Fornecedor dado um Cnpj
	 * 
	 * @param cnpj
	 * @return Optional<Fornecedor>
	 */
	Optional<Fornecedor> buscarPorCnpj(String cnpj);
	/**
	 * Retorna um Fornecedor dado um id
	 * 
	 * @param id
	 * @return Optional<Fornecedor>
	 */
	Optional<Fornecedor> buscarPorId(Long id);
	
	/**
	 * Cadastra um Fornecedor na base de Dados
	 * 
	 * @param fornecedor
	 * @return Fornecedor
	 */
	Fornecedor persistir(Fornecedor forncedor);
	
	/**
	 * Remove um Fornecedor na base de Dados
	 * 
	 * @param id
	 * @return void
	 */
	void remover(Long id);
}
