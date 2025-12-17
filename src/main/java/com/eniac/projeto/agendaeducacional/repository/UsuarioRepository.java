package com.eniac.projeto.agendaeducacional.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByEmail(String email);
}
