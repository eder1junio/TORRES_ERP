package com.example.demo.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.tipoPagamentoModelo;
import com.example.demo.repositorio.tipoPagamentoRepositorio;
import com.example.demo.service.tipoPagamentoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/tipoPagamento")
public class tipoPagamentoControler {

	
	@Autowired
	tipoPagamentoRepositorio repositorio;
	
	@Autowired
	tipoPagamentoService service;
 
 @PostMapping("/salvar")
 public ResponseEntity<tipoPagamentoModelo> salvaTipoPagamento (@RequestBody tipoPagamentoModelo entity) {
 
     return ResponseEntity.ok(repositorio.save(entity));
 }
 
 @GetMapping("/listar")
 public ResponseEntity<List<tipoPagamentoModelo>>listaTipoPagamento() {
     return ResponseEntity.ok(repositorio.findAll());
 }
 
 @GetMapping("/procura")
 public ResponseEntity<List<tipoPagamentoModelo>> procuraTipo(@RequestParam String tipoPagamento) {
     return ResponseEntity.ok(service.procuraTipoPagamento(tipoPagamento));
 }
 
 
 
 
}
