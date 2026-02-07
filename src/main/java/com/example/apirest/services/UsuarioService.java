package com.example.apirest.services;

import com.example.apirest.dtos.AtualizarUsuarioDTO;
import com.example.apirest.exception.UsuarioNaoEncontradoException;
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
        List<Usuario> usuarios =  nome.isEmpty()
                ? usuarioRepository.findAll()
                : usuarioRepository.findAllByNomeContainingIgnoreCase(nome);

        return Optional.of(usuarios)
                .filter(lista -> !lista.isEmpty())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Nenhum usuário encontrado!"));
    }

    public Usuario listarUserPeloId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Nenhum usuário encontrado!"));
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public void atualizarUsuario(Usuario usuario, Long id){
        Usuario usuarioAtualizado = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Nenhum usuário encontrado!"));

        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setEmail(usuario.getEmail());

        usuarioRepository.save(usuarioAtualizado);
    }

    public void atualizarUsuarioPeloNomeOuEmail(AtualizarUsuarioDTO usuarioDTO, Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Nenhum usuário encontrado!"));

        if (usuarioDTO.getNome() != null) {
            usuario.setNome(usuarioDTO.getNome());
        }

        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }

        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new UsuarioNaoEncontradoException("Nenhum usuário encontrado!");
        }
        usuarioRepository.deleteById(id);
    }

}
