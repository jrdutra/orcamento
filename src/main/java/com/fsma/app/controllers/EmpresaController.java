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

import com.fsma.app.dtos.in.EmpresaDtoIn;
import com.fsma.app.dtos.out.EmpresaDtoOut;
import com.fsma.app.entities.Empresa;
import com.fsma.app.response.Response;
import com.fsma.app.services.EmpresaService;
import com.fsma.app.validador.EmpresaValidador;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EmpresaValidador empresaValidador;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Response<EmpresaDtoOut>> cadastrar(@Valid @RequestBody EmpresaDtoIn empresaDtoIn,
			BindingResult result) throws NoSuchAlgorithmException{
        
		Response<EmpresaDtoOut> response = new Response<EmpresaDtoOut>();
		
		if (empresaValidador.isNaoPodeIncluir(empresaDtoIn.getEmpresa(), result)) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new EmpresaDtoOut(this.empresaService.persistir(empresaDtoIn.getEmpresa())));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarid/{id}")
	public ResponseEntity<Response<EmpresaDtoOut>> buscarPorId(@PathVariable("id") Long id){
		
		Response<EmpresaDtoOut> response = new Response<EmpresaDtoOut>();
		
		Optional<Empresa> empresaOpt = empresaService.buscarPorId(id);
		
		if(!empresaOpt.isPresent()) {
			response.getErrors().add("Empresa não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new EmpresaDtoOut(empresaOpt.get()));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarnomefantasia/{nomeFantasia}")
	public ResponseEntity<Response<List<EmpresaDtoOut>>> buscarPorNomeFantasia(@PathVariable("nomeFantasia") String nomeFantasia){
		
		Response<List<EmpresaDtoOut>> response = new Response<List<EmpresaDtoOut>>();
		
		List<Empresa> listaEmpresa = empresaService.buscarPorNomeFantasia(nomeFantasia);
		
		List<EmpresaDtoOut> listaEmpresaDtoOut = new ArrayList<EmpresaDtoOut>();
		
		if(listaEmpresa.isEmpty()) {
			response.getErrors().add("Empresas não encontradas para o nome fantasia " + nomeFantasia);
			return ResponseEntity.badRequest().body(response);
		}
		
		for(Empresa e : listaEmpresa) {
			listaEmpresaDtoOut.add(new EmpresaDtoOut(e));
		}
		
		response.setData(listaEmpresaDtoOut);
		
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping(value = "remover/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Optional<Empresa> empresa = this.empresaService.buscarPorId(id);
		
		if(!empresa.isPresent()) {
			response.getErrors().add("Erro ao remover a empresa. Empresa não encontrada para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.remover(id);
		
		response.setData(new String("Empresa com id " + id + " removida com sucesso!"));
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "atualizar/{id}")
	public ResponseEntity<Response<EmpresaDtoOut>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody EmpresaDtoIn empresaDtoIn, BindingResult result) throws ParseException {
		
		Response<EmpresaDtoOut> response = new Response<EmpresaDtoOut>();
		
		empresaValidador.isPodeAtualizar(empresaDtoIn.getEmpresa(), result);
		
		empresaDtoIn.setId(id);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		EmpresaDtoOut empresaDtoOut = new EmpresaDtoOut(empresaService.persistir(empresaDtoIn.getEmpresa()));
		
		response.setData(empresaDtoOut);
		
		return ResponseEntity.ok(response);
	}
	
}
