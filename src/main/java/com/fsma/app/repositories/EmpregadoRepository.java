package com.fsma.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Empregado;

@Transactional(readOnly = true)
public interface EmpregadoRepository  extends JpaRepository<Empregado, Long>{

	@Transactional(readOnly = true)
	Empregado findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Empregado findByNome(String nome);
	
	@Transactional(readOnly = true)
	Optional<Empregado> findById(Long id);
	
	@Transactional(readOnly = true)
	Empregado findTopByOrderByIdDesc();
	
	@Transactional(readOnly = true)
	@Query("SELECT empregado FROM Empregado empregado WHERE empregado.nome like %:pEmpregadoNome%")
	List<Empregado> findTop3ByNome(@Param("pEmpregadoNome") String empregadoNome, Pageable pageable);
	
}