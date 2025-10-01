package com.eniac.projeto.agendaeducacional.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;


public interface CadernoRepository extends JpaRepository<Caderno, Long> {
    List<Caderno> findByStatus_caderno(StatusCaderno status_caderno, Sort sort);
    List<Caderno> findByCategoriasNome_categoria(String nome_categoria);
}
