package com.fsma.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Fornecedor;

@Transactional(readOnly = true)
public interface FornecedorRepository  extends JpaRepository<Fornecedor, Long>{

	@Transactional(readOnly = true)
	Fornecedor findByCnpj(String cnpj);
	
	@Transactional(readOnly = true)
	Optional<Fornecedor> findById(Long id);
	
	@Transactional(readOnly = true)
	Fornecedor findTopByOrderByIdDesc();
	
	@Transactional(readOnly = true)
	@Query("SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.nome like %:pFornecedorNome%")
	List<Fornecedor> findByNome(@Param("pFornecedorNome") String fornecedorNome);
	
}