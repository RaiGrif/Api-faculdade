package com.eniac.projeto.agendaeducacional.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;
import com.eniac.projeto.agendaeducacional.repository.CadernoRepository;
import com.eniac.projeto.agendaeducacional.repository.CategoriaRepository;

import com.eniac.projeto.agendaeducacional.DTO.CategoriaRequest;

@Service
public class CadernoService {
    private CadernoRepository cadernoRepository;
    private CategoriaRepository categoriaRepository;

    public CadernoService (CadernoRepository cadernoRepository_, CategoriaRepository categoriaRepository_) {
        this.cadernoRepository = cadernoRepository_;
        this.categoriaRepository = categoriaRepository_;
    }

    public Caderno create(Caderno caderno) {
        cadernoRepository.save(caderno);
        return buscarPorId (caderno.getId_caderno());
    }

    public String update(Caderno caderno) {
        try{
        cadernoRepository.save(caderno);
        return "O caderno foi salvo com sucesso";
        } catch (Exception erro) {
            return "O caderno não foi salvo com sucesso";
        }
    }

    public ResponseEntity<Caderno> atualizarCategorias(Long id, List<CategoriaRequest> categoriasRequest) {
        Caderno caderno = cadernoRepository.findById(id).orElseThrow(() -> new RuntimeException("Caderno não encontrado"));

        List<Categoria> categorias = new ArrayList<>();

        for (CategoriaRequest req : categoriasRequest) {
            Categoria categoria;
            if (req.getId() != null) {
                categoria = categoriaRepository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada" + req.getId()));
            } else {
                categoria = new Categoria(req.getNome());
                categoriaRepository.save(categoria);
            }
            categorias.add(categoria);
        }

        caderno.setCategorias(categorias);

        Caderno atualizado = cadernoRepository.save(caderno);
        return ResponseEntity.ok(atualizado);
    }

    public Caderno buscarPorId(Long id) {
        return cadernoRepository.findById(id).orElseThrow(() -> new RuntimeException("Caderno não encontrado com ID:" + id));
    }

    public List<Caderno> list() {
        Sort sort = Sort.by("status_caderno").descending().and(Sort.by("titulo_caderno").ascending());
        return cadernoRepository.findAll(sort);
    }

    public List<Caderno> list(StatusCaderno statusCaderno, String sortBy, String direction, String nome_categoria) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
        Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        if (statusCaderno != null) {
            return cadernoRepository.findByStatus_caderno(statusCaderno, sort);
        }

        if (nome_categoria != null) {
            return cadernoRepository.findByCategoriasNome_categoria(nome_categoria);
        }

        return cadernoRepository.findAll(sort);
    }

    public String deleteById(Long id) {
        try{
        cadernoRepository.deleteById(id);
        return "O caderno foi deletado";
        } catch (Exception erro) {
            return "O caderno não foi deletado";
        }
    }

}
