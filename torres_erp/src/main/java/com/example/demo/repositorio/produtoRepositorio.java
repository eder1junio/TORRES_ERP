package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.produtoModelo;

public interface produtoRepositorio extends JpaRepository<produtoModelo, Long> {

}
