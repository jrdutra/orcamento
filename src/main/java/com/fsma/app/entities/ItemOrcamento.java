package com.fsma.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemorcamento")
public class ItemOrcamento {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer quantidade;
	private Double valorTotal;
	private Orcamento orcamento;
	private Material material;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@ManyToOne
	@JoinColumn(name = "orcamento_id")
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	@ManyToOne
	@JoinColumn(name = "material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}

	
	@Override
	public String toString() {
		return "ItemOrcamento [quantidade=" + quantidade + ", valorTotal=" + valorTotal + ", orcamento=" + orcamento
				+ ", material=" + material + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
