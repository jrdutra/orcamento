package com.fsma.app.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsma.app.entities.Fornecedor;
import com.fsma.app.repositories.FornecedorRepository;
import com.fsma.app.services.FornecedorService;

@Service
public class ForncedorServiceImpl implements FornecedorService{
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	public Optional<Fornecedor> buscarPorCnpj(String cnpj){
		log.info("Buscando um fornecedor para o cnpj {}", cnpj);
		return Optional.ofNullable(fornecedorRepository.findByCnpj(cnpj));
	}
	
	@Override
	public Optional<Fornecedor> buscarPorId(Long id){
		log.info("Buscando um fornecedor para o Id {}", id);
		return fornecedorRepository.findById(id);
	}
	
	@Override
	public Fornecedor persistir(Fornecedor fornecedor) {
		log.info("Persisinto o fornecedor: {}", fornecedor);
		return this.fornecedorRepository.save(fornecedor);
	}
	
	@Override
	public void remover(Long id) {
		log.info("Removendo o fornecedor de id: {}", id);
		this.fornecedorRepository.deleteById(id);
	}
}
