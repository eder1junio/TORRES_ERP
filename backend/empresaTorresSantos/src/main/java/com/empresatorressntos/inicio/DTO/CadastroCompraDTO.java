package com.empresatorressntos.inicio.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.modelo.ItemsCompra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CadastroCompraDTO {
		
		@NotNull(message = "O valor é obrigatório")
		@Positive(message = "O valor deve ser positivo")
	    public BigDecimal valorCompra;
		
		@NotNull(message = "A data da compra é obrigatória")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    public LocalDate dataCompra;
		
		@NotNull(message = "O ID do fornecedor é obrigatório")
	    public Long fornecedorId;
		
		public List<ItensCompraDTO>produto;
		
		 public CadastroCompraDTO() {
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

		public Long getFornecedorId() {
			return fornecedorId;
		}

		public void setFornecedorId(Long fornecedorId) {
			this.fornecedorId = fornecedorId;
		}

		public List<ItensCompraDTO> getProduto() {
			return produto;
		}

		public void setProduto(List<ItensCompraDTO> produto) {
			this.produto = produto;
		}

		


}

		

		


	   
		
	    
