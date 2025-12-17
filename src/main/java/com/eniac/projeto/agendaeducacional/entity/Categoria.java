package com.eniac.projeto.agendaeducacional.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column(name = "nome_categoria", nullable = false)
    private String nomeCategoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_criacao_categoria", nullable = false)
    private LocalDateTime dataCriacaoCategoria;

    @PrePersist
    public void PrePersist() {
        if (dataCriacaoCategoria == null){
            dataCriacaoCategoria = LocalDateTime.now();
        }
    }

    @Column(name = "cor", nullable = false)
    private String cor;

    @ManyToMany(mappedBy = "categorias")
    @JsonBackReference
    private List<Caderno> cadernos = new ArrayList<>();

    public Categoria () {}

    public Categoria (String nome_categoria_) {
        this.nomeCategoria = nome_categoria_;
    }

    public List<Caderno> getCadernos() {
        return cadernos;
    }

    public void setCadernos(List<Caderno> cadernos) {
        this.cadernos = cadernos;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNome_categoria() {
        return nomeCategoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nomeCategoria = nome_categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario_) {
        this.usuario = usuario_;
    }

    public LocalDateTime getData_criacao_categoria() {
        return dataCriacaoCategoria;
    }

    public void setData_criacao_categoria(LocalDateTime data_criacao_categoria) {
        this.dataCriacaoCategoria = data_criacao_categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    
}
