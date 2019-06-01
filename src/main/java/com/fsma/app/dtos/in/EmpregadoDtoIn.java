package com.fsma.app.dtos.in;

import com.fsma.app.entities.Empregado;

public class EmpregadoDtoIn {
	
	private Empregado empregado;
	
	public EmpregadoDtoIn() {
		empregado = new Empregado();
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


	public Empregado getEmpregado() {
		return this.empregado;
	}
	
}
