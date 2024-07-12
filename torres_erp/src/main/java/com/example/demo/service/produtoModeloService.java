package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.produtoModelo;
import com.example.demo.repositorio.produtoRepositorio;

@Service
public class produtoModeloService {
	@Autowired
	produtoRepositorio repositorio;
	
	public List<produtoModelo> buscarPorNomeParcial(String nome) {
        return repositorio.findByNomeContaining(nome);
    }

}
