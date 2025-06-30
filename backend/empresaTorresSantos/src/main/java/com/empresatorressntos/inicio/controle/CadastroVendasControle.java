package com.empresatorressntos.inicio.controle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresatorressntos.inicio.DTO.CadastroCompraDTO;
import com.empresatorressntos.inicio.DTO.CadastroVendaAlteraDTO;
import com.empresatorressntos.inicio.DTO.CadastroVendaDTO;
import com.empresatorressntos.inicio.DTO.ItemVendaDTO;
import com.empresatorressntos.inicio.mapper.CadastroVendaMapper;
import com.empresatorressntos.inicio.modelo.CadastroCompra;
import com.empresatorressntos.inicio.modelo.ItemVenda;
import com.empresatorressntos.inicio.modelo.cadastroVenda;
import com.empresatorressntos.inicio.repositorio.cadastroProdutoRepositorio;
import com.empresatorressntos.inicio.repositorio.cadastroVendaRepositorio;
import com.empresatorressntos.inicio.service.CadastroVendaServico;
import com.empresatorressntos.inicio.service.EstoqueServico;
import com.empresatorressntos.inicio.service.cadastroCompraServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/venda")
public class CadastroVendasControle {
	
	@Autowired
	public cadastroVendaRepositorio repository;
	
	@Autowired
	public cadastroProdutoRepositorio produtoRepo;
	
	@Autowired
	private EstoqueServico estoqueServico;
	
	@Autowired
	private CadastroVendaServico vendasServico;
	
	
	
	
	@PostMapping("/cadastra")
	public  ResponseEntity<?> vendas(@Valid @RequestBody CadastroVendaDTO dto, BindingResult result) {
		if (result.hasErrors()) {
	        List<String> erros = result.getFieldErrors().stream()
	            .map(e -> e.getField() + ": " + e.getDefaultMessage())
	            .collect(Collectors.toList());
	        return ResponseEntity.badRequest().body(erros);
	    }
		
		for(ItemVendaDTO produtoDTO : dto.getProduto()) {
			
			if(produtoDTO.getProdutoID() == null ||produtoDTO.getProdutoID() <= 0) {
				return ResponseEntity.badRequest().body("campo ID invalido ou inesistente "+produtoDTO.getProdutoID());
				
			}
			if(produtoDTO.getQuantidade() == null|| produtoDTO.getQuantidade() <= 0) {
				return ResponseEntity.badRequest().body("campo quantidade invalido ou inesistente "+produtoDTO.getQuantidade());
			}
			estoqueServico.diminuirEstoque(produtoDTO.getProdutoID(),produtoDTO.getQuantidade());
			estoqueServico.atualizaPrecoMedioVenda(produtoDTO.getProdutoID(), produtoDTO.getPrecoUnitario(), produtoDTO.getQuantidade());
			
		}
	    cadastroVenda c = new cadastroVenda(dto,produtoRepo);
	   
	    cadastroVenda salvo = repository.save(c);
	    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	
   @GetMapping("/listar")
   private List<cadastroVenda> listarVendas(){
	   return repository.findAll();
			   
   }
   
   @DeleteMapping("/deleta/{id}")
   public ResponseEntity<?> deletaVenda(@PathVariable Long id){
	   if(!repository.existsById(id)) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
		 
	   }
	   repository.deleteById(id);
	   
	   return ResponseEntity.ok("Deletado com sucesso");
	   
   }@PutMapping("/atualiza")
   public ResponseEntity<?>atualuzaVenda(@Valid @RequestBody CadastroVendaAlteraDTO vendas){
	   vendasServico.atualizaVenda(vendas);
	   
	   return ResponseEntity.ok("venda alterada com sucesso");
	   
	   
	   
   }
   @GetMapping("/{id}")
   public ResponseEntity<?>procuraVendaPorID(@PathVariable Long id){
	   Optional<cadastroVenda> vendas = repository.findById(id);
	  if (vendas.isPresent()) {
		  CadastroVendaDTO dto = CadastroVendaMapper.toDTO(vendas.get());
		  return ResponseEntity.ok(dto);
		 
	  }
	  
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda nao encontrada. Id "+id+ "Invalido");
	   
	  
	 
   }
   
   
   
}

