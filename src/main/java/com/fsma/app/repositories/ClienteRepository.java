package com.fsma.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fsma.app.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly = true)
	Cliente findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Optional<Cliente> findById(Long id);
	
}
