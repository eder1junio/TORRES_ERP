package com.empresatorressntos.inicio.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresatorressntos.inicio.DTO.CadastroFornecedorDTO;
import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.CadastroFornecedor;
import com.empresatorressntos.inicio.repositorio.CadastroFonecedorRepositorio;

@RestController
@RequestMapping("/fornecedor")
public class CadastroFornecedorControle {
	
	@Autowired
	private CadastroFonecedorRepositorio repositorio;
	
	@PostMapping("/cadastro")
	public CadastroFornecedor fornecedor(@RequestBody CadastroFornecedor fornecedor) {
		//TODO: process POST request
		
		return repositorio.save(fornecedor);
	}
	
	@GetMapping("/listar")
	public List<CadastroFornecedor> listarFornecedor() {
		return repositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>fornecedor(@PathVariable Long id){
		Optional<CadastroFornecedor>fornecedor = repositorio.findById(id);
		if(fornecedor.isPresent()) {
			return ResponseEntity.ok(new CadastroFornecedorDTO(fornecedor.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("fornecedor não encontrado");
	}
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<?> deletarFornecedor(@PathVariable Long id) {
	   
	    if (!repositorio.existsById(id)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
	    }

	  
	    repositorio.deleteById(id);
	    return ResponseEntity.ok("Fornecedor deletado com sucesso");
	}
}
