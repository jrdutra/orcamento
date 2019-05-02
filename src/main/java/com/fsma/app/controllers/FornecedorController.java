package com.fsma.app.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsma.app.dtos.in.FornecedorDtoIn;
import com.fsma.app.dtos.out.FornecedorDtoOut;
import com.fsma.app.entities.Fornecedor;
import com.fsma.app.response.Response;
import com.fsma.app.services.FornecedorService;
import com.fsma.app.validador.FornecedorValidador;

@RestController
@RequestMapping("/api/fornecedor")
@CrossOrigin(origins = "*")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private FornecedorValidador fornecedorValidador;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Response<FornecedorDtoOut>> cadastrar(@Valid @RequestBody FornecedorDtoIn fornecedorDtoIn,
			BindingResult result) throws NoSuchAlgorithmException{
		
		Response<FornecedorDtoOut> response = new Response<FornecedorDtoOut>();
		
		if (fornecedorValidador.isNaoPodeIncluir(fornecedorDtoIn.getFornecedor(), result)) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new FornecedorDtoOut(this.fornecedorService.persistir(fornecedorDtoIn.getFornecedor())));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarid/{id}")
	public ResponseEntity<Response<FornecedorDtoOut>> buscarPorId(@PathVariable("id") Long id){
		
		Response<FornecedorDtoOut> response = new Response<FornecedorDtoOut>();
		
		Optional<Fornecedor> fornecedorOpt = fornecedorService.buscarPorId(id);
		
		if(!fornecedorOpt.isPresent()) {
			response.getErrors().add("Fornecedor não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new FornecedorDtoOut(fornecedorOpt.get()));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarnome/{nome}")
	public ResponseEntity<Response<List<FornecedorDtoOut>>> buscarPorNome(@PathVariable("nome") String nome){
		
		Response<List<FornecedorDtoOut>> response = new Response<List<FornecedorDtoOut>>();
		
		List<Fornecedor> listaFornecedor = fornecedorService.buscarPorNome(nome);
		
		System.out.print(listaFornecedor);
		
		List<FornecedorDtoOut> listaFornecedorDtoOut = new ArrayList<FornecedorDtoOut>();
		
		if(listaFornecedor.isEmpty()) {
			response.getErrors().add("Fornecedores não encontrados para o nome " + nome);
			return ResponseEntity.badRequest().body(response);
		}
		
		for(Fornecedor f : listaFornecedor) {
			listaFornecedorDtoOut.add(new FornecedorDtoOut(f));
		}
		
		response.setData(listaFornecedorDtoOut);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "remover/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Optional<Fornecedor> fornecedor = this.fornecedorService.buscarPorId(id);
		
		if(!fornecedor.isPresent()) {
			response.getErrors().add("Erro ao remover o fornecedor. Fornecedor não encontrado para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.fornecedorService.remover(id);
		
		response.setData(new String("Fornecedor com id " + id + " removido com sucesso!"));
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "atualizar/{id}")
	public ResponseEntity<Response<FornecedorDtoOut>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody FornecedorDtoIn fornecedorDtoIn, BindingResult result) throws ParseException {
		
		Response<FornecedorDtoOut> response = new Response<FornecedorDtoOut>();
		
		fornecedorValidador.isPodeAtualizar(fornecedorDtoIn.getFornecedor(), result);
		
		fornecedorDtoIn.setId(id);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		FornecedorDtoOut fornecedorDtoOut = new FornecedorDtoOut(fornecedorService.persistir(fornecedorDtoIn.getFornecedor()));
		
		response.setData(fornecedorDtoOut);
		
		return ResponseEntity.ok(response);
	}
	
}
