package com.eniac.projeto.agendaeducacional.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "caderno")
public class Caderno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_caderno;

    @Column(name = "titulo_caderno",nullable = false)
    private String tituloCaderno;
    
    @Column(name = "conteudo", columnDefinition = "TEXT")
    private String conteudo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_caderno")
    private StatusCaderno statusCaderno;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_criacao_caderno", nullable = false)
    private LocalDateTime dataCriacaoCaderno;

    @PrePersist
    public void PrePersist() {
        if (dataCriacaoCaderno == null){
            dataCriacaoCaderno = LocalDateTime.now();
        }
        ultima_atualizacao = LocalDateTime.now();
    }

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultima_atualizacao;

    @PreUpdate
    public void PreUpdate() {
        ultima_atualizacao = LocalDateTime.now();
    }

    @ManyToMany
    @JoinTable(
        name = "caderno_categoria",
        joinColumns = @JoinColumn(name ="id_caderno"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "caderno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens = new ArrayList<>();

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
        return tituloCaderno;
    }

    public void setTitulo_caderno(String titulo_caderno) {
        this.tituloCaderno = titulo_caderno;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public StatusCaderno getStatus_caderno() {
        return statusCaderno;
    }

    public void setStatus_caderno(StatusCaderno status_caderno) {
        this.statusCaderno = status_caderno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario id_usuario) {
        this.usuario = id_usuario;
    }

    public LocalDateTime getData_criacao_caderno() {
        return dataCriacaoCaderno;
    }

    public void setData_criacao_caderno(LocalDateTime data_criacao_caderno) {
        this.dataCriacaoCaderno = data_criacao_caderno;
    }

    public LocalDateTime getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(LocalDateTime ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }
    
     

}