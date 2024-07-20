package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.compraModelo;



public interface compraRepositorio extends JpaRepository<compraModelo, Long> {
	
	@Query(value = "select p.fonecerdor_id ,f.fornecedor, sum(p.valor_totla_dos_produtos) as total FROM compra_modelo p join fornecedor f on p.fonecerdor_id = f.id group by fonecerdor_id;",nativeQuery = true)
	List<valorTotalComproFornecedor> obetervalor();
}
