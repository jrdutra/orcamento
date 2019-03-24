package com.fsma.app.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Response<ClienteDto>> cadastrar(@Valid @RequestBody ClienteDto clienteDto,
			BindingResult result) throws NoSuchAlgorithmException{
		
		log.info("Cadastrando Cliente: {}", clienteDto.toString());
		
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		validarDadosExistentes(clienteDto, result);
		
		Cliente cliente = this.converterDtoParaCliente(clienteDto);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados do cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clienteService.persistir(cliente);
		
		response.setData(this.converterClienteParaDto(cliente));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "buscarid/{id}")
	public ResponseEntity<Response<ClienteDto>> buscarPorId(@PathVariable("id") Long id){
		log.info("Buscando Cliente Pelo ID: {}", id);
		
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		
		if(!cliente.isPresent()) {
			log.info("Cliente não encontrado para o ID: {}", id);
			response.getErrors().add("Cliente não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterClienteParaDto(cliente.get()));
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "remover/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		log.info("Removendo cliente: id={}", id);
		Response<String> response = new Response<String>();
		Optional<Cliente> cliente = this.clienteService.buscarPorId(id);
		
		if(!cliente.isPresent()) {
			log.info("Erro ao remover o cliente, ID: {} é inexistente.", id);
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
		
		log.info("Atualizando cliente: {}", clienteDto.toString());
		
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		this.validarCliente(clienteDto, result);
		
		clienteDto.setId(id);
		
		Cliente cliente = this.converterDtoParaCliente(clienteDto);
		
		if(result.hasErrors()) {
			log.error("Erro validando cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		cliente = clienteService.persistir(cliente);
		
		response.setData(this.converterClienteParaDto(cliente));
		
		return ResponseEntity.ok(response);
	}
	
	private void validarCliente(ClienteDto clienteDto, BindingResult result) {
		if(clienteDto.getId()==null) {
			log.info("Cliente inválido: Não informado o ID do Cliente {}", clienteDto.toString());
			result.addError(new ObjectError("Cliente:", "ID do cliente informado é invalido"));
			return;
		}
		log.info("Validando cliente id {}: ", clienteDto.getId());
		Optional<Cliente> cliente = this.clienteService.buscarPorId(clienteDto.getId());
		if(!cliente.isPresent()) {
			result.addError(new ObjectError("Cliente:", "Cliente não encontrado, id inexistente."));
		}
		
	}

	private ClienteDto converterClienteParaDto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setBairro(cliente.getBairro());
		clienteDto.setCelular(cliente.getCelular());
		clienteDto.setCidade(cliente.getCidade());
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setEstado(cliente.getEstado());
		clienteDto.setLogradouro(cliente.getLogradouro());
		clienteDto.setNome(cliente.getNome());
		return clienteDto;
	}

	private Cliente converterDtoParaCliente(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		log.info("CLIENTE COM ID = {}: ", clienteDto.getId());
		if(clienteDto.getId()!=null) {
			cliente.setId(clienteDto.getId());
			
		}
		cliente.setBairro(clienteDto.getBairro());
		cliente.setCelular(clienteDto.getCelular());
		cliente.setCidade(clienteDto.getCidade());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setEstado(clienteDto.getEstado());
		cliente.setLogradouro(clienteDto.getLogradouro());
		cliente.setNome(clienteDto.getNome());
		return cliente;
	}

	private void validarDadosExistentes(ClienteDto clienteDto, BindingResult result) {
		this.clienteService.buscarPorCpf(clienteDto.getCpf()).ifPresent(cli -> result.addError(new ObjectError("cliente", "Cliente já existente")));
		
	}
	
}
