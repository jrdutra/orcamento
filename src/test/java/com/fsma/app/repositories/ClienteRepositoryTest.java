package com.fsma.app.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsma.app.entities.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private static final String CPF = "11666683710";
	
	@Before
	public void setUp() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNome("Joao Ricardo Core Dutra");
		cliente.setCpf(CPF);
		this.clienteRepository.save(cliente);
	}
	
	@After
	public final void tearDown() {
		this.clienteRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorCpf() {
		Cliente cliente = this.clienteRepository.findByCpf(CPF);
		assertEquals(CPF, cliente.getCpf());
	}
	
	
}
