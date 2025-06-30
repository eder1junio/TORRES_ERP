package com.empresatorressntos.inicio.DTO;

import com.empresatorressntos.inicio.modelo.CadastroProduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastroProdutoDTO {
		@NotNull(message = "ID obrigatório ")
	    private Long id;
		
		@NotBlank(message = "Nome obrigatório")
	    private String nome;
		
		@NotBlank(message = "Descrição obrigatória")
	    private String descricao;

	    public CadastroProdutoDTO(CadastroProduto produto) {
	        this.id = produto.getId();
	        this.nome = produto.getNome();
	        this.descricao = produto.getDescricao();
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

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

	    

}
