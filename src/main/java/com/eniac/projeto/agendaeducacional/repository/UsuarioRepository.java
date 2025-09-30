package com.eniac.projeto.agendaeducacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
    
}
