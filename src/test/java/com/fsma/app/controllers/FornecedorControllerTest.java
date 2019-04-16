package com.fsma.app.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fsma.app.services.FornecedorService;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("test")
public class FornecedorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private FornecedorService fornecedorService;
	
	private static final String BUSCAR_FORNECEDOR_ID_URL = "/api/fornecedor/buscarid/";
	private static final Long ID = Long.valueOf(2);
	
	@Test
	public void testBuscarEmpresaIdValido() throws Exception {
		BDDMockito.given(this.fornecedorService.buscarPorId(Mockito.anyLong())).willReturn(Optional.empty());
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_FORNECEDOR_ID_URL + ID).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors").value("Fornecedor não encontrado para o id " + ID));
	}
	
}
