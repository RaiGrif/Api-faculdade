package com.eniac.projeto.agendaeducacional.service;

import org.springframework.stereotype.Service;

import com.eniac.projeto.agendaeducacional.entity.Usuario;
import com.eniac.projeto.agendaeducacional.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository_) {
        this.usuarioRepository = usuarioRepository_;
    }

    public String create(Usuario usuario ) {
        try{ 
            usuarioRepository.save(usuario);
            return "Usuário adicionado com sucesso";
        } catch (Exception erro) {
            return "Erro: " + erro.getMessage() + " ao tentar adicionar úsuario" ;
        }
    } 
    

    public String update (Usuario usuario) {
        try{ 
            usuarioRepository.save(usuario);
            return "Usuário atualizado com sucesso";
        } catch (Exception erro) {
            return "Erro: " + erro.getMessage() + " ao tentar atualizar úsuario" ;
        }
    }

    public String delete(Long id){
        if (id != null) {
        try{ 
            usuarioRepository.deleteById(id);
            return "Usuário apagado com sucesso";
        } catch (Exception erro) {
            return "Erro: " + erro.getMessage() + " ao tentar apagar úsuario" ;
        }
        }
        return "Necessita de um id para deletar";
    }
}
