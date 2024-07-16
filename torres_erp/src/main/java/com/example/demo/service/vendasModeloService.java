package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.totalVendidoPorDia;
import com.example.demo.repositorio.vendasRepositorio;

@Service
public class vendasModeloService {
	@Autowired
	vendasRepositorio  repositorioVendas;

	
	public List<totalVendidoPorDia>ListatotalVendidoPorDia(){
		return repositorioVendas.vendasTotalPorDia();
	}
}
