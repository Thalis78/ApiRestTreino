package com.example.apirest.services;

import com.example.apirest.dtos.AtualizarUsuarioDTO;
import com.example.apirest.models.Usuario;
import com.example.apirest.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodosUsuarios(String nome){
        if(!nome.isEmpty()){
            return usuarioRepository.findAllByNomeContainingIgnoreCase(nome);
        }
        return usuarioRepository.findAll();
    }

    public Usuario listarUserPeloId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public void atualizarUsuario(Usuario usuario, Long id){
        Optional<Usuario> usuarioAtualizado = usuarioRepository.findById(id);

        if(usuarioAtualizado.isPresent()){
            Usuario usuarioNovo = usuarioAtualizado.get();
            usuarioNovo.setNome(usuario.getNome());
            usuarioNovo.setEmail(usuario.getEmail());

            usuarioRepository.save(usuarioNovo);
        }
    }

    public void atualizarUsuarioPeloNomeOuEmail(
            AtualizarUsuarioDTO usuarioDTO,
            Long id
    ) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario usuarioNovo = usuario.get();

            if (usuarioDTO.getNome() != null) {
                usuarioNovo.setNome(usuarioDTO.getNome());
            }

            if (usuarioDTO.getEmail() != null) {
                usuarioNovo.setEmail(usuarioDTO.getEmail());
            }

            usuarioRepository.save(usuarioNovo);
        }
    }


    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
