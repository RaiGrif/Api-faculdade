package com.eniac.projeto.agendaeducacional.repository;


import com.eniac.projeto.agendaeducacional.entity.StatusTarefa;
import com.eniac.projeto.agendaeducacional.entity.Tarefa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatusTarefa(StatusTarefa statusTarefa);
    List<Tarefa> findByPrioridade(int prioridade);
    List<Tarefa> findByStatusTarefaAndPrioridade(StatusTarefa statusTarefa, int prioridade, Sort sort);
}