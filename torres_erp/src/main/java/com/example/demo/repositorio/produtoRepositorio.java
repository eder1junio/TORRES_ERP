package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.produtoModelo;

public interface produtoRepositorio extends JpaRepository<produtoModelo, Long> {
	
	@Query("SELECT p FROM produtoModelo p WHERE p.nome LIKE %:nome%")
    List<produtoModelo> findByNomeContaining(@Param("nome") String nome);
    
    
    @Query( value = "SELECT v.produto_id, p.nome, COUNT(v.produto_id) as total FROM vendas_modelo_produto v \r\n"
    		+ "JOIN produto_modelo p ON v.produto_id = p.id  GROUP BY v.produto_id, p.nome ORDER BY total DESC;",  nativeQuery = true)
 	List<produtoModelo[]> findTopProdutos();
    
    

}
