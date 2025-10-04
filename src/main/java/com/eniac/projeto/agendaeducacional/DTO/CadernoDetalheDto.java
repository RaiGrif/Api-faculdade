package com.eniac.projeto.agendaeducacional.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.entity.Imagem;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;

public class CadernoDetalheDto {
    private Long id_caderno;
    private String titulo_caderno;
    private String conteudo; // aqui vem o texto completo
    private StatusCaderno statusCaderno;
    private List<String> categorias;
    private LocalDateTime dataCriacaoCaderno;
    private LocalDateTime ultima_atualizacao;
    private List<Imagem> imagens = new ArrayList<>();

    public CadernoDetalheDto (Caderno caderno) {
        this.id_caderno = caderno.getId_caderno();
        this.titulo_caderno = caderno.getTitulo_caderno();
        this.conteudo = caderno.getConteudo();
        this.statusCaderno = caderno.getStatus_caderno();
        this.categorias = caderno.getCategorias()
                                 .stream()
                                 .map(Categoria::getNome_categoria)
                                 .toList();
        this.dataCriacaoCaderno = caderno.getData_criacao_caderno();
        this.ultima_atualizacao = caderno.getUltima_atualizacao();
        this.imagens = caderno.getImagens();
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

    public StatusCaderno getStatusCaderno() {
        return statusCaderno;
    }

    public void setStatusCaderno(StatusCaderno statusCaderno) {
        this.statusCaderno = statusCaderno;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public LocalDateTime getDataCriacaoCaderno() {
        return dataCriacaoCaderno;
    }

    public void setDataCriacaoCaderno(LocalDateTime dataCriacaoCaderno) {
        this.dataCriacaoCaderno = dataCriacaoCaderno;
    }

    public LocalDateTime getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(LocalDateTime ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    
}
