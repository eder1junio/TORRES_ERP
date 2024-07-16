package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.tipoPagamentoModelo;


@Repository
public interface tipoPagamentoRepositorio extends JpaRepository<tipoPagamentoModelo,Long>{
	
	@Query(value = "SELECT * FROM tipo_pagamento_modelo p WHERE p.tipo_pagamento LIKE %:tipoPagamento%", nativeQuery = true)
    List<tipoPagamentoModelo> procuraPorTipoPagamento(@Param("tipoPagamento") String tipoPagamento);
}
