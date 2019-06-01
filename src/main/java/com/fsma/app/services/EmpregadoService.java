package com.fsma.app.services;

import java.util.List;
import java.util.Optional;

import com.fsma.app.entities.Empregado;

public interface EmpregadoService {

	Optional<Empregado> buscarPorCpf(String cpf);
	
	Optional<Empregado> buscarPorId(Long id);

	List<Empregado> buscarPorNome(String nome);

	Empregado persistir(Empregado forncedor);
	
	void remover(Long id);
}
