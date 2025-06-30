package com.empresatorressntos.inicio.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.ItemsCompra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CadastroCompraListarDTO {
	
	@NotNull(message = "O valor é obrigatório")
	@Positive(message = "O valor deve ser positivo")
	private BigDecimal valorCompra;
	
	@NotNull(message = "A data da compra é obrigatória")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCompra;
	
	@NotNull(message = "O ID do fornecedor é obrigatório")
    private Long fornecedorId;
	
	@NotBlank(message = "o nome do fornecedor e obrigatorio ")
    private String nomeFornecedor;
	
	private List<Long>prodtoID;
	
	public CadastroCompraListarDTO(CadastroCompra compra) {
		this.valorCompra = compra.getValorCompra();
		this.dataCompra = compra.getDataCompra();
		this.fornecedorId = compra.getFornecedor().getId();
		this.nomeFornecedor = compra.getFornecedor().getNome();
		this.prodtoID = compra.getItems().stream().map(item -> item.getProduto().getId()).collect(Collectors.toList());
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

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public List<Long> getProdtoID() {
		return prodtoID;
	}

	public void setProdtoID(List<Long> prodtoID) {
		this.prodtoID = prodtoID;
	}
	 
	  

}
	

