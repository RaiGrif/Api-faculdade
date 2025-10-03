package com.eniac.projeto.agendaeducacional.DTO;

import java.util.List;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;

public class CadernoDTO {
     private Long id_caderno;
    private String titulo_caderno;
    private StatusCaderno statusCaderno;
    private List<String> categorias; 

    
    public CadernoDTO(Caderno caderno) {
        this.id_caderno = caderno.getId_caderno();
        this.titulo_caderno = caderno.getTitulo_caderno();
        this.statusCaderno = caderno.getStatus_caderno();
        this.categorias = caderno.getCategorias()
                                 .stream()
                                 .map(Categoria::getNome_categoria)
                                 .toList();
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


    

    
}
