package com.empresatorressntos.inicio.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class CadastroFornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	
	 @OneToMany(mappedBy = "fornecedor")
	 @JsonIgnore
	 private List<CadastroCompra> compras;

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

	public List<CadastroCompra> getCompras() {
		return compras;
	}

	public void setCompras(List<CadastroCompra> compras) {
		this.compras = compras;
	}




	

}
