package com.example.apirest.repositorys;

import com.example.apirest.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
