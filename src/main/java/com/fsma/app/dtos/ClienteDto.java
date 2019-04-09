package com.fsma.app.dtos;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fsma.app.entities.Cliente;

public class ClienteDto {

	private Long id;
	private String cpf;
	private String nome;
	private String celular;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String email;
	
	public static Cliente toCliente(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		if(clienteDto.getId()!=null) {
			cliente.setId(clienteDto.getId());
		}
		cliente.setBairro(clienteDto.getBairro());
		cliente.setCelular(clienteDto.getCelular());
		cliente.setCidade(clienteDto.getCidade());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setEstado(clienteDto.getEstado());
		cliente.setLogradouro(clienteDto.getLogradouro());
		cliente.setNome(clienteDto.getNome());
		return cliente;
	}
	
	public static ClienteDto toClienteDto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setBairro(cliente.getBairro());
		clienteDto.setCelular(cliente.getCelular());
		clienteDto.setCidade(cliente.getCidade());
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setEstado(cliente.getEstado());
		clienteDto.setLogradouro(cliente.getLogradouro());
		clienteDto.setNome(cliente.getNome());
		return clienteDto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@CPF(message="CPF inválido.")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Length(min=5, max = 40, message = "Email deve conter entre 5 e 40 caracteres.")
	@Email(message="Email inválido.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "CadastroClienteDto [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", celular=" + celular
				+ ", logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado
				+ ", email=" + email + "]";
	}
}
