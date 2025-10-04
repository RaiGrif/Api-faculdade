package com.eniac.projeto.agendaeducacional.repository;


import com.eniac.projeto.agendaeducacional.entity.StatusTarefa;
import com.eniac.projeto.agendaeducacional.entity.Tarefa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDateTime;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatusTarefa(StatusTarefa statusTarefa);
    List<Tarefa> findByPrioridade(int prioridade);
    List<Tarefa> findByDataVencimentoBeforeAndStatusNot(LocalDateTime dataVencimento, StatusTarefa status);
    List<Tarefa> findByUsuarioIdAndStatus(Long usuarioId, StatusTarefa status);
    @Query("SELECT t FROM Tarefa t WHERE t.usuario.id = :usuarioId AND t.status = :status")
    List<Tarefa> buscarPorUsuarioEStatus(@Param("usuarioId") Long usuarioId, @Param("status") String status);
    List<Tarefa> findByStatusTarefaAndPrioridade(StatusTarefa statusTarefa, int prioridade, Sort sort);
}