package com.fsma.app.services;

import java.util.Optional;

import com.fsma.app.entities.Cliente;

public interface ClienteService {
	
	/**
	 * Retorna um Cliente dado um CPF
	 * 
	 * @param cpf
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorCpf(String cpf);
	/**
	 * Retorna um Cliente dado um id
	 * 
	 * @param id
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorId(Long id);
	
	/**
	 * Cadastra um Cliente na base de Dados
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Cliente persistir(Cliente cliente);
	
	/**
	 * Remove um Cliente na base de Dados
	 * 
	 * @param id
	 * @return void
	 */
	void remover(Long id);
}
