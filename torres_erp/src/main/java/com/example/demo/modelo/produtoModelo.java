package com.example.demo.modelo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class produtoModelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome;
	public String descricao;
	public Float valorProdutoCompra;
	public BigInteger codigoBarras;
	public Float valorVendaProduto;

	
	
	

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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Float getValorProdutoCompra() {
		return valorProdutoCompra;
	}
	public void setValorProdutoCompra(Float valorProdutoCompra) {
		this.valorProdutoCompra = valorProdutoCompra;
	}
	public BigInteger getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(BigInteger codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Float getValorVendaProduto() {
		return valorVendaProduto;
	}
	public void setValorVendaProduto(Float valorVendaProduto) {
		this.valorVendaProduto = valorVendaProduto;
	}
	
	
	

}
