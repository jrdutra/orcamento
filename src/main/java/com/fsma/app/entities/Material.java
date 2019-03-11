package com.fsma.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String descricao;
	private Double valorUnitario;
	private Unidade unidade;
	private Marca marca;
	private Tipo tipo;
	private Fornecedor fornecedor;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	@ManyToOne
	@JoinColumn(name = "unidade_id")
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	@ManyToOne
	@JoinColumn(name = "marca_id")
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Override
	public String toString() {
		return "Material [descricao=" + descricao + ", valorUnitario=" + valorUnitario + ", unidade=" + unidade
				+ ", marca=" + marca + ", tipo=" + tipo + ", fornecedor=" + fornecedor + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
