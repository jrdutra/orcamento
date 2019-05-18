package com.fsma.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Long>{

	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
	
	@Transactional(readOnly = true)
	Optional<Empresa> findById(Long id);
	
	@Transactional(readOnly = true)
	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.nomeFantasia like %:pNomeFantasia%")
	List<Empresa> findByNomeFantasia(@Param("pNomeFantasia") String empresaNomeFantasia);
	
}