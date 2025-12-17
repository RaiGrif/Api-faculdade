package com.eniac.projeto.agendaeducacional.repository;


import com.eniac.projeto.agendaeducacional.entity.StatusTarefa;
import com.eniac.projeto.agendaeducacional.entity.Tarefa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuarioIdAndStatusTarefa(Long idUsuario,StatusTarefa statusTarefa);
    List<Tarefa> findByUsuarioIdAndPrioridade(Long id,int prioridade);
    List<Tarefa> findByUsuarioIdAndStatusTarefaAndPrioridade(Long id,StatusTarefa statusTarefa, int prioridade, Sort sort);
    List<Tarefa> findByUsuarioId(Long idUsuario, Sort sort);

    List<Tarefa> findByUsuarioIdAndDataVencimentoBetweenAndStatusTarefaOrderByDataVencimentoAscPrioridadeDesc(
            Long usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim,
            StatusTarefa statusTarefa
    );

    List<Tarefa> findByUsuarioIdAndDataVencimentoBetweenAndStatusTarefaAndPrioridadeOrderByDataVencimentoAscPrioridadeDesc(
            Long usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim,
            StatusTarefa statusTarefa,
            int prioridade
    );

    List<Tarefa> findByUsuarioIdAndDataVencimentoBetweenOrderByDataVencimentoAscPrioridadeDesc(
        Long usuarioId,
        LocalDateTime inicio,
        LocalDateTime fim
        );

        List<Tarefa> findByUsuarioIdAndDataVencimentoBetweenAndPrioridadeOrderByDataVencimentoAscPrioridadeDesc(
        Long usuarioId,
        LocalDateTime inicio,
        LocalDateTime fim,
        int prioridade
        );
}