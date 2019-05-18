package com.fsma.app.services;

import java.util.List;
import java.util.Optional;

import com.fsma.app.entities.Cliente;

public interface ClienteService {
	
	Optional<Cliente> buscarPorCpf(String cpf);

	Optional<Cliente> buscarPorId(Long id);
	
	List<Cliente> buscarPorNome(String nome);
	
	Cliente persistir(Cliente cliente);
	
	void remover(Long id);
}
