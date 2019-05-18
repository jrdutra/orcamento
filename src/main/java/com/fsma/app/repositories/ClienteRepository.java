package com.fsma.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly = true)
	Cliente findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Optional<Cliente> findById(Long id);
	
	@Transactional(readOnly = true)
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.nome like %:pNome%")
	List<Cliente> findByNome(@Param("pNome") String clienteNome);
	
}
