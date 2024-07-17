package com.example.demo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.compraModelo;
import com.example.demo.repositorio.compraRepositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/compras")
public class comprasControler {
	
	@Autowired
	compraRepositorio comprarepositorio;
	
	@PostMapping("/salvar")
	public ResponseEntity<compraModelo>salvaCompra(@RequestBody compraModelo entity) {
		//TODO: process POST request
		
		return ResponseEntity.ok(comprarepositorio.save(entity));
		
	}
	
	

}
