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
public class ItemsCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
	@JsonIgnore
    private CadastroCompra compra;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @JsonIgnore
    private CadastroProduto produto;
    
    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;
    @Column
    public BigDecimal Subtotal;
    
    

    

	public BigDecimal getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CadastroCompra getCompra() {
		return compra;
	}

	public void setCompra(CadastroCompra compra) {
		this.compra = compra;
	}

	public CadastroProduto getProduto() {
		return produto;
	}

	public void setProduto(CadastroProduto produto) {
		this.produto = produto;
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

	


}
