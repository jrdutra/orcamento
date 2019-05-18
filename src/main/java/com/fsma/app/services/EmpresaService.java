package com.fsma.app.services;

import java.util.List;
import java.util.Optional;

import com.fsma.app.entities.Empresa;

public interface EmpresaService {

	Optional<Empresa> buscarPorCnpj(String cnpj);

	Optional<Empresa> buscarPorId(Long id);
	
	List<Empresa> buscarPorNomeFantasia(String nomeFantasia);
	
	Empresa persistir(Empresa Empresa);
	
	void remover(Long id);
}
