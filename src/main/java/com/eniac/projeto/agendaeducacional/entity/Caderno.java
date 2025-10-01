package com.eniac.projeto.agendaeducacional.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @Column(name = "status_caderno")
    private StatusCaderno status_caderno;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario id_usuario;

    @Column(name = "data_criacao_caderno", nullable = false)
    private LocalDateTime data_criacao_caderno;

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultima_atualizacao;

    @ManyToMany
    @JoinTable(
        name = "caderno_categoria",
        joinColumns = @JoinColumn(name ="id_caderno"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

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

    public StatusCaderno getStatus_caderno() {
        return status_caderno;
    }

    public void setStatus_caderno(StatusCaderno status_caderno) {
        this.status_caderno = status_caderno;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDateTime getData_criacao_caderno() {
        return data_criacao_caderno;
    }

    public void setData_criacao_caderno(LocalDateTime data_criacao_caderno) {
        this.data_criacao_caderno = data_criacao_caderno;
    }

    public LocalDateTime getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(LocalDateTime ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }
    
     

}