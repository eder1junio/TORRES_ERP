package com.empresatorressntos.inicio.modelo;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class CadastroProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	
	@Column
	private String descricao;
	@Column
	private Integer quantidadeEstoque = 0;
	@Column
	private BigDecimal precoVenda = BigDecimal.ZERO;
	@Column
	private BigDecimal precoCompra = BigDecimal.ZERO;
	@Column
	private Integer codigoBarra;
	
	
	public Integer getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(Integer codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ItemsCompra> itensCompra = new ArrayList<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ItemVenda> itensVenda = new ArrayList<>();
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ItemsCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemsCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	
	

}
