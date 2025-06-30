package com.empresatorressntos.inicio.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.empresatorressntos.inicio.modelo.ItemVenda;

public class CadastroVendaAlteraDTO {private Integer quantidade;


private Long id;
private LocalDate dataVenda;
private List<ItemVendaDTO> produto;
private BigDecimal valorVenda;



public BigDecimal getValorVenda() {
	return valorVenda;
}
public void setValorVenda(BigDecimal valorVenda) {
	this.valorVenda = valorVenda;
}
public LocalDate getDataVenda() {
	return dataVenda;
}
public void setDataVenda(LocalDate dataVenda) {
	this.dataVenda = dataVenda;
}


public Integer getQuantidade() {
	return quantidade;
}
public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
}
public List<ItemVendaDTO> getProduto() {
	return produto;
}
public void setProduto(List<ItemVendaDTO> produto) {
	this.produto = produto;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}






}
