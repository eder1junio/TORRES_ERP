package com.empresatorressntos.inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresatorressntos.inicio.exception.ResourceNotFoundException;
import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.repositorio.cadastroCompraRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;

@Service
public class cadastroCompraServico {
	
	
	@Autowired
	public cadastroCompraRepositorio compraRepositorio;
	@Autowired
	public cadastroProdutoRepositorio produtoRepositorio;
	
	public CadastroCompra deletarCompraComSeguranca(Long id) {
        CadastroCompra compra = compraRepositorio.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Compra com ID " + id + " não encontrada."));
        compraRepositorio.deleteById(id);

        
        
       
        return compra;
    }
	

}
