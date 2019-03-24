package com.fsma.app.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Cpf não pode ser vazio.")
	@Length(min=3, max=11, message = "O CPF deve conter 11 caracteres, sem pontos ou traços.")
	@CPF(message="CPF inválido.")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min=3, max=50, message = "O Nome deve conter no máximo 50 caracteres.")
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
	@NotEmpty(message = "Email não pode ser vazio.")
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
