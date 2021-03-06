package com.fsma.app.dtos.out;

import com.fsma.app.entities.Fornecedor;

public class FornecedorDtoOut {
	private Fornecedor fornecedor;
	
	public FornecedorDtoOut() {
		fornecedor = new Fornecedor();
	}
	
	public FornecedorDtoOut(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Long getId() {
		return fornecedor.getId();
	}

	public void setId(Long id) {
		fornecedor.setId(id);
	}

	public String getNome() {
		return fornecedor.getNome();
	}

	public void setNome(String nome) {
		fornecedor.setNome(nome);
	}

	public String getEndereco() {
		return fornecedor.getEndereco();
	}

	public void setEndereco(String endereco) {
		fornecedor.setEndereco(endereco);
	}

	public String getTelefone() {
		return fornecedor.getTelefone();
	}

	public void setTelefone(String telefone) {
		fornecedor.setTelefone(telefone);
	}

	public String getCnpj() {
		return fornecedor.getCnpj();
	}

	public void setCnpj(String cnpj) {
		fornecedor.setCnpj(cnpj);
	}
	
}
