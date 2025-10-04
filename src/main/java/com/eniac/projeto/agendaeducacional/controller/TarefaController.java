package com.eniac.projeto.agendaeducacional.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.StatusTarefa;
import com.eniac.projeto.agendaeducacional.entity.Tarefa;
import com.eniac.projeto.agendaeducacional.service.TarefaService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private TarefaService tarefaService;

    public TarefaController (TarefaService tarefaService_) {
        this.tarefaService = tarefaService_;
    }

    @GetMapping
    List<Tarefa> listarTarefas(@RequestParam(required = false,name = "statusTarefa") String statusTarefa, @RequestParam(required = false ,name = "sortBy") List<String> sortBy, @RequestParam(defaultValue = "asc",name = "direction") String direction, @RequestParam(required = false ,name = "prioridade", defaultValue = "0") int prioridade) {
        
        String sortDirection = direction.equalsIgnoreCase("desc") ? "desc" : "asc";

        StatusTarefa statusEnum = null;
        if (statusTarefa != null) {
            try {
                statusEnum = StatusTarefa.valueOf(statusTarefa.toUpperCase());
            } catch (IllegalArgumentException e) {
                // opcional : lançar exceção
            }
        }
        return tarefaService.list(statusEnum, sortBy, sortDirection, prioridade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId (@PathVariable("id") Long id) {
        return tarefaService.buscarPorId(id).map(tarefa -> ResponseEntity.ok(new Tarefa(tarefa))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<Tarefa> create(@RequestBody Tarefa tarefa) {
        return tarefaService.create(tarefa, tarefa.getUsuario().getId());
    }

    @PutMapping("/{id}")
    public String update (@PathVariable("id") Long id_tarefa, @RequestBody Tarefa tarefa) {
        return tarefaService.update(tarefa, id_tarefa);
    }
    
    @DeleteMapping("/{id}")
    String delete (@PathVariable("id") Long id) {
        return tarefaService.deleteById(id);
    }
    
}
