package com.fsma.app.dtos.in;

import com.fsma.app.entities.Fornecedor;

public class FornecedorDtoIn {
	
	private Fornecedor fornecedor;
	
	public FornecedorDtoIn() {
		fornecedor = new Fornecedor();
	}
	
	public FornecedorDtoIn(Fornecedor fornecedor) {
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

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}
	
}
