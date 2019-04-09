package com.fsma.app.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
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
import com.fsma.app.dtos.in.ClienteDtoIn;
import com.fsma.app.dtos.out.ClienteDtoOut;
import com.fsma.app.entities.Cliente;
import com.fsma.app.response.Response;
import com.fsma.app.services.ClienteService;
import com.fsma.app.validador.ClienteValidador;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteValidador clienteValidador;

	@PostMapping("/cadastrar")
	public ResponseEntity<Response<ClienteDtoOut>> cadastrar(@Valid @RequestBody ClienteDtoIn clienteDtoIn,
			BindingResult result) throws NoSuchAlgorithmException{
		
		Response<ClienteDtoOut> response = new Response<ClienteDtoOut>();
		
		if (clienteValidador.isNaoPodeIncluir(clienteDtoIn.getCliente(), result)) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		};
		
		response.setData(new ClienteDtoOut(this.clienteService.persistir(clienteDtoIn.getCliente())));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarid/{id}")
	public ResponseEntity<Response<ClienteDtoOut>> buscarPorId(@PathVariable("id") Long id){
		
		Response<ClienteDtoOut> response = new Response<ClienteDtoOut>();
		
		Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
		
		if(!clienteOpt.isPresent()) {
			response.getErrors().add("Cliente não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new ClienteDtoOut(clienteOpt.get()));
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "remover/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Optional<Cliente> cliente = this.clienteService.buscarPorId(id);
		
		if(!cliente.isPresent()) {
			response.getErrors().add("Erro ao remover o cliente. Cliente não encontrado para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clienteService.remover(id);
		
		response.setData(new String("Cliente com id " + id + " removido com sucesso!"));
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "atualizar/{id}")
	public ResponseEntity<Response<ClienteDtoOut>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody ClienteDtoIn clienteDtoIn, BindingResult result) throws ParseException {
		
		Response<ClienteDtoOut> response = new Response<ClienteDtoOut>();
		
		clienteValidador.isPodeAtualizar(clienteDtoIn.getCliente(), result);
		
		clienteDtoIn.setId(id);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		ClienteDtoOut clienteDtoOut = new ClienteDtoOut(clienteService.persistir(clienteDtoIn.getCliente()));
		
		response.setData(clienteDtoOut);
		
		return ResponseEntity.ok(response);
	}
	
}
