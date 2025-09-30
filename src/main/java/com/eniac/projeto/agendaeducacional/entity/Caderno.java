package com.eniac.projeto.agendaeducacional.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "caderno")
public class Caderno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_caderno;

    @Column(nullable = false)
    private String titulo_caderno;

    @Column(name = "conteudo")
    private String conteudo;

    @Enumerated(EnumType.STRING)
    private statusCaderno status_caderno;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private String id_usuario;

    @Column(name = "data_criacao_caderno", nullable = false)
    private LocalDateTime data_criacao_caderno;

    public Long getId_caderno() {
        return id_caderno;
    }

    public void setId_caderno(Long id_caderno) {
        this.id_caderno = id_caderno;
    }

    public String getTitulo_caderno() {
        return titulo_caderno;
    }

    public void setTitulo_caderno(String titulo_caderno) {
        this.titulo_caderno = titulo_caderno;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public statusCaderno getStatus_caderno() {
        return status_caderno;
    }

    public void setStatus_caderno(statusCaderno status_caderno) {
        this.status_caderno = status_caderno;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDateTime getData_criacao_caderno() {
        return data_criacao_caderno;
    }

    public void setData_criacao_caderno(LocalDateTime data_criacao_caderno) {
        this.data_criacao_caderno = data_criacao_caderno;
    }
    
     

}