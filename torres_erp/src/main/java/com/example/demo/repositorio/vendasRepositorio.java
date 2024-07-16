package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.vendasModelo;

public interface vendasRepositorio extends JpaRepository<vendasModelo, Long> {
	
	@Query(value = "SELECT v.data, v.cliente, SUM(m.valor_produto_compra) AS totalValorProduto\r\n"
			+ "FROM vendas_modelo v\r\n"
			+ "JOIN vendas_modelo_produto p ON v.id = p.vendas_modelo_id\r\n"
			+ "JOIN produto_modelo m ON p.produto_id = m.id\r\n"
			+ "GROUP BY v.cliente, v.data;", nativeQuery = true)
	List<totalVendidoPorDia> vendasTotalPorDia();

}
