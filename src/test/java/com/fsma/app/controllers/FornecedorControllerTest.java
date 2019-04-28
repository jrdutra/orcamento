package com.fsma.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fsma.app.entities.Fornecedor;
import com.fsma.app.repositories.FornecedorRepository;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("test")
public class FornecedorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private FornecedorRepository repository;

	private static final String BUSCAR_FORNECEDOR_ID_URL = "/api/fornecedor/buscarid/";
	private static Fornecedor fornecedor;
	
	@Before
	public void setup() {
		fornecedor = new Fornecedor();
		fornecedor.setCnpj("48218737000102");
		fornecedor.setNome("Fornecedor de Teste");
		fornecedor.setEndereco("Rua A n 90, Imboassica");
		fornecedor.setTelefone("(22)997634093");
	}
	
	@After
	public void resetDb() {
	    repository.deleteAll();
	}
	 
	@Test
	public void testBuscarFornecedorIdInexistente() throws Exception {
		
		mvc.perform(get(BUSCAR_FORNECEDOR_ID_URL + Mockito.anyString()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testBuscarFornecedorExistente() throws Exception {
		Long id = createFornecedorTeste(fornecedor);
		mvc.perform(get(BUSCAR_FORNECEDOR_ID_URL + id).contentType(MediaType.APPLICATION_JSON))
		    .andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.nome").value(fornecedor.getNome()))
			.andExpect(jsonPath("$.data.cnpj").value(fornecedor.getCnpj()))
			.andExpect(jsonPath("$.data.telefone").value(fornecedor.getTelefone()))
			.andExpect(jsonPath("$.data.endereco").value(fornecedor.getEndereco()))
			.andExpect(jsonPath("$.data.id").value(fornecedor.getId()));
		
	}
	
	private Long createFornecedorTeste(Fornecedor fornecedor) {
	        Fornecedor fornecedorSalvo = repository.saveAndFlush(fornecedor);
	        return fornecedorSalvo.getId();
	}
}
