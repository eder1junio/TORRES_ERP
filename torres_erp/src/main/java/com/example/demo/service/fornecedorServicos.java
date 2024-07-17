package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.fornecedorModelo;
import com.example.demo.repositorio.fornecedorRepositorio;

@Service
public class fornecedorServicos {
	
	@Autowired
	fornecedorRepositorio fornecedorRepositorio;
	
	
	public List<fornecedorModelo>obterFornecedorServico(String fornecedor){
		return fornecedorRepositorio.obterFornecedor(fornecedor);
	}

}
