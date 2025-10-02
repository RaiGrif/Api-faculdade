package com.eniac.projeto.agendaeducacional.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;
import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.repository.CadernoRepository;
import com.eniac.projeto.agendaeducacional.repository.CategoriaRepository;
import com.eniac.projeto.agendaeducacional.repository.UsuarioRepository;
import com.eniac.projeto.agendaeducacional.DTO.CategoriaRequest;

@Service
public class CadernoService {
    private CadernoRepository cadernoRepository;
    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;

    public CadernoService (CadernoRepository cadernoRepository_, CategoriaRepository categoriaRepository_, UsuarioRepository usuarioRepository_) {
        this.cadernoRepository = cadernoRepository_;
        this.categoriaRepository = categoriaRepository_;
        this.usuarioRepository = usuarioRepository_;
    }

    public Caderno create(Caderno caderno, Long id_usuario) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow(() -> new RuntimeException("Usuário não encontrao"));
        caderno.setId_usuario(usuario);
        return cadernoRepository.save(caderno);
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

    public List<Caderno> list(StatusCaderno statusCaderno, List<String> sortBy, String direction, String nome_categoria) {

        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = List.of("dataCriacaoCaderno");
        }

        Sort sort = Sort.by(dir, sortBy.toArray(new String[0]));

        if (statusCaderno != null && nome_categoria != null) {
            return cadernoRepository.findByStatusCadernoAndCategoriasNomeCategoria(statusCaderno, nome_categoria, sort);
        }

        if (statusCaderno != null) {
            return cadernoRepository.findByStatusCaderno(statusCaderno, sort);
        }

        if (nome_categoria != null) {
            return cadernoRepository.findByCategoriasNomeCategoria(nome_categoria, sort );
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
