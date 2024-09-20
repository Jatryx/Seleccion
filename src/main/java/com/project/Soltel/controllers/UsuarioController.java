package com.project.Soltel.controllers;

import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.models.UsuarioModel;
import com.project.Soltel.services.UsuarioService;

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
    private UsuarioService usuarioService;

    @GetMapping("/todosUsuarios")
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioService.consultarTodosUsuarios();
    }

    @GetMapping("/consultarCodope/{codope}")
    public ResponseEntity<UsuarioModel> getUsuarioByCodope(@PathVariable("codope") String codope) {
        Optional<UsuarioModel> usuario = usuarioService.consultarUsuarioCodope(codope);
        if (usuario != null) {
              return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.notFound().build();
            }   
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioModel codope) {
    	Optional<UsuarioModel> usuario = usuarioService.consultarUsuarioCodope(codope.getCodope());
		if (usuario.isPresent()) {
			String mensaje = "Ya existe un usuario con el CODOPE: " + codope.getCodope();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}else {
		UsuarioModel guardarUsuario = usuarioService.guardarUsuario(codope);
		return ResponseEntity.ok(guardarUsuario);
		}
    }

    @PutMapping("/actualizar/{codope}")
    public ResponseEntity<?> updateUsuario(@PathVariable("codope") String codope, @RequestBody UsuarioModel usuarioDetails) {
        Optional<UsuarioModel> usuario = usuarioService.consultarUsuarioCodope(codope);
        if (usuario.isPresent()) {
            usuario.get().setContraseña(usuarioDetails.getContraseña());
            usuario.get().setActivo(usuarioDetails.getActivo());
            UsuarioModel updatedUsuario = usuarioService.actualizarUsuario(usuario.get());
            return ResponseEntity.ok(updatedUsuario);
        } else {
        	String mensaje = "No existe un usuario con el CODOPE: " + codope;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
    @PutMapping("/activar/{codope}")
    public ResponseEntity<?> eliminarEstado(@PathVariable String codope) {
        Optional<UsuarioModel> usuario = usuarioService.consultarUsuarioCodope(codope);
          if (usuario.isPresent()) {
               usuario.get().setActivo(false);
               UsuarioModel usuarioEliminado = usuarioService.actualizarUsuario(usuario.get());
               return ResponseEntity.ok(usuarioEliminado);
          } else {
               String mensaje = "No existe un usuario con el CODOPE: " + codope;
               return ResponseEntity.status(404).body(mensaje);
          }
     }
}
