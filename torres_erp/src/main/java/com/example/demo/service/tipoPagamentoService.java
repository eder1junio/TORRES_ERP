package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.tipoPagamentoModelo;
import com.example.demo.repositorio.tipoPagamentoRepositorio;

@Service
public class tipoPagamentoService {
	
	@Autowired
	tipoPagamentoRepositorio repositorio;
	
	public List<tipoPagamentoModelo> procuraTipoPagamento(String tipoPagamento){
		List<tipoPagamentoModelo> tipo = repositorio.procuraPorTipoPagamento(tipoPagamento);
		return tipo;
		
		
	}

}
