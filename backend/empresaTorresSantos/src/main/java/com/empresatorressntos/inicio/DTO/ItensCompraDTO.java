package com.empresatorressntos.inicio.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.modelo.ItemsCompra;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ItensCompraDTO {
	
	
   
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private Long produtoID;
    private Long compraID;
    
    
    public ItensCompraDTO() {
    	
    	
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


	public Long getCompraID() {
		return compraID;
	}


	public void setCompraID(Long compraID) {
		this.compraID = compraID;
	}


	
}
