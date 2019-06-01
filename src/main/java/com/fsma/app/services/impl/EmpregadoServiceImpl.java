package com.fsma.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsma.app.entities.Empregado;
import com.fsma.app.repositories.EmpregadoRepository;
import com.fsma.app.services.EmpregadoService;

@Service
public class EmpregadoServiceImpl implements EmpregadoService{
	
	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	@Override
	public Optional<Empregado> buscarPorCpf(String cpf){
		return Optional.ofNullable(empregadoRepository.findByCpf(cpf));
	}
	
	@Override
	public Optional<Empregado> buscarPorId(Long id){
		return empregadoRepository.findById(id);
	}
	
	@Override
	public List<Empregado> buscarPorNome(String nome){
		Pageable p = PageRequest.of(0, 3);
		return empregadoRepository.findTop3ByNome(nome, p);
	}
	
	@Override
	public Empregado persistir(Empregado empregado) {
		return this.empregadoRepository.save(empregado);
	}
	
	@Override
	public void remover(Long id) {
		this.empregadoRepository.deleteById(id);
	}
}
