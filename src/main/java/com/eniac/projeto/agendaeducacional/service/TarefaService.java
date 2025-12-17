package com.eniac.projeto.agendaeducacional.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.entity.StatusTarefa;
import com.eniac.projeto.agendaeducacional.entity.Tarefa;
import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.repository.TarefaRepository;
import com.eniac.projeto.agendaeducacional.repository.UsuarioRepository;

@Service
public class TarefaService {
    private UsuarioRepository usuarioRepository;
    private TarefaRepository tarefaRepository;

    public TarefaService (TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Tarefa> create (Tarefa tarefa, Long id_usuario) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        tarefa.setUsuario(usuario);
        tarefaRepository.save(tarefa);
        return list();
    };

    public String update(Tarefa tarefa, Long id_tarefa) {
        
        try {
            Tarefa tarefaExistente = tarefaRepository.findById(id_tarefa).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

            tarefaExistente.setTituloTarefa(tarefa.getTituloTarefa());
            tarefaExistente.setDescricao(tarefa.getDescricao());
            tarefaExistente.setPrioridade(tarefa.getPrioridade());
            tarefaExistente.setStatusTarefa(tarefa.getStatusTarefa());
            tarefaExistente.setDataVencimento(tarefa.getDataVencimento());
            if (tarefa.getStatusTarefa() == StatusTarefa.REALIZADO) {
                tarefaExistente.setDataConclusao(LocalDateTime.now());
            } else {
                tarefaExistente.setDataConclusao(null);
            }
            tarefaExistente.setUsuario(tarefa.getUsuario());

            tarefaRepository.save(tarefaExistente);
            return "A tarefa foi salva com sucesso";
        } catch (Exception e) {
            e.printStackTrace();  
            return "A tarefa não foi salva com sucesso";
        }
    }
    

    public List<Tarefa> list() {
        Sort sort = Sort.by("statusTarefa").ascending().and(Sort.by("tituloTarefa").ascending());
        return tarefaRepository.findAll(sort);
    }

    public List<Tarefa> list(Long idUsuario,StatusTarefa statusTarefa, List<String> sortBy, String direction, Integer prioridade) {
        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = List.of("dataCriacao");
        }

        Sort sort = Sort.by(dir, sortBy.toArray(new String[0]));

        List<Tarefa> tarefas;

        boolean prioridadeValida = prioridade != null && prioridade > 0 && prioridade < 3;

        if (statusTarefa != null && prioridadeValida ) {
            tarefas = tarefaRepository.findByUsuarioIdAndStatusTarefaAndPrioridade(idUsuario,statusTarefa, prioridade, sort);
        } else if (statusTarefa != null) {
            tarefas = tarefaRepository.findByUsuarioIdAndStatusTarefa(idUsuario,statusTarefa);
        } else if (prioridadeValida) {
            tarefas = tarefaRepository.findByUsuarioIdAndPrioridade(idUsuario,prioridade);
        } else {
            tarefas = tarefaRepository.findByUsuarioId(idUsuario, sort);
        }

        return tarefas;
    }

    public String deleteById(Long id) {
        try{
        tarefaRepository.deleteById(id);
        return "A tarefa foi deletada";
        } catch (Exception erro) {
            return "A tarefa não foi deletada";
        }
    }

    public Optional<Tarefa> buscarPorId(Long id) {
          return tarefaRepository.findById(id);
    }

    public List<Tarefa> buscarPorUsuarioEMes(Long idUsuario, int ano, int mes, StatusTarefa statusTarefa, Integer prioridade) {

        YearMonth ym = YearMonth.of(ano, mes);

        LocalDateTime inicio = ym.atDay(1).atStartOfDay();
        LocalDateTime fim = ym.atEndOfMonth().atTime(23, 59, 59);
        
         if (statusTarefa != null && prioridade != null) {
        return tarefaRepository
            .findByUsuarioIdAndDataVencimentoBetweenAndStatusTarefaAndPrioridadeOrderByDataVencimentoAscPrioridadeDesc(
                idUsuario, inicio, fim, statusTarefa, prioridade
            );
        }

        if (statusTarefa != null) {
        return tarefaRepository
            .findByUsuarioIdAndDataVencimentoBetweenAndStatusTarefaOrderByDataVencimentoAscPrioridadeDesc(
                idUsuario, inicio, fim, statusTarefa
            );
        }

         if (prioridade != null) {
            return tarefaRepository
                .findByUsuarioIdAndDataVencimentoBetweenAndPrioridadeOrderByDataVencimentoAscPrioridadeDesc(
                idUsuario, inicio, fim, prioridade
            );
    }

    return tarefaRepository
            .findByUsuarioIdAndDataVencimentoBetweenOrderByDataVencimentoAscPrioridadeDesc(
            idUsuario, inicio, fim
        );
    }
}
