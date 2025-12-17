package com.eniac.projeto.agendaeducacional.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import jakarta.transaction.Transactional;

import com.eniac.projeto.agendaeducacional.DTO.CadernoDTO;
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
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        caderno.setUsuario(usuario);
        return cadernoRepository.save(caderno);
    }

    public String update(Caderno caderno, Long id_caderno) {
        try {
        Caderno cadernoExistente = cadernoRepository.findById(id_caderno)
            .orElseThrow(() -> new RuntimeException("Caderno não encontrado"));

        // Atualiza apenas os campos que podem mudar
        cadernoExistente.setTitulo_caderno(caderno.getTitulo_caderno());
        cadernoExistente.setConteudo(caderno.getConteudo());
        cadernoExistente.setStatus_caderno(caderno.getStatus_caderno());
        

        cadernoRepository.save(cadernoExistente);
        return "O caderno foi salvo com sucesso";
    } catch (Exception e) {
        e.printStackTrace(); // 
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

    public Optional<Caderno> buscarPorId(Long id) {
        return cadernoRepository.findById(id);
    }

    public List<Caderno> list() {
        Sort sort = Sort.by("status_caderno").descending().and(Sort.by("titulo_caderno").ascending());
        return cadernoRepository.findAll(sort);
    }

    public List<CadernoDTO> list(Long idUsuario,StatusCaderno statusCaderno, List<String> sortBy, String direction, String nome_categoria) {

        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = List.of("dataCriacaoCaderno");
        }

        Sort sort = Sort.by(dir, sortBy.toArray(new String[0]));
        
        List<Caderno> cadernos;

        if (statusCaderno != null && nome_categoria != null) {
            cadernos = cadernoRepository.findByUsuarioIdAndStatusCadernoAndCategoriasNomeCategoria(
                        idUsuario, statusCaderno, nome_categoria, sort
                );
        } else if (statusCaderno != null) {
            cadernos = cadernoRepository.findByUsuarioIdAndStatusCaderno(idUsuario,statusCaderno, sort);
        } else if (nome_categoria != null) {
            cadernos = cadernoRepository.findByUsuarioIdAndCategoriasNomeCategoria(idUsuario,nome_categoria, sort );
        } else {
            cadernos = cadernoRepository.findByUsuarioId(idUsuario,sort);
        }

        return cadernos.stream().map(CadernoDTO::new).toList();
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
