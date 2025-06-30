package com.empresatorressntos.inicio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresatorressntos.inicio.DTO.CadastroVendaAlteraDTO;
import com.empresatorressntos.inicio.DTO.CadastroVendaDTO;
import com.empresatorressntos.inicio.controle.CadastroVendasControle;
import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.modelo.ItemVenda;
import com.empresatorressntos.inicio.modelo.cadastroVenda;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroVendaRepositorio;

@Service
public class CadastroVendaServico {

   
	
	
	@Autowired
	public cadastroVendaRepositorio vendaRepo;

	@Autowired
	public cadastroProdutoRepositorio produtoRepo;

   
	public cadastroVenda salvaVenda(CadastroVendaDTO dto) {
		cadastroVenda venda = new cadastroVenda(dto,produtoRepo);
		return vendaRepo.save(venda);
	}
	
	public void atualizaVenda(CadastroVendaAlteraDTO vendas) {
		cadastroVenda vendaAntiga =  vendaRepo.findById(vendas.getId())
				.orElseThrow(() -> new RuntimeException("Venda não encontrada"));
		
		List<ItemVenda> novosItens = vendas.getProduto().stream()
			    .map(dto -> {
			        ItemVenda item = new ItemVenda();
			        item.setId(dto.getProdutoID());
			        item.setNome(dto.getNome());
			        item.setQuantidade(dto.getQuantidade());
			        item.setPrecoUnitario(dto.getPrecoUnitario());
			        CadastroProduto produto = produtoRepo.findById(dto.getProdutoID())
			        		.orElseThrow(() -> new RuntimeException("Produto não encontrada"));
			        item.setVenda(vendaAntiga); 
			        item.setProduto(produto);
			        
			        return item;
			    })
			    .collect(Collectors.toList());
		vendaAntiga.setDataVenda(vendas.getDataVenda());
		vendaAntiga.getProdutos().clear();
		vendaAntiga.getProdutos().addAll(novosItens);
		vendaAntiga.setValorVenda(vendas.getValorVenda());
		vendaRepo.save(vendaAntiga);
	}
	
		
	
	


}
