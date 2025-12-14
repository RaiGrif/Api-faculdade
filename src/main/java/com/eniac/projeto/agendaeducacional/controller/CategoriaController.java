package com.eniac.projeto.agendaeducacional.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.service.CategoriaService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
        private CategoriaService categoriaService;

        public CategoriaController (CategoriaService categoriaService_){
            this.categoriaService = categoriaService_;
        }

        @GetMapping("{id}")
        public List<Categoria> get(@PathVariable("id") Long id) {
            return categoriaService.list(id);
        }

        @PostMapping
        public List<Categoria> create (@RequestBody Categoria categoria) {
            return categoriaService.create(categoria.getUsuario().getId(),categoria);
        }      

        @PutMapping("{id}")
        public List<Categoria> update(@PathVariable("id") Long id,@RequestBody Categoria categoria) {
            return categoriaService.update(id, categoria);
        }

        @DeleteMapping("{id}")
        List<Categoria> delete (@PathVariable("id") Long id){
            return categoriaService.deleteById(id);
        }
        
}
