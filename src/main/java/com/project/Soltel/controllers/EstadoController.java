package com.project.Soltel.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    // Clase Candidato para representar un candidato con nombre y estado
    static class Candidato {
        private String nombre;
        private String estado;

        public Candidato(String nombre, String estado) {
            this.nombre = nombre;
            this.estado = estado;
        }

        // Getters y Setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }

    // Lista de candidatos
    private List<Candidato> candidatos = new ArrayList<>();

    // Constructor de la clase
    public EstadoController() {
        // Inicialmente puedes añadir algunos candidatos a la lista
        candidatos.add(new Candidato("Candidato 1", "En proceso"));
        candidatos.add(new Candidato("Candidato 2", "Autodescartado"));
    }

    // Método GET para obtener la lista de candidatos
    @GetMapping
    public List<Candidato> obtenerCandidatos() {
        return candidatos;  // Retorna la lista de candidatos
    }

    // Método POST para agregar un nuevo candidato
    @PostMapping
    public String agregarCandidato(@RequestBody Candidato candidato) {
        candidatos.add(candidato);
        return "Candidato añadido: " + candidato.getNombre() + " con estado: " + candidato.getEstado();
    }

    // Método DELETE para eliminar un candidato por su ID (índice)
    @DeleteMapping("/{id}")
    public String eliminarCandidato(@PathVariable int id) {
        if (id >= 0 && id < candidatos.size()) {
            Candidato eliminado = candidatos.remove(id);
            return "Candidato eliminado: " + eliminado.getNombre() + " con estado: " + eliminado.getEstado();
        } else {
            return "Candidato no encontrado";
        }
    }

    // Método PUT para actualizar el estado de un candidato
    @PutMapping("/{id}")
    public String actualizarEstadoCandidato(@PathVariable int id, @RequestBody String nuevoEstado) {
        if (id >= 0 && id < candidatos.size()) {
            Candidato candidato = candidatos.get(id);
            candidato.setEstado(nuevoEstado);
            return "Estado del candidato actualizado a: " + nuevoEstado;
        } else {
            return "Candidato no encontrado";
        }
    }
}
