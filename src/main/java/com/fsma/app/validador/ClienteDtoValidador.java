package com.fsma.app.validador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.fsma.app.dtos.ClienteDto;
import com.fsma.app.entities.Cliente;
import com.fsma.app.services.ClienteService;

@Component
public class ClienteDtoValidador {

	@Autowired
	private ClienteService clienteService;
	
	public boolean isPodeIncluir(ClienteDto clienteDto, BindingResult result) {
		
		//Restrição 1
		// CPF informado já existe
		Optional<Cliente> clienteOptional = clienteService.buscarPorCpf(clienteDto.getCpf());
		if(clienteOptional.isPresent()) {
			result.addError(new ObjectError("cliente", "Cliente já existente."));
		}
		
		//Restrição 2
		//CPF não pode ser vazio
		if(clienteDto.getCpf().isEmpty()) {
			result.addError(new ObjectError("cliente", "O CPF não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CPF deve conter 11 caracteres
		if(clienteDto.getCpf().length() != 11) {
			result.addError(new ObjectError("cliente", "O CPF deve conter 11 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(clienteDto.getNome().isEmpty()) {
			result.addError(new ObjectError("cliente", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 50 caracteres.
		if(clienteDto.getNome().length()<3 || clienteDto.getNome().length()>50) {
			result.addError(new ObjectError("cliente", "O Nome deve conter entre 3 e 50 caracteres."));
		}
		
		//Restrição 4
		//Email não pode ser vazio
		if(clienteDto.getEmail().isEmpty()) {
			result.addError(new ObjectError("cliente", "O Email não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 40 caracteres.
		if(clienteDto.getEmail().length()<5 || clienteDto.getEmail().length()>40) {
			result.addError(new ObjectError("cliente", "Email deve conter entre 5 e 40 caracteres."));
		}
	
		return result.hasErrors();
	}
	
	public boolean isPodeAtualizar(ClienteDto clienteDto, BindingResult result) {
		
		//Restrição 1
		//Id para modificação vazia
		if(clienteDto.getId()==null) {
			result.addError(new ObjectError("cliente:", "ID do cliente informado é invalido"));
			return false;
		}
		
		//Restrição 2
		//Não existe cliente com o id informado
		Optional<Cliente> cliente = this.clienteService.buscarPorId(clienteDto.getId());
		if(!cliente.isPresent()) {
			result.addError(new ObjectError("cliente:", "Cliente não encontrado, id inexistente."));
		}
		
		return result.hasErrors();
	}
}
