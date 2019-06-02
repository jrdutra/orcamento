package com.fsma.app.dtos.in;

import java.util.List;

import com.fsma.app.entities.Empregado;
import com.fsma.app.entities.Empresa;

public class EmpresaDtoIn {
	
	private Empresa empresa;
	
	public EmpresaDtoIn() {
		empresa = new Empresa();
	}

	public Long getId() {
		return empresa.getId();
	}

	public void setId(Long id) {
		empresa.setId(id);
	}

	public String getNomeFantasia() {
		return empresa.getNomeFantasia();
	}

	public void setNomeFantasia(String nomeFantasia) {
		empresa.setNomeFantasia(nomeFantasia);
	}

	public String getRazaoSocial() {
		return empresa.getRazaoSocial();
	}

	public void setRazaoSocial(String razaoSocial) {
		empresa.setRazaoSocial(razaoSocial);
	}

	public String getCnpj() {
		return empresa.getCnpj();
	}

	public void setCnpj(String cnpj) {
		empresa.setCnpj(cnpj);
	}

	public String getLogradouro() {
		return empresa.getLogradouro();
	}

	public void setLogradouro(String logradouro) {
		empresa.setLogradouro(logradouro);
	}

	public String getBairro() {
		return empresa.getBairro();
	}

	public void setBairro(String bairro) {
		empresa.setBairro(bairro);
	}

	public String getCidade() {
		return empresa.getCidade();
	}

	public void setCidade(String cidade) {
		empresa.setCidade(cidade);
	}

	public String getEstado() {
		return empresa.getEstado();
	}

	public void setEstado(String estado) {
		empresa.setEstado(estado);
	}

	public String getEmail() {
		return empresa.getEmail();
	}

	public void setEmail(String email) {
		empresa.setEmail(email);
	}
	
	
	
	public List<Empregado> getEmpregados() {
		return empresa.getEmpregados();
	}

	public void setEmpregados(List<Empregado> empregados) {
		empresa.setEmpregados(empregados);
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}
	
}
