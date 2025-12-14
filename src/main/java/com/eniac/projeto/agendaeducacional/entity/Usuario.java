package com.eniac.projeto.agendaeducacional.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false, unique = true)
    private String senha;

    private LocalDateTime dataCadastro;

    @PrePersist
    public void PrePersist() {
        if (dataCadastro == null){
            dataCadastro = LocalDateTime.now();
        }
    }
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Imagem fotoPerfil;

    public Usuario(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.fotoPerfil = usuario.getFotoPerfil();
        this.dataCadastro = usuario.getDataCadastro();
    }

    public Usuario () {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro_) {
        this.dataCadastro = dataCadastro_;
    }
    public Imagem getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(Imagem fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    
    
}
