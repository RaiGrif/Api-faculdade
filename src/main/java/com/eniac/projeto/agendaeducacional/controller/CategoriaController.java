package com.eniac.projeto.agendaeducacional.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.service.CategoriaService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/categorias")
public class CategoriaController {
        private CategoriaService categoriaService;

        public CategoriaController (CategoriaService categoriaService_){
            this.categoriaService = categoriaService_;
        }

        @GetMapping
        public List<Categoria> getMethodName(Categoria categoria) {
            return categoriaService.list();
        }

        @PostMapping
        public List<Categoria> create (@RequestBody Categoria categoria) {
            return categoriaService.create(categoria);
        }      

        @PutMapping
        public List<Categoria> putMethodName(@RequestBody Categoria categoria) {
            return categoriaService.update(categoria);
        }

        @DeleteMapping("{ID}")
        List<Categoria> delete (@PathVariable("ID") Long id){
            return delete(id);
        }
        
}
