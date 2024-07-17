package com.example.demo.controle;

import com.example.demo.modelo.fornecedorModelo;
import com.example.demo.repositorio.fornecedorRepositorio;
import com.example.demo.service.fornecedorServicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/fornecedor")
public class fornecedorControler {
	
	@Autowired
	fornecedorRepositorio fornecedorRepositorio;
	
	@Autowired
	fornecedorServicos servicosFornecedor;
	
	@GetMapping("/listar")
	public ResponseEntity<List<fornecedorModelo>>listaFornecedor() {
		return ResponseEntity.ok(fornecedorRepositorio.findAll());
	}
	
	@GetMapping("/procuraFornecedor")
	public ResponseEntity<List<fornecedorModelo>>obeterFornecedor1 (@RequestParam String fornecedor2) {
		return ResponseEntity.ok(servicosFornecedor.obterFornecedorServico(fornecedor2));
	}
	@PostMapping("/salva")
	public ResponseEntity<fornecedorModelo>salvafornecedor(@RequestBody fornecedorModelo entity) {

		
		return ResponseEntity.ok(fornecedorRepositorio.save(entity));
	}
	
	

}
