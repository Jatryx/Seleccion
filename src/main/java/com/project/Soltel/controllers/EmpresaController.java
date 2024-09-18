package com.project.Soltel.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    // Lista en memoria para almacenar empresas temporalmente
    private List<String> ListaEmpresa = new ArrayList<>();

    // Constructor vacío
    public EmpresaController() {
        // Inicialmente puedes añadir algún candidato a la lista
    	ListaEmpresa.add("INDRA");
    	ListaEmpresa.add("CAPGEMINI");
    }

    // Método GET para obtener la lista de empresas
    @GetMapping
    public List<String> obtenerEmpresa() {
        return ListaEmpresa;
    }

    // Método POST para agregar una nueva empresa
    @PostMapping
    public String agregarEmpresa(@RequestBody String empresa) {
        ListaEmpresa.add(empresa);
        return "Empresa añadida: " + empresa;
    }

    // Método DELETE para eliminar una empresa por su ID
    @DeleteMapping("/{id}")
    public String eliminarempresa(@PathVariable int id) {
        if (id >= 0 && id < ListaEmpresa.size()) {
            String eliminado = ListaEmpresa.remove(id);
            return "Empresa eliminada: " + eliminado;
        } else {
            return "Empresa no encontrada";
        }
    }
}

