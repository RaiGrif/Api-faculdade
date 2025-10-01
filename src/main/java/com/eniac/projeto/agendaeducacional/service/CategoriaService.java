package com.eniac.projeto.agendaeducacional.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public CategoriaService (CategoriaRepository categoriaRepository_) {
        this.categoriaRepository = categoriaRepository_;
    }

    public List<Categoria> create(Categoria categoria){
        categoriaRepository.save(categoria);
        return list();
    }

    public List<Categoria> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "data_criacao_categoria");
        return categoriaRepository.findAll(sort);
    }

    public List<Categoria> update(Categoria categoria){
        categoriaRepository.save(categoria);
        return list();
    }

    public List<Categoria> deleteById(Long id){
        categoriaRepository.deleteById(id);
        return list();
    }
}
