package com.empresatorressntos.inicio.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;

@Service
public class EstoqueServico {
	
	@Autowired
	public cadastroProdutoRepositorio produtoRepo;
	
	public void adicionaEstoque(Long produtoID, Integer quantidade) {
		CadastroProduto produto = produtoRepo.findById(produtoID)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		
		produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()+ quantidade);
		
		produtoRepo.save(produto);
		
	}
	
	public void atualizarPrecoMedio(Long produtoID, BigDecimal valorProdutoCompra,Integer quantidade) {
		CadastroProduto produto = produtoRepo.findById(produtoID)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		
		Integer quantidadeEnEstoque = produto.getQuantidadeEstoque();
		BigDecimal valorEmEstoque = produto.getPrecoCompra();
		BigDecimal precoNoEstoqueTotal = valorEmEstoque.multiply(BigDecimal.valueOf(quantidadeEnEstoque));
		
		BigDecimal valorTotalCompra = valorProdutoCompra.multiply(BigDecimal.valueOf(quantidade));
		
		BigDecimal valorAtualizadoProduto = (precoNoEstoqueTotal.add(valorTotalCompra))
				.divide(BigDecimal.valueOf(quantidadeEnEstoque+quantidade),2,RoundingMode.HALF_UP);
		
		produto.setPrecoCompra(valorAtualizadoProduto);
		produtoRepo.save(produto);
				
		
	}
	
	public void diminuirEstoque(Long produtoID, Integer quantidade) {
		CadastroProduto produto = produtoRepo.findById(produtoID)
				.orElseThrow(() ->new RuntimeException("Produto Não Encontrado"));
		produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()-quantidade);
		produtoRepo.save(produto);
		
	}
	
	public void atualizaPrecoMedioVenda(Long produtoID, BigDecimal valorProdutoVenda,Integer quantidade) {
		CadastroProduto produto = produtoRepo.findById(produtoID).
				orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		Integer quantidadeEmEstoque = produto.getQuantidadeEstoque();
		BigDecimal valorEmEstoque = produto.getPrecoVenda();
		BigDecimal precoNoEstoqueTotal = valorEmEstoque.multiply(BigDecimal.valueOf(quantidadeEmEstoque));
		BigDecimal valorTotalVenda = valorProdutoVenda.multiply(BigDecimal.valueOf(quantidade));
		BigDecimal valorAtualizadoProduto = (precoNoEstoqueTotal.add(valorTotalVenda))
				.divide(BigDecimal.valueOf(quantidadeEmEstoque+quantidade),2,RoundingMode.HALF_UP);
		produto.setPrecoVenda(valorAtualizadoProduto);
		produtoRepo.save(produto);
		
		
		
	}

}
