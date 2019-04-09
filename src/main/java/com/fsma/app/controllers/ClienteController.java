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

import com.fsma.app.dtos.ClienteDto;
import com.fsma.app.entities.Cliente;
import com.fsma.app.response.Response;
import com.fsma.app.services.ClienteService;
import com.fsma.app.validador.ClienteDtoValidador;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteDtoValidador clienteDtoValidador;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Response<ClienteDto>> cadastrar(@Valid @RequestBody ClienteDto clienteDto,
			BindingResult result) throws NoSuchAlgorithmException{
		
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		clienteDtoValidador.isPodeIncluir(clienteDto, result);
		
		Cliente cliente = ClienteDto.toCliente(clienteDto);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clienteService.persistir(cliente);
		
		response.setData(ClienteDto.toClienteDto(cliente));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarid/{id}")
	public ResponseEntity<Response<ClienteDto>> buscarPorId(@PathVariable("id") Long id){
		
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		
		if(!cliente.isPresent()) {
			response.getErrors().add("Cliente não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(ClienteDto.toClienteDto(cliente.get()));
		
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
	public ResponseEntity<Response<ClienteDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody ClienteDto clienteDto, BindingResult result) throws ParseException {
		
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		clienteDtoValidador.isPodeAtualizar(clienteDto, result);
		
		clienteDto.setId(id);
		
		Cliente cliente = ClienteDto.toCliente(clienteDto);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		cliente = clienteService.persistir(cliente);
		
		response.setData(ClienteDto.toClienteDto(cliente));
		
		return ResponseEntity.ok(response);
	}
	
}
