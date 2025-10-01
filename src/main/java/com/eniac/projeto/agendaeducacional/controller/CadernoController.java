package com.eniac.projeto.agendaeducacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;
import com.eniac.projeto.agendaeducacional.service.CadernoService;

import DTO.CategoriaRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/cadernos")
public class CadernoController {
    private CadernoService cadernoService;

    public CadernoController(CadernoService cadernoService_) {
        this.cadernoService = cadernoService_;
    }

    @GetMapping
    List<Caderno> listarCadernos(@RequestParam(required = false) StatusCaderno statusCaderno, @RequestParam(defaultValue = "titulo") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
        return cadernoService.list(statusCaderno, sortBy, direction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caderno> buscarPorId (@PathVariable Long id) {
        Caderno caderno = cadernoService.buscarPorId(id);
        return ResponseEntity.ok(caderno);
    }

    @PostMapping
    public Caderno create (@RequestBody Caderno caderno) {
        return cadernoService.create(caderno);
    }

    @PutMapping("/{id}")
    public String update (@PathVariable Caderno caderno) {
        return cadernoService.update(caderno);
    }

    @PutMapping("/{idCaderno/categorias}")
    public ResponseEntity<Caderno> updateCategorias(@PathVariable("idCaderno") Long id_caderno, @RequestBody List<CategoriaRequest> categoriasRequest) {
        return cadernoService.atualizarCategorias(id_caderno, categoriasRequest);
    }

    @DeleteMapping("/{id}")
    String delete (@PathVariable("id") Long id) {
        return cadernoService.deleteById(id);
    }
    
}
