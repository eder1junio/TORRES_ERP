package com.example.demo.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.compraModelo;
import com.example.demo.modelo.produtoModelo;
import com.example.demo.repositorio.compraRepositorio;
import com.example.demo.repositorio.produtoRepositorio;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/compras")
public class comprasControler {
	
	@Autowired
	compraRepositorio comprarepositorio;
	
	@Autowired 
	produtoRepositorio produtoRepositorio;
	
	@PostMapping("/salvar")
	public ResponseEntity<compraModelo>salvaCompra(@RequestBody compraModelo entity) {
		for(produtoModelo produto:entity.getProduto()) {
			Optional<produtoModelo> optionalProduto = produtoRepositorio.findById(produto.getId());
			if (optionalProduto.isPresent()) {
				produtoModelo produto1 = optionalProduto.get();
				if(produto.getValorVendaProduto() != null) {
					produto1.setValorVendaProduto(produto.getValorVendaProduto());
					produtoRepositorio.save(produto1);
					
				}
			}
			
		}
		
		return ResponseEntity.ok(comprarepositorio.save(entity));
		
	}
	
	

}
