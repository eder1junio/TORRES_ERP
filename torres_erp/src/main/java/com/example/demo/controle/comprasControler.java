package com.example.demo.controle;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.compraModelo;
import com.example.demo.modelo.produtoModelo;
import com.example.demo.repositorio.compraRepositorio;
import com.example.demo.repositorio.produtoRepositorio;
import com.example.demo.repositorio.valorTotalComproFornecedor;
import com.example.demo.service.comprasServicos;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/compras")
public class comprasControler {
	
	@Autowired
	compraRepositorio comprarepositorio;
	
	@Autowired 
	produtoRepositorio produtoRepositorio;
	
	@Autowired
	comprasServicos servicosCompras;
	
	@PostMapping("/salvar")
	public ResponseEntity<compraModelo>salvaCompra(@RequestBody compraModelo entity) {
	
		
		return ResponseEntity.ok(comprarepositorio.save(entity));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<compraModelo>>listaCompra() {
		return ResponseEntity.ok(comprarepositorio.findAll());
	}
	
	@GetMapping("/obterTotal")
	public ResponseEntity<List<valorTotalComproFornecedor>>obterTotal() {
		return ResponseEntity.ok(servicosCompras.obetervalor());
	}
	
	

}
