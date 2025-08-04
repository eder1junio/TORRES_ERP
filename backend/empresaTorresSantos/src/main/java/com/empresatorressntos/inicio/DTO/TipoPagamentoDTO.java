package com.empresatorressntos.inicio.DTO;

import java.util.stream.Collectors;

import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.TipoPagamento;

public class TipoPagamentoDTO {
		
	private Long id;
	private String tipoPagamento;
	
	 public TipoPagamentoDTO() {
	    }

	
	public TipoPagamentoDTO(TipoPagamento tipo) {
		this.tipoPagamento = tipo.getTipoPagamento();
	}
	public TipoPagamentoDTO() {
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	
}
