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

import com.fsma.app.dtos.in.EmpregadoDtoIn;
import com.fsma.app.dtos.out.EmpregadoDtoOut;
import com.fsma.app.entities.Empregado;
import com.fsma.app.response.Response;
import com.fsma.app.services.EmpregadoService;
import com.fsma.app.validador.EmpregadoValidador;

@RestController
@RequestMapping("/api/empregado")
@CrossOrigin(origins = "*")
public class EmpregadoController {

	@Autowired
	private EmpregadoService empregadoService;
	
	@Autowired
	private EmpregadoValidador empregadoValidador;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Response<EmpregadoDtoOut>> cadastrar(@Valid @RequestBody EmpregadoDtoIn empregadoDtoIn,
			BindingResult result) throws NoSuchAlgorithmException{
		
		Response<EmpregadoDtoOut> response = new Response<EmpregadoDtoOut>();

		if (empregadoValidador.isNaoPodeIncluir(empregadoDtoIn.getEmpregado(), result)) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new EmpregadoDtoOut(this.empregadoService.persistir(empregadoDtoIn.getEmpregado())));

		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarid/{id}")
	public ResponseEntity<Response<EmpregadoDtoOut>> buscarPorId(@PathVariable("id") Long id){

		Response<EmpregadoDtoOut> response = new Response<EmpregadoDtoOut>();
		
		Optional<Empregado> empregadoOpt = empregadoService.buscarPorId(id);
		
		if(!empregadoOpt.isPresent()) {
			response.getErrors().add("Empregado não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new EmpregadoDtoOut(empregadoOpt.get()));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarnome/{nome}")
	public ResponseEntity<Response<List<EmpregadoDtoOut>>> buscarPorNome(@PathVariable("nome") String nome){

		Response<List<EmpregadoDtoOut>> response = new Response<List<EmpregadoDtoOut>>();
		
		List<Empregado> listaEmpregado = empregadoService.buscarPorNome(nome);

		List<EmpregadoDtoOut> listaEmpregadoDtoOut = new ArrayList<EmpregadoDtoOut>();
		
		if(listaEmpregado.isEmpty()) {
			response.getErrors().add("Empregados não encontrados para o nome " + nome);
			return ResponseEntity.badRequest().body(response);
		}
		
		for(Empregado f : listaEmpregado) {
			listaEmpregadoDtoOut.add(new EmpregadoDtoOut(f));
		}
		
		response.setData(listaEmpregadoDtoOut);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "remover/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Optional<Empregado> empregado = this.empregadoService.buscarPorId(id);

		if(!empregado.isPresent()) {
			response.getErrors().add("Erro ao remover o empregado. Empregado não encontrado para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empregadoService.remover(id);
		
		response.setData(new String("Empregado com id " + id + " removido com sucesso!"));
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "atualizar/{id}")
	public ResponseEntity<Response<EmpregadoDtoOut>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody EmpregadoDtoIn empregadoDtoIn, BindingResult result) throws ParseException {

		Response<EmpregadoDtoOut> response = new Response<EmpregadoDtoOut>();
		
		empregadoValidador.isPodeAtualizar(empregadoDtoIn.getEmpregado(), result);
		
		empregadoDtoIn.setId(id);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		EmpregadoDtoOut empregadoDtoOut = new EmpregadoDtoOut(empregadoService.persistir(empregadoDtoIn.getEmpregado()));
		
		response.setData(empregadoDtoOut);
		
		return ResponseEntity.ok(response);
	}
	
}
