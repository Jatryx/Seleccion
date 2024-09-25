package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.EstadoModel;
import com.project.Soltel.services.EstadoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    // Método GET para obtener la lista de candidatos
    @GetMapping("/consultar")
    public List<EstadoModel> obtenerTodosEstados() {
        return estadoService.consultarTodosEstados();
    }

    @GetMapping("/estado/{nombreEstado}")
    public Optional<EstadoModel> obtenerEstadoPorNombre(@PathVariable String nombreEstado) {
        return estadoService.consultarNombreEstado(nombreEstado);
    }

    // Método POST para agregar un nuevo candidato
    @PostMapping("/insertar")
    public ResponseEntity<?> agregarEstado(@RequestBody EstadoModel estado) {
        Optional<EstadoModel> candidatoBuscado = estadoService.consultarNombreEstado(estado.getEstado());
        if (candidatoBuscado.isPresent()) {
            String mensaje = "Ya existe un estado con el nombre: " + estado.getEstado();
            return ResponseEntity.status(404).body(mensaje);
        } else {
            EstadoModel nuevoEstado = new EstadoModel(estado.getEstado(), true);
            EstadoModel guardarEstado = estadoService.guardarEstado(nuevoEstado);
            return ResponseEntity.ok(guardarEstado);
        }    
    }


    @PutMapping("/desactivar/{nombreEstado}")
    public ResponseEntity<?> eliminarEstado(@PathVariable String nombreEstado) {
       Optional<EstadoModel> estado = estadoService.consultarNombreEstado(nombreEstado);
         if (estado.isPresent()) {
              estado.get().setActivo(false);
              estadoService.actualizarEstado(estado.get());
              String mensaje = "Se desactivo el estado: " + nombreEstado;
              return ResponseEntity.ok(mensaje);
         } else {
              String mensaje = "No se encontró el estado: " + nombreEstado;
              return ResponseEntity.status(404).body(mensaje);
         }
    }

    // Método PUT para actualizar el estado de un candidato
    @PutMapping("/actualizar/{nombreEstado}")
    public String actualizarEstadoCandidato(@PathVariable String nombreEstado, @RequestBody EstadoModel nuevoEstado) {
        Optional<EstadoModel> estado = estadoService.consultarNombreEstado(nombreEstado);
        Optional<EstadoModel> estadoNuevo = estadoService.consultarNombreEstado(nuevoEstado.getEstado());
        if (estadoNuevo.isPresent()) {
            return "Ya existe un estado con el nombre: " + nuevoEstado.getEstado();
        } else
        if (estado.isPresent()) {
            estado.get().setEstado(nuevoEstado.getEstado());
            estadoService.actualizarEstado(estado.get());
            return "Estado actualizado: " + nuevoEstado.getEstado();
        } else {
            return "No existe un estado con nombre" + nombreEstado;
        }
    }
}
