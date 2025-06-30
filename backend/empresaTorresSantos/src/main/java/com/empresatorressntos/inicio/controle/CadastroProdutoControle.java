package com.empresatorressntos.inicio.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.empresatorressntos.inicio.DTO.CadastroProdutoDTO;
import com.empresatorressntos.inicio.modelo.CadastroProduto;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/produto")
public class CadastroProdutoControle {
	
	@Autowired
	public cadastroProdutoRepositorio repositorio;
	
	@PostMapping("/cadastro")
	public CadastroProduto produto(@RequestBody CadastroProduto produto) {
		
		return repositorio.save(produto);
	}
	
	
	
	@GetMapping("/listar")
	public List<CadastroProduto>listarProduto() {
		return repositorio.findAll();
	}
	

	@GetMapping("/buscar/{id}")
	public ResponseEntity<CadastroProdutoDTO> buscaPordutoPorId(@PathVariable Long id) {
		 CadastroProdutoDTO dto = repositorio.findById(id)
			        .map(produto -> new CadastroProdutoDTO(produto))
			        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

			    return ResponseEntity.ok(dto);
			}
}