package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.fornecedorModelo;

@Repository
public interface fornecedorRepositorio extends JpaRepository<fornecedorModelo, Long>{
	
	@Query(value = "select * from fornecedor_modelo f where f.fornecedor like '%:fornecedor%';",  nativeQuery = true)
	List<fornecedorModelo> obterFornecedor(String fornecedor);
}
