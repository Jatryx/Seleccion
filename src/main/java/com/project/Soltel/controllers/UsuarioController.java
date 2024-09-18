package com.project.Soltel.controllers;

import com.project.Soltel.models.UsuarioModel;
import com.project.Soltel.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{codope}")
    public ResponseEntity<UsuarioModel> getUsuarioByCodope(@PathVariable("codope") String codope) {
        UsuarioModel usuario = usuarioRepository.findUsuarioByCodope(codope);
        return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> createUsuario(@RequestBody UsuarioModel usuario) {
        UsuarioModel savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{codope}")
    public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable("codope") String codope, @RequestBody UsuarioModel usuarioDetails) {
        UsuarioModel existingUsuario = usuarioRepository.findUsuarioByCodope(codope);
        if (existingUsuario != null) {
            existingUsuario.setContraseña(usuarioDetails.getContraseña());
            existingUsuario.setActivo(usuarioDetails.getActivo());
            UsuarioModel updatedUsuario = usuarioRepository.save(existingUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codope}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("codope") String codope) {
        UsuarioModel usuarioOptional = usuarioRepository.findUsuarioByCodope(codope);
        if (usuarioOptional != null) {
            usuarioRepository.delete(usuarioOptional);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
