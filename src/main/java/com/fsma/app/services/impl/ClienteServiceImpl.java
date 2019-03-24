package com.fsma.app.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsma.app.entities.Cliente;
import com.fsma.app.repositories.ClienteRepository;
import com.fsma.app.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Optional<Cliente> buscarPorCpf(String cpf){
		log.info("Buscando um cliente para o CPF {}", cpf);
		return Optional.ofNullable(clienteRepository.findByCpf(cpf));
	}
	
	@Override
	public Optional<Cliente> buscarPorId(Long id){
		log.info("Buscando um cliente para o Id {}", id);
		return clienteRepository.findById(id);
	}
	
	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persisinto o cliente: {}", cliente);
		return this.clienteRepository.save(cliente);
	}
	
	@Override
	public void remover(Long id) {
		log.info("Removendo o cliente de id: {}", id);
		this.clienteRepository.deleteById(id);
	}
}
