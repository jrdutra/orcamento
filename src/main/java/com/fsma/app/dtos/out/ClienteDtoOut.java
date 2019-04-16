package com.fsma.app.dtos.out;


import com.fsma.app.entities.Cliente;

public class ClienteDtoOut {
	
	private Cliente cliente;
	
	public ClienteDtoOut() {
		cliente = new Cliente();
	}
	public ClienteDtoOut(Cliente cliente) {
		this.cliente = cliente;
	}
	public Long getId() {
		return cliente.getId();
	}
	public void setId(long id) {
		cliente.setId(id);
	}
	public String getCpf() {
		return cliente.getCpf();
	}
	public void setCpf(String cpf) {
		cliente.setCpf(cpf);
	}
	public String getNome() {
		return cliente.getNome();
	}
	public void setNome(String nome) {
		cliente.setNome(nome);
	}
	public String getCelular() {
		return cliente.getCelular();
	}
	public void setCelular(String celular) {
		cliente.setCelular(celular);
	}
	public String getLogradouro() {
		return cliente.getLogradouro();
	}
	public void setLogradouro(String logradouro) {
		cliente.setLogradouro(logradouro);
	}
	public String getBairro() {
		return cliente.getBairro();
	}
	public void setBairro(String bairro) {
		cliente.setBairro(bairro);
	}
	public String getCidade() {
		return cliente.getCidade();
	}
	public void setCidade(String cidade) {
		cliente.setCidade(cidade);
	}
	public String getEstado() {
		return cliente.getEstado();
	}
	public void setEstado(String estado) {
		cliente.setEstado(estado);
	}
	public String getEmail() {
		return cliente.getEmail();
	}
	public void setEmail(String email) {
		cliente.setEmail(email);
	}	
}
