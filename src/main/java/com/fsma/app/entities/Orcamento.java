package com.fsma.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "orcamento")
public class Orcamento {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Cliente cliente;
	private Empresa empresa;
	private Double valorTotal;
	private List<ItemOrcamento> itensOrcamento;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "Orcamento [cliente=" + cliente + ", empresa=" + empresa + ", valorTotal=" + valorTotal + "]";
	}

	@OneToMany(mappedBy = "orcamento", 
			   targetEntity = ItemOrcamento.class, 
			   fetch = FetchType.LAZY, 
			   cascade = CascadeType.ALL)
	public List<ItemOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}
	public void setItensOrcamento(List<ItemOrcamento> itensOrcamento) {
		this.itensOrcamento = itensOrcamento;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
