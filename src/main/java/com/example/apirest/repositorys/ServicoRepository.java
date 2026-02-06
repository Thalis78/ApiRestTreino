package com.example.apirest.repositorys;

import com.example.apirest.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findAllByNomeContainingIgnoreCase(String nome);
}
