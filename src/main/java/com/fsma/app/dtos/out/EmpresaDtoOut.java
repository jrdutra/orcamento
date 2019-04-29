package com.fsma.app.dtos.out;

import com.fsma.app.entities.Empresa;

public class EmpresaDtoOut {
	private Empresa empresa;
	
	public EmpresaDtoOut() {
		empresa = new Empresa();
	}
	
	public EmpresaDtoOut(Empresa empresa) {
		this.empresa = empresa;
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
	
	
}
