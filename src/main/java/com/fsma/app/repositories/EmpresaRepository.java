package com.fsma.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Long>{

	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
	
	@Transactional(readOnly = true)
	Optional<Empresa> findById(Long id);
	
}