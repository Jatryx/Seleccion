package com.project.Soltel.controllers;

import com.project.Soltel.models.UsuarioModel;
import com.project.Soltel.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/todosUsuarios")
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioService.consultarTodosUsuarios();
    }

    @GetMapping("/consultarCodope/{codope}")
    public ResponseEntity<UsuarioModel> getUsuarioByCodope(@PathVariable("codope") String codope) {
        UsuarioModel usuario = usuarioService.consultarUsuarioCodope(codope);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }    
    }

    @PostMapping("/añadirUsuario")
    public ResponseEntity<UsuarioModel> createUsuario(@RequestBody UsuarioModel usuario) {
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setCodope(usuario.getCodope());
        nuevoUsuario.setContraseña(usuario.getContraseña());
        UsuarioModel savedUsuario = usuarioService.guardarUsuario(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/actualizar/{codope}")
    public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable("codope") String codopeantiguo, @RequestBody UsuarioModel usuarioDetails) {
        UsuarioModel usuario = usuarioService.consultarUsuarioCodope(codopeantiguo);
        if (usuario != null) {
            usuario.setContraseña(usuarioDetails.getContraseña());
            UsuarioModel updatedUsuario = usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/eliminar/{codope}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("codope") String codope) {
        UsuarioModel usuario = usuarioService.consultarUsuarioCodope(codope);
        if (usuario != null) {
            usuario.setActivo(false);
            usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
