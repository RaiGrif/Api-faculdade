package com.eniac.projeto.agendaeducacional.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.Tarefa;
import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService_) {
        this.usuarioService = usuarioService_;
    }

    @PostMapping
    String create(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @GetMapping("{id}")
    ResponseEntity<Usuario> mostrarUsuario (@PathVariable("id") Long id){
        return usuarioService.buscarPorId(id).map(usuario -> ResponseEntity.ok(new Usuario(usuario))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    String alterarUsuario (@PathVariable("id") Long id_usuario, @RequestBody Usuario usuario) {
        return usuarioService.update(usuario, id_usuario);
    }

    @DeleteMapping("/{id}")
    String apagarUsuario(@PathVariable("id") Long id) {
        return usuarioService.delete(id);
    }
}
