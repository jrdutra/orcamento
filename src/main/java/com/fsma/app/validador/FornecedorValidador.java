package com.fsma.app.validador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.fsma.app.entities.Fornecedor;
import com.fsma.app.services.FornecedorService;

@Component
public class FornecedorValidador {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	public boolean isNaoPodeIncluir(Fornecedor fornecedor, BindingResult result) {
		//Restrição 1
		// CNPJ informado já existe
		Optional<Fornecedor> fornecedorOptional = fornecedorService.buscarPorCnpj(fornecedor.getCnpj());
		if(fornecedorOptional.isPresent()) {
			result.addError(new ObjectError("fornecedor", "Fornecedor já existente."));
		}
		
		//Restrição 2
		//CNPJ não pode ser vazio
		if(fornecedor.getCnpj().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O CNPJ não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CNPJ deve conter 14 caracteres
		if(fornecedor.getCnpj().length() != 14) {
			result.addError(new ObjectError("fornecedor", "O CNPJ deve conter 14 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(fornecedor.getNome().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 60 caracteres.
		if(fornecedor.getNome().length()<3 || fornecedor.getNome().length()>60) {
			result.addError(new ObjectError("fornecedor", "O Nome deve conter entre 3 e 60 caracteres."));
		}
		
		//Restrição 4
		//Telefone não pode ser vazio
		if(fornecedor.getTelefone().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O Telefone não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 15 caracteres.
		if(fornecedor.getTelefone().length()<8 || fornecedor.getTelefone().length()>15) {
			result.addError(new ObjectError("telefone", "Telefone deve conter entre 8 e 15 caracteres."));
		}
		
		
		return result.hasErrors();
	}
	
public boolean isPodeAtualizar(Fornecedor fornecedor, BindingResult result) {
		
		//Restrição 0
		//Id para modificação vazia
		if(fornecedor.getId()==null) {
			result.addError(new ObjectError("fornecedor:", "ID do fornecedor informado é invalido"));
			return false;
		}
		
		//Restrição 1
		//Não existe fornecedor com o id informado
		Optional<Fornecedor> fornecedorOpt = this.fornecedorService.buscarPorId(fornecedor.getId());
		if(!fornecedorOpt.isPresent()) {
			result.addError(new ObjectError("fornecedor:", "Fornecedor não encontrado, id inexistente."));
		}
		
		//Restrição 2
		//CNPJ não pode ser vazio
		if(fornecedor.getCnpj().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O CNPJ não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CNPJ deve conter 14 caracteres
		if(fornecedor.getCnpj().length() != 14) {
			result.addError(new ObjectError("fornecedor", "O CNPJ deve conter 14 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(fornecedor.getNome().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 60 caracteres.
		if(fornecedor.getNome().length()<3 || fornecedor.getNome().length()>60) {
			result.addError(new ObjectError("fornecedor", "O Nome deve conter entre 3 e 60 caracteres."));
		}
		
		//Restrição 4
		//Telefone não pode ser vazio
		if(fornecedor.getTelefone().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O Telefone não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 15 caracteres.
		if(fornecedor.getTelefone().length()<8 || fornecedor.getTelefone().length()>15) {
			result.addError(new ObjectError("telefone", "Telefone deve conter entre 8 e 15 caracteres."));
		}
		
		return result.hasErrors();
	}
	
}
