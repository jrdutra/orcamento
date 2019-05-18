package com.fsma.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsma.app.entities.Cliente;
import com.fsma.app.repositories.ClienteRepository;
import com.fsma.app.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Optional<Cliente> buscarPorCpf(String cpf){
		return Optional.ofNullable(clienteRepository.findByCpf(cpf));
	}
	
	@Override
	public Optional<Cliente> buscarPorId(Long id){
		return clienteRepository.findById(id);
	}
	
	@Override
	public List<Cliente> buscarPorNome(String nome){
		Pageable p = PageRequest.of(0, 3);
		return clienteRepository.findTop3ByNome(nome, p);
	}
	
	@Override
	public Cliente persistir(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	@Override
	public void remover(Long id) {
		this.clienteRepository.deleteById(id);
	}
}
