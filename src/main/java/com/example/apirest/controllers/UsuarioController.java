package com.example.apirest.controllers;

import com.example.apirest.dtos.AtualizarUsuarioDTO;
import com.example.apirest.models.Usuario;
import com.example.apirest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosUsuarios(
            @RequestParam(defaultValue = "") String nome
    ) {
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.listarUserPeloId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuarionovo,@PathVariable Long id){
        usuarioService.atualizarUsuario(usuarionovo,id);

        Usuario usuario = usuarioService.buscarUsuarioPorId(id).get();

        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(
            @RequestBody AtualizarUsuarioDTO dto,
            @PathVariable Long id
    ) {
        usuarioService.atualizarUsuarioPeloNomeOuEmail(dto, id);

        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.get());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
