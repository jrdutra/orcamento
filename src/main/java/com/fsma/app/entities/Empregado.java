package com.fsma.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empregado")
public class Empregado {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name= "nome", nullable = false, length = 50)
	private String nome;
	@Column(name= "cpf", nullable = false, length = 11)
	private String cpf;
	@Column(name = "telefone", nullable = true, length = 15)
	private String telefone;
	@Column(name= "funcao", nullable = false, length = 50)
	private String funcao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return "Empregado [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", funcao="
				+ funcao + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
