package com.fsma.app.validador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.fsma.app.entities.Cliente;
import com.fsma.app.services.ClienteService;

@Component
public class ClienteValidador {

	@Autowired
	private ClienteService clienteService;
	
	public boolean isNaoPodeIncluir(Cliente cliente, BindingResult result) {
		
		//Restrição 1
		// CPF informado já existe
		Optional<Cliente> clienteOptional = clienteService.buscarPorCpf(cliente.getCpf());
		if(clienteOptional.isPresent()) {
			result.addError(new ObjectError("cliente", "Cliente já existente."));
		}
		
		//Restrição 2
		//CPF não pode ser vazio
		if(cliente.getCpf().isEmpty()) {
			result.addError(new ObjectError("cliente", "O CPF não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CPF deve conter 11 caracteres
		if(cliente.getCpf().length() != 11) {
			result.addError(new ObjectError("cliente", "O CPF deve conter 11 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(cliente.getNome().isEmpty()) {
			result.addError(new ObjectError("cliente", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 50 caracteres.
		if(cliente.getNome().length()<3 || cliente.getNome().length()>50) {
			result.addError(new ObjectError("cliente", "O Nome deve conter entre 3 e 50 caracteres."));
		}
		
		//Restrição 4
		//Email não pode ser vazio
		if(cliente.getEmail().isEmpty()) {
			result.addError(new ObjectError("cliente", "O Email não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 40 caracteres.
		if(cliente.getEmail().length()<5 || cliente.getEmail().length()>40) {
			result.addError(new ObjectError("cliente", "Email deve conter entre 5 e 40 caracteres."));
		}
	
		return result.hasErrors();
	}
	
	public boolean isPodeAtualizar(Cliente cliente, BindingResult result) {
		
		//Restrição 0
		//Id para modificação vazia
		if(cliente.getId()==null) {
			result.addError(new ObjectError("cliente:", "ID do cliente informado é invalido"));
			return false;
		}
		
		//Restrição 1
		//Não existe cliente com o id informado
		Optional<Cliente> clienteOpt = this.clienteService.buscarPorId(cliente.getId());
		if(!clienteOpt.isPresent()) {
			result.addError(new ObjectError("cliente:", "Cliente não encontrado, id inexistente."));
		}
		
		//Restrição 2
		//CPF não pode ser vazio
		if(cliente.getCpf().isEmpty()) {
			result.addError(new ObjectError("cliente", "O CPF não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CPF deve conter 11 caracteres
		if(cliente.getCpf().length() != 11) {
			result.addError(new ObjectError("cliente", "O CPF deve conter 11 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(cliente.getNome().isEmpty()) {
			result.addError(new ObjectError("cliente", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 50 caracteres.
		if(cliente.getNome().length()<3 || cliente.getNome().length()>50) {
			result.addError(new ObjectError("cliente", "O Nome deve conter entre 3 e 50 caracteres."));
		}
		
		//Restrição 4
		//Email não pode ser vazio
		if(cliente.getEmail().isEmpty()) {
			result.addError(new ObjectError("cliente", "O Email não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 40 caracteres.
		if(cliente.getEmail().length()<5 || cliente.getEmail().length()>40) {
			result.addError(new ObjectError("cliente", "Email deve conter entre 5 e 40 caracteres."));
		}
		
		
		return result.hasErrors();
	}
}
