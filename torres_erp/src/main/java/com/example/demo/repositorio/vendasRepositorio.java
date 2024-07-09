package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.vendasModelo;

public interface vendasRepositorio extends JpaRepository<vendasModelo, Long> {

}
