package com.project.Soltel.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatosController {

    // Lista en memoria para almacenar candidatos temporalmente
    private List<String> candidatos = new ArrayList<>();

    // Constructor vacío
    public CandidatosController() {
        // Inicialmente puedes añadir algún candidato a la lista
        candidatos.add("Candidato 1");
        candidatos.add("Candidato 2");
    }

    // Método GET para obtener la lista de candidatos
    @GetMapping
    public List<String> obtenerCandidatos() {
        return candidatos;
    }

    // Método POST para agregar un nuevo candidato
    @PostMapping
    public String agregarCandidato(@RequestBody String candidato) {
        candidatos.add(candidato);
        return "Candidato añadido: " + candidato;
    }

    // Método DELETE para eliminar un candidato por su ID (índice)
    @DeleteMapping("/{id}")
    public String eliminarCandidato(@PathVariable int id) {
        if (id >= 0 && id < candidatos.size()) {
            String eliminado = candidatos.remove(id);
            return "Candidato eliminado: " + eliminado;
        } else {
            return "Candidato no encontrado";
        }
    }
}

