package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.fornecedorModelo;

@Repository
public interface fornecedorRepositorio extends JpaRepository<fornecedorModelo, Long>{
	
	@Query("SELECT p FROM fornecedorModelo p WHERE p.fornecedor LIKE %:fornecedor%")
	List<fornecedorModelo> obterFornecedor(String fornecedor);
}
