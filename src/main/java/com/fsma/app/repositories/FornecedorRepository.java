package com.fsma.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Fornecedor;

public interface FornecedorRepository  extends JpaRepository<Fornecedor, Long>{

	@Transactional(readOnly = true)
	Fornecedor findByCnpj(String cnpj);
	
	@Transactional(readOnly = true)
	Optional<Fornecedor> findById(Long id);
	
}
