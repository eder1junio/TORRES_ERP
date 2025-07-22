package com.empresatorressntos.inicio.controle;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresatorressntos.inicio.DTO.CadastroCompraListarDTO;
import com.empresatorressntos.inicio.DTO.CadastroVendaDTO;
import com.empresatorressntos.inicio.DTO.TipoPagamentoDTO;
import com.empresatorressntos.inicio.modelo.TipoPagamento;
import com.empresatorressntos.inicio.repositorio.RepositorioTipoPagamento;

@RestController
@RequestMapping("/tipoPagamento")
public class ControleTipoPagamento {
	
	@Autowired
	private RepositorioTipoPagamento repositorio;
	
	
	@PostMapping("/cadastra")
	public ResponseEntity<?>cadastro(@RequestBody TipoPagamentoDTO dto){
		TipoPagamento tipo = new TipoPagamento();
		tipo.setId(dto.getId());
		tipo.setTipoPagamento(dto.getTipoPagamento());
		 repositorio.save(tipo);
		return ResponseEntity.ok("cadastro Realizado com sucesso");
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<TipoPagamentoDTO>>listaTipoPagamento(){
		List<TipoPagamentoDTO> lista = repositorio.findAll().stream()
				.map(TipoPagamentoDTO::new)
				.collect(Collectors.toList());
	 return ResponseEntity.ok(lista);
		
	}

}
