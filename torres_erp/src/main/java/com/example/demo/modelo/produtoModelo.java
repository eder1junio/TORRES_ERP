package com.example.demo.modelo;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class produtoModelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome;
	public String descricao;
	public Float valorProduto;
	
	
	 @ManyToMany(mappedBy = "produto")
	 private List<vendasModelo> vendas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Float getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(Float valorProduto) {
		this.valorProduto = valorProduto;
	}
	public List<vendasModelo> getVendas() {
		return vendas;
	}
	public void setVendas(List<vendasModelo> vendas) {
		this.vendas = vendas;
	}
	

}
