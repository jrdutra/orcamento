package com.fsma.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
    private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @Column(name= "nome", nullable = false, length = 60)
    private String nome;
    
    @Column(name= "endereco", nullable = true, length = 100)
    private String endereco;
    
    @Column(name= "telefone", nullable = false, length = 15)
    private String telefone;
    
    @Column(name= "cnpj", nullable = false, length = 14)
    private String cnpj;
	
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone
				+ ", cnpj=" + cnpj + "]";
	}
}
