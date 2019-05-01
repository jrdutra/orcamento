package com.fsma.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
	private static final String REMOVER_FORNECEDOR_URL = "/api/fornecedor/remover/";
	private static final String ATUALIZAR_FORNECEDOR_URL = "/api/fornecedor/atualizar/";
	private Fornecedor fornecedorSalvo;
	private Fornecedor fornecedorNaoSalvo;
	
	@Before
	public void setup() {
		//Fornecedor o objeto fornecedor salvo é diferente do fornecedor nao salvo
        fornecedorSalvo = criaFornecedorSalvo();
        fornecedorNaoSalvo = criaFornecedorNaoSalvo();
	}
	
	@After
	public void resetDb() {
	    repository.deleteAll();
	}
	
	@Test
	public void testBuscarFornecedorInexistente() throws Exception {
		mvc.perform(get(BUSCAR_FORNECEDOR_ID_URL + Mockito.anyString()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
	
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
	
	@Test
	public void testCadastrarFornecedorNaoSalvo() throws Exception{
		mvc.perform(postRequestJSON(CADASTRAR_FORNECEDOR_URL, fornecedorNaoSalvo))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.nome").value(fornecedorNaoSalvo.getNome()))
				.andExpect(jsonPath("$.data.cnpj").value(fornecedorNaoSalvo.getCnpj()))
				.andExpect(jsonPath("$.data.telefone").value(fornecedorNaoSalvo.getTelefone()))
				.andExpect(jsonPath("$.data.endereco").value(fornecedorNaoSalvo.getEndereco()))
				.andExpect(jsonPath("$.data.id").isNumber())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	public void testCadastrarFornecedorSalvo() throws Exception{
		mvc.perform(postRequestJSON(CADASTRAR_FORNECEDOR_URL, fornecedorSalvo))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Fornecedor já existente."));
	}
	
	@Test
	public void testDeletarFornecedorExistente() throws Exception {
		mvc.perform(deleteRequestJSON(REMOVER_FORNECEDOR_URL + fornecedorSalvo.getId()))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$.data").value("Fornecedor com id " 
				   								+ fornecedorSalvo.getId() 
				   								+ " removido com sucesso!"))
		   .andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	public void testDeletarFornecedorInexistente() throws Exception {
		Long idInexistente = repository.findTopByOrderByIdDesc().getId();
		idInexistente++;
		mvc.perform(deleteRequestJSON(REMOVER_FORNECEDOR_URL + idInexistente))
		   .andExpect(status().isBadRequest())
		   .andExpect(jsonPath("$.data").isEmpty())
		   .andExpect(jsonPath("$.errors").value("Erro ao remover o fornecedor. "
		   		                               + "Fornecedor não encontrado para o ID "
				                               + idInexistente));
	}
	
	@Test
	public void testAtualizarFornecedorExistente() throws Exception {
		fornecedorSalvo.setNome("Nome Alterado");
		mvc.perform(putRequestJSON(ATUALIZAR_FORNECEDOR_URL + fornecedorSalvo.getId(), fornecedorSalvo))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$.data").isNotEmpty())
		   .andExpect(jsonPath("$.data.nome").value(fornecedorSalvo.getNome()))
		   .andExpect(jsonPath("$.data.cnpj").value(fornecedorSalvo.getCnpj()))
		   .andExpect(jsonPath("$.data.telefone").value(fornecedorSalvo.getTelefone()))
		   .andExpect(jsonPath("$.data.endereco").value(fornecedorSalvo.getEndereco()))
		   .andExpect(jsonPath("$.data.id").value(fornecedorSalvo.getId()))
		   .andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	public void testAtualizarFornecedorInexistente() throws Exception {
		Long idInexistente = repository.findTopByOrderByIdDesc().getId();
		idInexistente++;
		fornecedorSalvo.setId(idInexistente);
		mvc.perform(putRequestJSON(ATUALIZAR_FORNECEDOR_URL + fornecedorSalvo.getId(), fornecedorSalvo))
		   .andExpect(status().isBadRequest())
		   .andExpect(jsonPath("$.data").isEmpty())
		   .andExpect(jsonPath("$.errors").value("Fornecedor não encontrado, id inexistente."));
	}
	
	//FUNÇÕES AUXILIARES
	private MockHttpServletRequestBuilder getRequestJSON(String url) {
		MockHttpServletRequestBuilder request = get(url);
		return request.contentType(MediaType.APPLICATION_JSON);
	}
	private MockHttpServletRequestBuilder deleteRequestJSON(String url) {
		MockHttpServletRequestBuilder request = delete(url);
		return request.contentType(MediaType.APPLICATION_JSON);
	}
	private MockHttpServletRequestBuilder postRequestJSON(String url, Fornecedor f) throws JsonProcessingException {
		MockHttpServletRequestBuilder request = post(url);
		return request.content(this.obterJsonRequeisicaoPost(f))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
	}
	private MockHttpServletRequestBuilder putRequestJSON(String url, Fornecedor f) throws JsonProcessingException {
		MockHttpServletRequestBuilder request = put(url);
		return request.content(this.obterJsonRequeisicaoPut(f))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
	}
	//FUNCAO PARA CRIAR UMA STRING jSON DE UM FornecedorDtoIn
	private String obterJsonRequeisicaoPost(Fornecedor f) throws JsonProcessingException{
		FornecedorDtoIn fornecedorDtoIn = new FornecedorDtoIn(f);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(fornecedorDtoIn);
	}
	//FUNCAO PARA CRIAR UMA STRING jSON DE UM FornecedorDtoIn
	private String obterJsonRequeisicaoPut(Fornecedor f) throws JsonProcessingException{
		FornecedorDtoIn fornecedorDtoIn = new FornecedorDtoIn(f);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(fornecedorDtoIn);
	}
	//FUNCAO PARA CRIAR FORNECEDOR SALVO
	private Fornecedor criaFornecedorSalvo() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj("48218737000102");
		fornecedor.setNome("Fornecedor de Teste");
		fornecedor.setEndereco("Rua A n 90, Imboassica");
		fornecedor.setTelefone("(22)997634093");
        return repository.saveAndFlush(fornecedor);
	}
	//FUNCAO PARA CRIAR FORNECEDOR NAO SALVO
	private Fornecedor criaFornecedorNaoSalvo() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj("48218737000101");
		fornecedor.setNome("Fornecedor Para Salvar no Banco");
		fornecedor.setEndereco("Rua B n 91, Pq. Tubos");
		fornecedor.setTelefone("(22)997634093");
		return fornecedor;
	}
}
