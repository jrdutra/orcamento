package com.fsma.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empregado")
public class Empregado {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String telefone;
	private String funcao;
	private Empresa empresa;
	private List<OrdemDeTrabalho> ordemDeTrabalho;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name= "nome", nullable = false)
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
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@OneToMany(mappedBy = "empregado", 
			   targetEntity = OrdemDeTrabalho.class, 
			   fetch = FetchType.LAZY, 
			   cascade = CascadeType.ALL)
	public List<OrdemDeTrabalho> getOrdemDeTrabalho() {
		return ordemDeTrabalho;
	}
	public void setOrdemDeTrabalho(List<OrdemDeTrabalho> ordemDeTrabalho) {
		this.ordemDeTrabalho = ordemDeTrabalho;
	}
	@Override
	public String toString() {
		return "Empregado [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", funcao=" + funcao + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
