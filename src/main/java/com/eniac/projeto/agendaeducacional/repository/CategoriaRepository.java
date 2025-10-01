package com.eniac.projeto.agendaeducacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
