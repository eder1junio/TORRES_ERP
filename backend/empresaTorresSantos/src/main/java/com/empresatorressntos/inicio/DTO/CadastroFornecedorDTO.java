package com.empresatorressntos.inicio.DTO;

import com.empresatorressntos.inicio.modelo.CadastroFornecedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastroFornecedorDTO {
	
	
	@NotNull(message = "id invalido")
	private Long id;
	
	@NotBlank(message = "digite um nome valido")
	private String nome;
	
	public CadastroFornecedorDTO(CadastroFornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
	}
	public CadastroFornecedorDTO() {
		
	}

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
	
	

}
