package com.eniac.projeto.agendaeducacional.service;

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
            tarefaExistente.setDataConclusao(tarefa.getDataConclusao());
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

    public List<Tarefa> list(StatusTarefa statusTarefa, List<String> sortBy, String direction, int prioridade) {
        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = List.of("dataCriacao");
        }

        Sort sort = Sort.by(dir, sortBy.toArray(new String[0]));

        List<Tarefa> tarefas;

        if (statusTarefa != null && prioridade > 0 && prioridade < 3 ) {
            tarefas = tarefaRepository.findByStatusTarefaAndPrioridade(statusTarefa, prioridade, sort);
        } else if (statusTarefa != null) {
            tarefas = tarefaRepository.findByStatusTarefa(statusTarefa);
        } else if (prioridade > 0) {
            tarefas = tarefaRepository.findByPrioridade(prioridade);
        } else {
            tarefas = tarefaRepository.findAll(sort);
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
}
