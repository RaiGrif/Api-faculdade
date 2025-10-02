package com.eniac.projeto.agendaeducacional.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.service.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
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
    String list(@PathVariable("id") Long id){
        return usuarioService.delete(id);
    }
}
