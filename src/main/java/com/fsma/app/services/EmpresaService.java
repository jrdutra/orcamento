package com.fsma.app.services;

import java.util.Optional;

import com.fsma.app.entities.Empresa;

public interface EmpresaService {

	Optional<Empresa> buscarPorCnpj(String cnpj);

	Optional<Empresa> buscarPorId(Long id);
	
	Empresa persistir(Empresa Empresa);
	
	void remover(Long id);
}
