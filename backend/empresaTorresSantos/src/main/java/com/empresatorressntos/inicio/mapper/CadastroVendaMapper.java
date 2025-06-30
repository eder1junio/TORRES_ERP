package com.empresatorressntos.inicio.mapper;

import com.empresatorressntos.inicio.DTO.CadastroVendaDTO;
import com.empresatorressntos.inicio.modelo.cadastroVenda;

public class CadastroVendaMapper {
	
	 public static CadastroVendaDTO toDTO(cadastroVenda venda) {
	        CadastroVendaDTO dto = new CadastroVendaDTO();
	        dto.setId(venda.getId());
	        dto.setDataVenda(venda.getDataVenda());
	        dto.setValorVenda(venda.getValorVenda());
	        return dto;
	 }
	public static cadastroVenda toEntity(CadastroVendaDTO dto) {
        cadastroVenda venda = new cadastroVenda();
        venda.setId(dto.getId());
        venda.setDataVenda(dto.getDataVenda());
        venda.setValorVenda(dto.getValorVenda());
        return venda;
    }

}
