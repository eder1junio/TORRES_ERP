package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.compraRepositorio;
import com.example.demo.repositorio.valorTotalComproFornecedor;

@Service
public class comprasServicos {
	
	@Autowired
	compraRepositorio repositorioCompra;
	
	public List<valorTotalComproFornecedor>obetervalor(){
		return repositorioCompra.obetervalor();
		
	}


}
