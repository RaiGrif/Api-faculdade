package com.eniac.projeto.agendaeducacional.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.entity.Categoria;
import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.repository.CategoriaRepository;
import com.eniac.projeto.agendaeducacional.repository.UsuarioRepository;

@Service
public class CategoriaService {
    private final UsuarioRepository usuarioRepository;
    private CategoriaRepository categoriaRepository;

    public CategoriaService (CategoriaRepository categoriaRepository_, UsuarioRepository usuarioRepository_, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository_;
        this.usuarioRepository = usuarioRepository_;
    }

    public List<Categoria> create(Long id_usuario,Categoria categoria){
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow(() -> new RuntimeException("Usuário não encontrao"));
        categoria.setUsuario(usuario);
        categoriaRepository.save(categoria);
        return list(id_usuario);
    }

    public List<Categoria> list(Long id) {
        return categoriaRepository.findAllByUsuarioIdOrderByNomeCategoriaAsc(id);
    }

    public List<Categoria> update(Long id,Categoria categoria){
        try {
        Categoria categoriaExistente = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));

        // Atualiza apenas os campos que podem mudar
        categoriaExistente.setNome_categoria(categoria.getNome_categoria());;
        categoriaExistente.setCor(categoria.getCor());;
        categoriaExistente.setCadernos(categoria.getCadernos());
        
        categoriaRepository.save(categoriaExistente);
        return list(id);
    } catch (Exception e) {
        e.printStackTrace(); // 
        return list(id);
    }
        
    }

    public List<Categoria> deleteById(Long id){
        try{
        categoriaRepository.deleteById(id);
        return list(id);
        } catch (Exception erro) {
            return list(id);
        }
        
    }
}
