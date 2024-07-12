package com.example.demo.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.vendasModelo;
import com.example.demo.repositorio.vendasRepositorio;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/vendas")
public class vendasControle {
	
	
	@Autowired
	vendasRepositorio repositorio;
	
	@PostMapping("/cadastra")
	public ResponseEntity<vendasModelo> cadastra(@RequestBody vendasModelo entity) {
		//TODO: process POST request
		
		return ResponseEntity.ok(repositorio.save(entity));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<vendasModelo>> lista() {
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/procurar")
	public ResponseEntity<?> buscar(@RequestParam Long id) {
		Optional<vendasModelo> buscar = repositorio.findById(id);
		if (buscar.isPresent()) {
			return ResponseEntity.ok(buscar.get());
		}else {
					
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("falha");
		}	
			
	}
	
	

}
