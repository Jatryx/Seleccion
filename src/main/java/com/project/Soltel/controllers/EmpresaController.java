package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.services.EmpresaService;
import java.util.List;
import java.util.Optional;


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
    public Optional<EmpresaModel> obtenerEmpresaPorNombre(@PathVariable String nombreEmpresa) {
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
        Optional<EmpresaModel> empresaNueva = empresaService.consultarNombreEmpresa(nuevoNombre);
        if (empresaNueva != null) {
            String mensaje = "Ya existe una empresa con el nombre: " + nuevoNombre;
            return ResponseEntity.status(404).body(mensaje);
        }
        Optional<EmpresaModel> empresa = empresaService.consultarNombreEmpresa(nombreEmpresa);
        if (empresa.isPresent()) {
            empresa.get().setNombreEmpresa(nuevoNombre);
            EmpresaModel empresaActualizada = empresaService.actualizarEmpresa(empresa.get());
            return ResponseEntity.ok(empresaActualizada);
        } else {
            String mensaje = "Empresa no encontrada";
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    // Método DELETE para eliminar una empresa por su ID
    @PutMapping("/eliminar/{nombreEmpresa}")
    public String eliminarempresa(@PathVariable String nombreEmpresa) {
        Optional<EmpresaModel> empresa = empresaService.consultarNombreEmpresa(nombreEmpresa);
        if (empresa.isPresent()) {
            empresa.get().setActivo(false);
            return "Empresa eliminada";
        } else {
            return "Empresa no encontrada";
        }
    }
}

