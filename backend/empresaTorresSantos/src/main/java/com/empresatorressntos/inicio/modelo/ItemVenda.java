package com.empresatorressntos.inicio.modelo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemVenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer quantidade;
	
	@Column
	private String nome;
	
	@Column
	private BigDecimal precoUnitario;
	
	@ManyToOne
	@JoinColumn(name = "vendaID")
	@JsonIgnore
	private cadastroVenda venda;
	
	@ManyToOne
	@JoinColumn(name =  "produtoID")
	@JsonIgnore
	private CadastroProduto produto;
	
	public BigDecimal getSubTotal() {
		return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public cadastroVenda getVenda() {
		return venda;
	}

	public void setVenda(cadastroVenda venda) {
		this.venda = venda;
	}

	public CadastroProduto getProduto() {
		return produto;
	}

	public void setProduto(CadastroProduto produto) {
		this.produto = produto;
	}

	

}
