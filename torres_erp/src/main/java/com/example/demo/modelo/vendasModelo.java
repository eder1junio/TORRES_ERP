package com.example.demo.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class vendasModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String cliente;
	public Float valorProduto;
	public String tipoPagamento;
	public int quantidade;
	
	@ManyToMany
	public List<produtoModelo> produto;

}
