package com.eniac.projeto.agendaeducacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByUsuarioIdOrderByNomeCategoriaAsc(Long usuarioId);
}
