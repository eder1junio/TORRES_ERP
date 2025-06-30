package com.empresatorressntos.inicio.modelo;





import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.empresatorressntos.inicio.DTO.CadastroCompraDTO;
import com.empresatorressntos.inicio.DTO.ItensCompraDTO;
import com.empresatorressntos.inicio.repositorio.CadastroFonecedorRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroCompraRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CadastroCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private BigDecimal valorCompra;
	
	@Column
	private LocalDate dataCompra;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemsCompra> items = new ArrayList<>();

	@ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private CadastroFornecedor fornecedor;
	 
	public void calcularValorTotal() {
	    this.valorCompra = items.stream()
	        .map(ItemsCompra::getSubtotal)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public BigDecimal getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public CadastroFornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(CadastroFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public CadastroCompra(
	        CadastroCompraDTO dto, 
	        CadastroFonecedorRepositorio fornecedorRepo, 
	        cadastroProdutoRepositorio produtoRepo) {
	    
	    this.valorCompra = dto.getValorCompra();
	    this.dataCompra = dto.getDataCompra();

	   
	    this.fornecedor = fornecedorRepo.findById(dto.getFornecedorId())
	            .orElseThrow(() -> new RuntimeException("Fornecedor com ID " + dto.getFornecedorId() + " não encontrado"));

	    this.items = new ArrayList<>();

	    
	    for (ItensCompraDTO itemDTO : dto.getProduto()) {
	        
	        CadastroProduto produto = produtoRepo.findById(itemDTO.getProdutoID())
	                .orElseThrow(() -> new RuntimeException("Produto com ID " + itemDTO.getProdutoID() + " não encontrado"));

	        ItemsCompra item = new ItemsCompra();
	        item.setProduto(produto);
	        item.setCompra(this);
	        item.setQuantidade(itemDTO.getQuantidade());
	        item.setPrecoUnitario(itemDTO.getPrecoUnitario());

	       
	        BigDecimal subtotal = itemDTO.getPrecoUnitario()
	                .multiply(BigDecimal.valueOf(itemDTO.getQuantidade()));
	        item.setSubtotal(subtotal);

	        this.items.add(item);
	    }
	}



	

	public List<ItemsCompra> getItems() {
		return items;
	}

	public void setItems(List<ItemsCompra> items) {
		this.items = items;
	}

	public CadastroCompra() {
		
	} 
	
	

	
	
}