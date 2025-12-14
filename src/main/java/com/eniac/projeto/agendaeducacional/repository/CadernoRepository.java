package com.eniac.projeto.agendaeducacional.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;


public interface CadernoRepository extends JpaRepository<Caderno, Long> {
    List<Caderno> findByUsuarioIdAndStatusCaderno(Long idUsuario,StatusCaderno statusCaderno, Sort sort);
    List<Caderno> findByUsuarioIdAndCategoriasNomeCategoria(Long idUsuario,String nomeCategoria, Sort sort);
    List<Caderno> findByUsuarioIdAndStatusCadernoAndCategoriasNomeCategoria(Long idUsuario,StatusCaderno statusCaderno, String nomeCategoria, Sort sort);
    List<Caderno> findByUsuarioId(Long idUsuario, Sort sort);
}
    