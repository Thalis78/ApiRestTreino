package com.example.apirest.controllers;

import com.example.apirest.dtos.AtualizarServicoDTO;
import com.example.apirest.models.Servico;
import com.example.apirest.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicoController {
    @Autowired
    private ServicoService service;

    @PostMapping
    public ResponseEntity<?> criarServico(@RequestBody Servico servico){
        service.cadastrar(servico);
        return ResponseEntity.ok(servico);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosOsServicos(@RequestParam(required = false) String nome){
        List<Servico> servicos = service.listarTodos(nome);

        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id){
        Servico servico = service.procurarPeloId(id);

        if(servico == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarServico(@PathVariable Long id, @RequestBody Servico servicoNovo){
        service.atualizar(servicoNovo, id);

        Servico servico = service.procurarPeloId(id);

        return ResponseEntity.ok(servico);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarNomeouDescricao(@PathVariable Long id, @RequestBody AtualizarServicoDTO servicoDTO){
        service.atualizarNomeOuDescricao(servicoDTO, id);

        Servico servico = service.procurarPeloId(id);

        return ResponseEntity.ok(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);

        return ResponseEntity.ok().build();
    }
}
