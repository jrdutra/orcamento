package com.fsma.app.validador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.fsma.app.entities.Empresa;
import com.fsma.app.response.Response;
import com.fsma.app.services.EmpresaService;

@Component
public class EmpresaValidador {
	
	@Autowired
	private EmpresaService empresaService;
	
	public boolean isNaoPodeIncluir(Empresa empresa, BindingResult result) {
		
		//Restrição 1
		// CNPJ informado já existe
		Optional<Empresa> empresaOptional = empresaService.buscarPorCnpj(empresa.getCnpj());
		if(empresaOptional.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa já existente."));
		}
		
		//Restrição 2
		//CNPJ não pode ser vazio
		if(empresa.getCnpj().isEmpty()) {
			result.addError(new ObjectError("empresa", "O CNPJ não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CNPJ deve conter 14 caracteres
		if(empresa.getCnpj().length() != 14) {
			result.addError(new ObjectError("empresa", "O CNPJ deve conter 14 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Razao Social não pode ser vazia
		if(empresa.getRazaoSocial().isEmpty()) {
			result.addError(new ObjectError("empresa", "A Razão social não pode ser vazia."));
		}
		
		//Restrição 3.1
		//A Razão social deve conter no máximo 60 caracteres.
		if(empresa.getRazaoSocial().length()<3 || empresa.getRazaoSocial().length()>60) {
			result.addError(new ObjectError("empresa", "A Razão social deve conter entre 3 e 60 caracteres."));
		}
		
		return result.hasErrors();
	}
    public boolean isPodeAtualizar(Empresa empresa, BindingResult result) {
		
		//Restrição 0
		//Id para modificação vazia
		if(empresa.getId()==null) {
			result.addError(new ObjectError("empresa:", "ID da empresa informado é invalido"));
			return false;
		}
		
		//Restrição 1
		//Não existe empresa com o id informado
		Optional<Empresa> empresaOpt = this.empresaService.buscarPorId(empresa.getId());
		if(!empresaOpt.isPresent()) {
			result.addError(new ObjectError("empresa:", "Empresa não encontrada, id inexistente."));
		}
		
		//Restrição 2
		//CNPJ não pode ser vazio
		if(empresa.getCnpj().isEmpty()) {
			result.addError(new ObjectError("empresa", "O CNPJ não pode ser vazio."));
		}
		
		//Restrição 2.2
		//CNPJ deve conter 14 caracteres
		if(empresa.getCnpj().length() != 14) {
			result.addError(new ObjectError("empresa", "O CNPJ deve conter 14 caracteres, sem pontos ou traços."));
		}
		
		//Restrição 3
		//Razao Social não pode ser vazia
		if(empresa.getRazaoSocial().isEmpty()) {
			result.addError(new ObjectError("empresa", "A Razão social não pode ser vazia."));
		}
		
		//Restrição 3.1
		//A Razão social deve conter no máximo 60 caracteres.
		if(empresa.getRazaoSocial().length()<3 || empresa.getRazaoSocial().length()>60) {
			result.addError(new ObjectError("empresa", "A Razão social deve conter entre 3 e 60 caracteres."));
		}
		
		return result.hasErrors();
	}
	public boolean isNaoPodeRemover(Optional<Empresa> empresa, Response<String> response) {
		//Restrição 1
		//Verifica se a empresa existe
		if(!empresa.isPresent()) {
			response.getErrors().add("Erro ao remover a empresa. Empresa não encontrada");
			return true;
		}
		//Restrição 2
		//Verifica se a empresa possui empregados
		if(empresa.get().getEmpregados().size()!=0) {
			System.out.println(empresa.get().getEmpregados()); 
			response.getErrors().add("A empressa não pode ser removida pois possui empregados.");
			return true;
		}
		return false;
	}
}
