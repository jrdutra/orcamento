package com.fsma.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name= "cpf", nullable = false, length = 11)
	private String cpf;
	@Column(name= "nome", nullable = false, length = 50)
	private String nome;
	@Column(name = "celular", nullable = true, length = 15)
	private String celular;
	@Column(name = "logradouro", nullable = true, length = 100)
	private String logradouro;
	@Column(name = "bairro", nullable = true, length = 50)
	private String bairro;
	@Column(name = "cidade", nullable = true, length = 50)
	private String cidade;
	@Column(name = "estado", length = 30)
	private String estado;
	@Column(name = "email", nullable = true, length = 40)
	private String email;
	
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", celular=" + celular + ", logradouro="
				+ logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", email=" + email
				+ "]";
	}
}
