package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.services.EmpresaService;
import java.util.List;


@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Método GET para obtener la lista de empresas
    @GetMapping("/consultarTodasEmpresa")
    public List<EmpresaModel> obtenerTodasEmpresa() {
        return empresaService.consultarTodasEmpresas();
    }

    @GetMapping("/empresa/{nombreEmpresa}")
    public EmpresaModel obtenerEmpresaPorNombre(@PathVariable String nombreEmpresa) {
        return empresaService.consultarNombreEmpresa(nombreEmpresa);
    }

    // Método POST para agregar una nueva empresa
    @PostMapping("/insertar")
    public ResponseEntity<?> agregarEmpresa(@RequestBody String empresa) {
        EmpresaModel nuevaEmpresa = new EmpresaModel();
        nuevaEmpresa.setNombreEmpresa(empresa);
        EmpresaModel guardarEmpresa = empresaService.guardarEmpresa(nuevaEmpresa);
        return ResponseEntity.ok(guardarEmpresa);
    }

    @PutMapping("/actualizar/{nombreEmpresa}")
    public ResponseEntity<?> actualizarEmpresa(@PathVariable String nombreEmpresa, @RequestBody String nuevoNombre) {
        EmpresaModel empresaNueva = empresaService.consultarNombreEmpresa(nuevoNombre);
        if (empresaNueva != null) {
            String mensaje = "Ya existe una empresa con el nombre: " + nuevoNombre;
            return ResponseEntity.status(404).body(mensaje);
        }
        EmpresaModel empresa = empresaService.consultarNombreEmpresa(nombreEmpresa);
        if (empresa != null) {
            empresa.setNombreEmpresa(nuevoNombre);
            EmpresaModel empresaActualizada = empresaService.actualizarEmpresa(empresa);
            return ResponseEntity.ok(empresaActualizada);
        } else {
            String mensaje = "No se encontró la empresa con el nombre: " + nombreEmpresa;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    // Método DELETE para eliminar una empresa por su ID
    @PutMapping("/eliminar/{nombreEmpresa}")
    public String eliminarempresa(@PathVariable String nombreEmpresa) {
        EmpresaModel empresa = empresaService.consultarNombreEmpresa(nombreEmpresa);
        if (empresa != null) {
            empresa.setActivo(false);
            EmpresaModel empresaEliminada = empresaService.actualizarEmpresa(empresa);
            return "Empresa eliminada: " + nombreEmpresa;
        } else {
            return "Empresa no encontrada";
        }
    }
}

