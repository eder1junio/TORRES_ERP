package com.example.demo.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.produtoModelo;
import com.example.demo.repositorio.produtoRepositorio;
import com.example.demo.service.produtoModeloService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/produto")
public class produtoControle {
	
	@Autowired
	produtoModeloService produtoModeloService;
	
	@Autowired
	produtoRepositorio repositorio;
	
	@PostMapping("/cadastra")
	public ResponseEntity<produtoModelo> cadastra (@RequestBody produtoModelo entity) {
		
		return ResponseEntity.ok(repositorio.save(entity));
	}
	 @GetMapping("/listar")
	 public ResponseEntity<List<produtoModelo>> lista(){
		 
		 return ResponseEntity.ok(repositorio.findAll());
	 }
	 @GetMapping("/procurar")
	 public ResponseEntity<?> buscar(@RequestParam Long id) {
		 Optional<produtoModelo> buscar = repositorio.findById(id);
	 	return ResponseEntity.ok(buscar.get());
	 	
	 }
	 
	 @GetMapping("/procuranome")
	 public ResponseEntity<List<produtoModelo>> procuraNome(@RequestParam String nome) {
	 	return ResponseEntity.ok(produtoModeloService.buscarPorNomeParcial(nome));
	 }
	 
	 @GetMapping("/produtoTop")
	 public ResponseEntity< List<produtoModelo[]>> getTopProdutos() {
	        return ResponseEntity.ok(produtoModeloService.produtoTop());
	 }
	 
	 
	 
	

}
