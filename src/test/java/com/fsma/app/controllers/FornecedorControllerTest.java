package com.fsma.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsma.app.dtos.in.FornecedorDtoIn;
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
	private static final String CADASTRAR_FORNECEDOR_URL = "/api/fornecedor/cadastrar/";
	private Fornecedor fornecedorSalvo;
	private Fornecedor fornecedorNaoSalvo;
	
	@Before
	public void setup() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj("48218737000102");
		fornecedor.setNome("Fornecedor de Teste");
		fornecedor.setEndereco("Rua A n 90, Imboassica");
		fornecedor.setTelefone("(22)997634093");
        fornecedorSalvo = repository.saveAndFlush(fornecedor);
        fornecedorNaoSalvo = new Fornecedor();
        fornecedorNaoSalvo.setCnpj("48218737000101");
        fornecedorNaoSalvo.setNome("Fornecedor Para Salvar no Banco");
        fornecedorNaoSalvo.setEndereco("Rua B n 91, Pq. Tubos");
        fornecedorNaoSalvo.setTelefone("(22)997634093");
	}
	
	@After
	public void resetDb() {
	    repository.deleteAll();
	}
	//TESTA A BUSCA DE FORNECEDOR POR UM ID NÃO EXISTENTE
	@Test
	public void testBuscarFornecedorIdInexistente() throws Exception {
		mvc.perform(get(BUSCAR_FORNECEDOR_ID_URL + Mockito.anyString()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
	//TESTA A BUSCA DE UM FORNECEDOR POR UM ID EXISTENTE(Requisição GET)
	@Test
	public void testBuscarFornecedorExistente() throws Exception {
		mvc.perform(getRequestJSON(BUSCAR_FORNECEDOR_ID_URL + fornecedorSalvo.getId()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.nome").value(fornecedorSalvo.getNome()))
			.andExpect(jsonPath("$.data.cnpj").value(fornecedorSalvo.getCnpj()))
			.andExpect(jsonPath("$.data.telefone").value(fornecedorSalvo.getTelefone()))
			.andExpect(jsonPath("$.data.endereco").value(fornecedorSalvo.getEndereco()))
			.andExpect(jsonPath("$.data.id").value(fornecedorSalvo.getId()))
			.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	//TESTA O CADASTRO DE UM FORNECEDOR NÃO EXISTENTE NO BANCO(Requisição POST)
	@Test
	public void testCadastrarFornecedorExistente() throws Exception{
		mvc.perform(postRequestJSON(CADASTRAR_FORNECEDOR_URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.nome").value(fornecedorNaoSalvo.getNome()))
				.andExpect(jsonPath("$.data.cnpj").value(fornecedorNaoSalvo.getCnpj()))
				.andExpect(jsonPath("$.data.telefone").value(fornecedorNaoSalvo.getTelefone()))
				.andExpect(jsonPath("$.data.endereco").value(fornecedorNaoSalvo.getEndereco()))
				.andExpect(jsonPath("$.data.id").isNumber())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	
	//FUNÇÕES AUXILIARES
	private MockHttpServletRequestBuilder getRequestJSON(String url) {
		MockHttpServletRequestBuilder request = get(url);
		return request.contentType(MediaType.APPLICATION_JSON);
	}
	private MockHttpServletRequestBuilder postRequestJSON(String url) throws JsonProcessingException {
		MockHttpServletRequestBuilder request = post(url);
		return request.content(this.obterJsonRequeisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
	}
	//FUNCAO PARA CRIAR UMA STRING jSON DE UM FornecedorDtoIn
	private String obterJsonRequeisicaoPost() throws JsonProcessingException{
		FornecedorDtoIn fornecedorDtoIn = new FornecedorDtoIn(fornecedorNaoSalvo);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(fornecedorDtoIn);
	}
}
