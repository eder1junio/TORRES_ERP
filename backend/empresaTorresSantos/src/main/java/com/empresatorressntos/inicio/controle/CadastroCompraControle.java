package com.empresatorressntos.inicio.controle;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresatorressntos.inicio.DTO.CadastroCompraDTO;
import com.empresatorressntos.inicio.DTO.CadastroCompraListarDTO;
import com.empresatorressntos.inicio.DTO.ItensCompraDTO;
import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.CadastroFornecedor;
import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.modelo.ItemsCompra;
import com.empresatorressntos.inicio.repositorio.CadastroFonecedorRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroCompraRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;
import com.empresatorressntos.inicio.service.EstoqueServico;
import com.empresatorressntos.inicio.service.cadastroCompraServico;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/compra")
public class CadastroCompraControle {
	
	@Autowired
	public cadastroCompraRepositorio repositorio;
	@Autowired
	private CadastroFonecedorRepositorio fornecedorRepo;
	@Autowired
	private cadastroProdutoRepositorio produtoRepo;
	
	@Autowired
	private cadastroCompraServico compraServico;
	
	@Autowired
	private EstoqueServico estoqueServico;
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastro(@RequestBody @Valid CadastroCompraDTO dto, BindingResult result) {
	    
	    if (result.hasErrors()) {
	        List<String> erros = result.getFieldErrors().stream()
	            .map(e -> e.getField() + ": " + e.getDefaultMessage())
	            .collect(Collectors.toList());
	        return ResponseEntity.badRequest().body(erros);
	    }

	   
	    if (dto.getFornecedorId() == null) {
	        return ResponseEntity.badRequest().body("ID do fornecedor não pode ser nulo");
	    }

	    if (dto.getProduto() == null || dto.getProduto().isEmpty()) {
	        return ResponseEntity.badRequest().body("A lista de produtos não pode estar vazia");
	    }

	    for (ItensCompraDTO itemDTO : dto.getProduto()) {
	        if (itemDTO.getProdutoID() == null || itemDTO.getProdutoID() == null) {
	            return ResponseEntity.badRequest().body("Cada item deve ter um produto com ID não nulo");
	        }
	        if (itemDTO.getQuantidade() == null || itemDTO.getQuantidade() <= 0) {
	            return ResponseEntity.badRequest().body("Quantidade deve ser maior que zero para cada produto");
	        }
	        estoqueServico.adicionaEstoque(itemDTO.getProdutoID(),itemDTO.getQuantidade());
	        estoqueServico.atualizarPrecoMedio(itemDTO.getProdutoID(), itemDTO.getPrecoUnitario(), itemDTO.getQuantidade());
	    }

	    try {
	    
	        CadastroCompra novaCompra = new CadastroCompra(dto, fornecedorRepo, produtoRepo);
	        novaCompra.calcularValorTotal();
	        CadastroCompra salvo = repositorio.save(novaCompra);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body("Erro ao cadastrar: " + e.getMessage());
	    }
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<CadastroCompraListarDTO>> listarCompras() {
		List<CadastroCompraListarDTO> lista = repositorio.findAll().stream()
			.map(CadastroCompraListarDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok(lista);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarID(@PathVariable Long id) {
		Optional<CadastroCompra> compra = repositorio.findById(id);
		if (compra.isPresent()) {
			return ResponseEntity.ok(new CadastroCompraListarDTO(compra.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra não encontrada");
	}

	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarCompraID(@PathVariable Long id) {
		try {
			CadastroCompra compraDeletada = compraServico.deletarCompraComSeguranca(id);
			return ResponseEntity.ok(compraDeletada);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
	

}
