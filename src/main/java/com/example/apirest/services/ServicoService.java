package com.example.apirest.services;

import com.example.apirest.dtos.AtualizarServicoDTO;
import com.example.apirest.models.Servico;
import com.example.apirest.repositorys.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    ServicoRepository repository;

    public void cadastrar(Servico servico){
        repository.save(servico);
    }

    public Servico procurarPeloId(Long id){
        Optional<Servico> servico = repository.findById(id);

        return servico.orElse(null);
    }

    public List<Servico> listarTodos(String nome){
        if(nome != null){
            return repository.findAllByNomeContainingIgnoreCase(nome);
        }
        return repository.findAll();
    }

    public void atualizar(Servico servico, Long id){
        Optional<Servico> servicoAtualizado = repository.findById(id);

        if(servicoAtualizado.isPresent()){
            Servico servicoNovo = servicoAtualizado.get();
            servicoNovo.setNome(servico.getNome());
            servicoNovo.setDescricao(servico.getDescricao());

            repository.save(servicoNovo);
        }
    }

    public void atualizarNomeOuDescricao(AtualizarServicoDTO dto, Long id){
        Optional<Servico> servico = repository.findById(id);

        if(servico.isPresent()){
            Servico servicoNovo = servico.get();
            if (dto.getNome() != null){
                servicoNovo.setNome(dto.getNome());
            }

            if (dto.getDescricao() != null){
                servicoNovo.setDescricao(dto.getDescricao());
            }

            repository.save(servicoNovo);
        }

    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
