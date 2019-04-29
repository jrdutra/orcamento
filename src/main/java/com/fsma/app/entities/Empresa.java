package com.fsma.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name= "nome_fantasia", nullable = true, length = 60)
	private String nomeFantasia;
	
	@Column(name= "razao_social", nullable = false, length = 60)
	private String razaoSocial;
	
	@Column(name= "cnpj", nullable = false)
	private String Cnpj;
	
	@Column(name= "logradouro", nullable = true, length = 100)
	private String logradouro;
	
	@Column(name= "bairro", nullable = true, length = 50)
	private String bairro;
	
	@Column(name= "cidade", nullable = true, length = 50)
	private String cidade;
	
	@Column(name= "estado", nullable = true, length = 20)
	private String estado;
	
	@Column(name= "email", nullable = true, length = 50)
	private String email;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getCnpj() {
		return Cnpj;
	}
	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
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
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial + ", Cnpj="
				+ Cnpj + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado="
				+ estado + ", email=" + email + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
