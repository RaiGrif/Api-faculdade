package com.eniac.projeto.agendaeducacional.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tituloTarefa",nullable = false)
    private String tituloTarefa;

    @Column(name = "descricao",length = 1000)
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void PrePersist() {
        if (dataCriacao == null){
            dataCriacao = LocalDateTime.now();
        }
    }

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa statusTarefa;

    @Column(nullable = false)
    private Integer prioridade;

    @Column(name = "data_vencimento")
    private LocalDateTime dataVencimento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Getters and Setters
    public Tarefa(){}


    public Tarefa(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.tituloTarefa = tarefa.getTituloTarefa();
        this.descricao = tarefa.getDescricao();
        this.statusTarefa = tarefa.getStatusTarefa();
        this.dataConclusao = tarefa.getDataConclusao();
        this.dataCriacao = tarefa.getDataCriacao();
        this.dataVencimento = tarefa.getDataVencimento();
        this.usuario = tarefa.getUsuario();
        this.prioridade = tarefa.getPrioridade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_) {
        this.id = id_;
    }

    public String getTituloTarefa() {
        return tituloTarefa;
    }

    public void setTituloTarefa(String tituloTarefa_) {
        this.tituloTarefa = tituloTarefa_;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa_) {
        this.statusTarefa = statusTarefa_;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
