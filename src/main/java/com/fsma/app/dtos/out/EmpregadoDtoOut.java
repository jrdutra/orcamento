package com.fsma.app.dtos.out;

import com.fsma.app.entities.Empregado;

public class EmpregadoDtoOut {
	
	private Empregado empregado;
	
	public EmpregadoDtoOut() {
		empregado = new Empregado();
	}
	
	public EmpregadoDtoOut(Empregado empregado) {
		this.empregado = empregado;
	}

	public Long getId() {
		return empregado.getId();
	}

	public void setId(Long id) {
		empregado.setId(id);
	}

	public String getNome() {
		return empregado.getNome();
	}

	public void setNome(String nome) {
		empregado.setNome(nome);
	}


	public String getTelefone() {
		return empregado.getTelefone();
	}

	public void setTelefone(String telefone) {
		empregado.setTelefone(telefone);
	}

	public String getCpf() {
		return empregado.getCpf();
	}

	public void setCpf(String cpf) {
		empregado.setCpf(cpf);
	}

	public String getFuncao() {
		return empregado.getFuncao();
	}

	public void setFuncao(String funcao) {
		empregado.setFuncao(funcao);
	}

	public Long getEmpresa() {
		return empregado.getEmpresa().getId();
	}

}
