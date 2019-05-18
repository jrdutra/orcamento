package com.fsma.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsma.app.entities.Fornecedor;
import com.fsma.app.repositories.FornecedorRepository;
import com.fsma.app.services.FornecedorService;

@Service
public class ForncedorServiceImpl implements FornecedorService{
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	public Optional<Fornecedor> buscarPorCnpj(String cnpj){
		return Optional.ofNullable(fornecedorRepository.findByCnpj(cnpj));
	}
	
	@Override
	public Optional<Fornecedor> buscarPorId(Long id){
		return fornecedorRepository.findById(id);
	}
	
	@Override
	public List<Fornecedor> buscarPorNome(String nome){
		Pageable p = PageRequest.of(0, 3);
		return fornecedorRepository.findTop3ByNome(nome, p);
	}
	
	@Override
	public Fornecedor persistir(Fornecedor fornecedor) {
		return this.fornecedorRepository.save(fornecedor);
	}
	
	@Override
	public void remover(Long id) {
		this.fornecedorRepository.deleteById(id);
	}
}
