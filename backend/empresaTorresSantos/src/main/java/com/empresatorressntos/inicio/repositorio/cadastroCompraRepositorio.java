package com.empresatorressntos.inicio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresatorressntos.inicio.modelo.CadastroCompra;


public interface cadastroCompraRepositorio  extends JpaRepository<CadastroCompra, Long>{

	
}
