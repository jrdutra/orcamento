package com.fsma.app.validador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.fsma.app.entities.Empregado;
import com.fsma.app.services.EmpregadoService;

@Component
public class EmpregadoValidador {
	
	@Autowired
	private EmpregadoService empregadoService;
	
	public boolean isNaoPodeIncluir(Empregado empregado, BindingResult result) {
		//Restrição 1
		// CPF informado já existe
		
		Optional<Empregado> empregadoOptional = empregadoService.buscarPorCpf(empregado.getCpf());
		
		if(empregadoOptional.isPresent()) {
			result.addError(new ObjectError("empregado", "Empregado já existente."));
		}
		
		//Restrição 2
		//CPF não pode ser vazio
		
		if(empregado.getCpf().isEmpty()) {
			result.addError(new ObjectError("empregado", "O CPF não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CPF deve conter 11 caracteres
		if(empregado.getCpf().length() != 11) {
			result.addError(new ObjectError("empregado", "O CPF deve conter 11 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(empregado.getNome().isEmpty()) {
			result.addError(new ObjectError("empregado", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 60 caracteres.
		if(empregado.getNome().length()<3 || empregado.getNome().length()>60) {
			result.addError(new ObjectError("empregado", "O Nome deve conter entre 3 e 60 caracteres."));
		}
		
		//Restrição 4
		//Telefone não pode ser vazio
		if(empregado.getTelefone().isEmpty()) {
			result.addError(new ObjectError("empregado", "O Telefone não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 15 caracteres.
		if(empregado.getTelefone().length()<8 || empregado.getTelefone().length()>15) {
			result.addError(new ObjectError("empregado", "Telefone deve conter entre 8 e 15 caracteres."));
		}
		
		
		return result.hasErrors();
	}
	
public boolean isPodeAtualizar(Empregado empregado, BindingResult result) {
		
		//Restrição 0
		//Id para modificação vazia
		if(empregado.getId()==null) {
			result.addError(new ObjectError("empregado:", "ID do empregado informado é invalido"));
			return false;
		}
		
		//Restrição 1
		//Não existe fornecedor com o id informado
		Optional<Empregado> empregadoOpt = this.empregadoService.buscarPorId(empregado.getId());
		if(!empregadoOpt.isPresent()) {
			result.addError(new ObjectError("empregado:", "Empregado não encontrado, id inexistente."));
		}
		
		//Restrição 2
		//CPF não pode ser vazio
		if(empregado.getCpf().isEmpty()) {
			result.addError(new ObjectError("empregado", "O CPF não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CPF deve conter 11 caracteres
		if(empregado.getCpf().length() != 11) {
			result.addError(new ObjectError("empregado", "O CPF deve conter 11 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Nome não pode ser vazio
		if(empregado.getNome().isEmpty()) {
			result.addError(new ObjectError("fornecedor", "O Nome não pode ser vazio."));
		}
		
		//Restrição 3.1
		//O Nome deve conter no máximo 60 caracteres.
		if(empregado.getNome().length()<3 || empregado.getNome().length()>60) {
			result.addError(new ObjectError("empregado", "O Nome deve conter entre 3 e 60 caracteres."));
		}
		
		//Restrição 4
		//Telefone não pode ser vazio
		if(empregado.getTelefone().isEmpty()) {
			result.addError(new ObjectError("empregado", "O Telefone não pode ser vazio."));
		}
		
		//Restrição 4.1
		//Email deve conter entre 5 e 15 caracteres.
		if(empregado.getTelefone().length()<8 || empregado.getTelefone().length()>15) {
			result.addError(new ObjectError("empregado", "Telefone deve conter entre 8 e 15 caracteres."));
		}
		
		return result.hasErrors();
	}
	
}
