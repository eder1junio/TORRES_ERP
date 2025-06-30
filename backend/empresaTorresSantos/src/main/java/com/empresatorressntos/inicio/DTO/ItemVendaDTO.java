package com.empresatorressntos.inicio.DTO;

import java.math.BigDecimal;

import jakarta.persistence.Entity;



public class ItemVendaDTO {
	
	
	
	private Integer quantidade;
	

	private BigDecimal precoUnitario;
	
	private Long produtoID;
	
	private BigDecimal subtotal;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Long getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(Long produtoID) {
		this.produtoID = produtoID;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}



	
}
