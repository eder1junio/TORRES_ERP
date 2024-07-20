package com.example.demo.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class compraModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@ManyToOne
	public fornecedorModelo fonecerdor;
	
	@ManyToMany
	List<produtoModelo>Produto;
	
	@ManyToOne
	public tipoPagamentoModelo pagamento;
	
	public LocalDate dataDaCompra;
	public Float valorTotlaDosProdutos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public fornecedorModelo getFonecerdor() {
		return fonecerdor;
	}
	public void setFonecerdor(fornecedorModelo fonecerdor) {
		this.fonecerdor = fonecerdor;
	}
	public List<produtoModelo> getProduto() {
		return Produto;
	}
	public void setProduto(List<produtoModelo> produto) {
		Produto = produto;
	}
	public tipoPagamentoModelo getPagamento() {
		return pagamento;
	}
	public void setPagamento(tipoPagamentoModelo pagamento) {
		this.pagamento = pagamento;
	}
	public LocalDate getDataDaCompra() {
		return dataDaCompra;
	}
	public void setDataDaCompra(LocalDate dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}
	public Float getValorTotlaDosProdutos() {
		return valorTotlaDosProdutos;
	}
	public void setValorTotlaDosProdutos(Float valorTotlaDosProdutos) {
		this.valorTotlaDosProdutos = valorTotlaDosProdutos;
	}
	

}
