package com.eniac.projeto.agendaeducacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniac.projeto.agendaeducacional.entity.Caderno;

public interface CadernoRepository extends JpaRepository<Caderno, Long> {
    
}
