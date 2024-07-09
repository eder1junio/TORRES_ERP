package com.example.demo.modelo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repositorio.produtoRepositorio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/produto")
public class produtoControle {
	
	@Autowired
	produtoRepositorio repositorio;
	
	@PostMapping("/cadastra")
	public ResponseEntity<produtoModelo> cadastra (@RequestBody produtoModelo entity) {
		
		return ResponseEntity.ok(repositorio.save(entity));
	}
	 @GetMapping("/listra")
	 public ResponseEntity<List<produtoModelo>> lista(){
		 
		 return ResponseEntity.ok(repositorio.findAll());
	 }
	

}
