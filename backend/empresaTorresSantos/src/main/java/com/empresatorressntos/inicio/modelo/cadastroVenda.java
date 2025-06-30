package com.empresatorressntos.inicio.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.empresatorressntos.inicio.DTO.CadastroCompraDTO;
import com.empresatorressntos.inicio.DTO.CadastroVendaDTO;
import com.empresatorressntos.inicio.DTO.ItemVendaDTO;
import com.empresatorressntos.inicio.repositorio.CadastroFonecedorRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class cadastroVenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate DataVenda;
	
	@Column
	private BigDecimal ValorVenda;
	
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> produtos = new ArrayList<>();
	
	public void calcularValorTotal() {
		this.ValorVenda = produtos.stream().map(ItemVenda::getSubTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	public cadastroVenda(CadastroVendaDTO dto, cadastroProdutoRepositorio produtoRepo) {
	    this.DataVenda = dto.getDataVenda();
	    this.produtos = new ArrayList<>();

	    for (ItemVendaDTO itemDTO : dto.getProduto()) {
	        CadastroProduto produto = produtoRepo.findById(itemDTO.getProdutoID())
	                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	        ItemVenda item = new ItemVenda();
	        item.setProduto(produto);
	        item.setQuantidade(itemDTO.getQuantidade());
	        item.setPrecoUnitario(itemDTO.getPrecoUnitario());
	        item.setVenda(this);

	        this.produtos.add(item);
	    }

	    this.calcularValorTotal();
	}
	public cadastroVenda() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataVenda() {
		return DataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		DataVenda = dataVenda;
	}

	public BigDecimal getValorVenda() {
		return ValorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		ValorVenda = valorVenda;
	}

	public List<ItemVenda> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ItemVenda> produtos) {
		this.produtos = produtos;
	}

	 
	

	
	

}
