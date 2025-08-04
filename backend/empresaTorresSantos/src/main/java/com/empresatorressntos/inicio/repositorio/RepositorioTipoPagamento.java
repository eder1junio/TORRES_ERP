package com.empresatorressntos.inicio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresatorressntos.inicio.modelo.TipoPagamento;


public interface RepositorioTipoPagamento extends JpaRepository<TipoPagamento, Long> {
	
	List<TipoPagamento> findBytipoPagamentoContainingIgnoreCase(String tipoPagamento);

}


