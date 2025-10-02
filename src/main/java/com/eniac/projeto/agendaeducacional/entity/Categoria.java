package com.eniac.projeto.agendaeducacional.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    private Usuario id_Usuario;

    @Column(name = "data_criacao_categoria", nullable = false)
    private LocalDateTime data_criacao_categoria;

    @Column(name = "cor", nullable = false)
    private String cor;

    @ManyToMany(mappedBy = "categorias")
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

    public Usuario getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(Usuario id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public LocalDateTime getData_criacao_categoria() {
        return data_criacao_categoria;
    }

    public void setData_criacao_categoria(LocalDateTime data_criacao_categoria) {
        this.data_criacao_categoria = data_criacao_categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    
}
