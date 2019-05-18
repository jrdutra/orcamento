package com.fsma.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsma.app.entities.Empresa;
import com.fsma.app.repositories.EmpresaRepository;
import com.fsma.app.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj){
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}
	
	@Override
	public Optional<Empresa> buscarPorId(Long id){
		return empresaRepository.findById(id);
	}
	
	@Override
	public List<Empresa> buscarPorNomeFantasia(String nomeFantasia){
		Pageable p = PageRequest.of(0, 3);
		return empresaRepository.findTop3ByNomeFantasia(nomeFantasia, p);
	}
	
	@Override
	public Empresa persistir(Empresa empresa) {
		return this.empresaRepository.save(empresa);
	}
	
	@Override
	public void remover(Long id) {
		this.empresaRepository.deleteById(id);
	}
	
}
