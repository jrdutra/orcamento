package com.fsma.app.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsma.app.entities.Cliente;
import com.fsma.app.repositories.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

	@MockBean
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	private static final String CPF = "11666683710";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.clienteRepository.findByCpf(Mockito.anyString())).willReturn(new Cliente());
		BDDMockito.given(this.clienteRepository.save(Mockito.any(Cliente.class))).willReturn(new Cliente());
	}
	
	@Test
	public void testBuscarClientePorCpf() {
		Optional<Cliente> cliente = this.clienteService.buscarPorCpf(CPF);
		assertTrue(cliente.isPresent());
	}
	
	@Test
	public void testPersistirCliente() {
		Cliente cliente = this.clienteService.persistir(new Cliente());
		assertNotNull(cliente);
	}
}
