package com.eniac.projeto.agendaeducacional.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.DTO.UsuarioDTO;
import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository_) {
        this.usuarioRepository = usuarioRepository_;
    }

    public Optional<Usuario> create(Usuario usuario ) {
        try{ 
            usuarioRepository.save(usuario);
            return usuarioRepository.findByEmail(usuario.getEmail().toString());
        } catch (Exception erro) {
            return null ;
        }
    } 
    

    public String update (Usuario usuario, Long id_usuario) {
        try{
            Usuario usuarioExistente = usuarioRepository.findById(id_usuario).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
            
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            
            usuarioRepository.save(usuarioExistente);
            return "Usuário atualizado com sucesso";
        } catch (Exception erro) {
            return "Erro: " + erro.getMessage() + " ao tentar atualizar úsuario" ;
        }
    }

    public String delete(Long id){
        try{
            usuarioRepository.deleteById(id);
            return "O usuario foi deletado";
        } catch (Exception erro) {
            return "O usuario não foi deletado";
        }
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
        }

    public Long buscarEmailESenha(UsuarioDTO usuarioDTO) {
         Optional<Usuario> optUsuario =
        usuarioRepository.findByEmail(usuarioDTO.getEmail());

    if (optUsuario.isEmpty()) {
        return null;
    }

    Usuario usuario = optUsuario.get();

    if (!usuario.getSenha().equals(usuarioDTO.getSenha())) {
        return null;
    }

    return usuario.getId();
}
}