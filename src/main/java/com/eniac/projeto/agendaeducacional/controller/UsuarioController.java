package com.eniac.projeto.agendaeducacional.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.DTO.UsuarioDTO;
import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.service.UsuarioService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService_) {
        this.usuarioService = usuarioService_;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Usuario usuario) {
       Optional<Usuario> criado = usuarioService.create(usuario);

         if (criado != null) {
              return ResponseEntity.ok(criado);
         } else {
              return ResponseEntity.status(500).body("Erro ao criar usuário");
         }
    }

    @PostMapping("/login")
    ResponseEntity<Long> login(@RequestBody UsuarioDTO usuariodDto) {
       Long id = usuarioService.buscarEmailESenha(usuariodDto);

         if (id != null) {
              return ResponseEntity.ok(id);
         } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
    }

    @GetMapping
    Long mostrarUsuario (@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.buscarEmailESenha(usuarioDTO);
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
