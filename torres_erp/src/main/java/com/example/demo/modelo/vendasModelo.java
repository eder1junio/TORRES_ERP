package com.example.demo.modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class vendasModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String cliente;
	public Float valorTotalDaVenda;
	public String tipoPagamento;
	public LocalDate data;
	
	@ManyToAny()
	 private List<produtoModelo> produto;
	 	 
	
	public List<produtoModelo> getProduto() {
		return produto;
	}
	public void setProduto(List<produtoModelo> produto) {
		this.produto = produto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Float getValorTotalDaVenda() {
		return valorTotalDaVenda;
	}
	public void setValorTotalDaVenda(Float valorTotalDaVenda) {
		this.valorTotalDaVenda = valorTotalDaVenda;
	}
	
	
	

}
