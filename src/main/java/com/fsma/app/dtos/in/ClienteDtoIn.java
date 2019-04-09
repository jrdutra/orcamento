package com.fsma.app.dtos.in;


import com.fsma.app.entities.Cliente;

public class ClienteDtoIn {
	
	private Cliente cliente;
	
	public ClienteDtoIn() {
		cliente = new Cliente();
	}

	public void setId(long id) {
		cliente.setId(id);
	}

	public void setCpf(String cpf) {
		cliente.setCpf(cpf);
	}

	public void setNome(String nome) {
		cliente.setNome(nome);
	}

	public void setCelular(String celular) {
		cliente.setCelular(celular);
	}

	public void setLogradouro(String logradouro) {
		cliente.setLogradouro(logradouro);
	}

	public void setBairro(String bairro) {
		cliente.setBairro(bairro);
	}

	public void setCidade(String cidade) {
		cliente.setCidade(cidade);
	}

	public void setEstado(String estado) {
		cliente.setEstado(estado);
	}

	public void setEmail(String email) {
		cliente.setEmail(email);
	}

	public Cliente getCliente( ) {
		return cliente;
	}
	
}
