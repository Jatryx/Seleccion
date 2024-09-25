package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.services.EmpresaService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Método GET para obtener la lista de empresas
    @GetMapping("/consultar")
    public List<EmpresaModel> obtenerTodasEmpresa() {
        return empresaService.consultarTodasEmpresas();
    }

    @GetMapping("/consultar/{nombreEmpresa}")
    public Optional<EmpresaModel> obtenerEmpresaPorNombre(@PathVariable String nombreEmpresa) {
        return empresaService.consultarNombreEmpresa(nombreEmpresa);
    }

    // Método POST para agregar una nueva empresa
    @PostMapping("/insertar")
    public ResponseEntity<?> agregarEmpresa(@RequestBody EmpresaModel empresa) {
    	Optional<EmpresaModel> empresaExistente = empresaService.consultarNombreEmpresa(empresa.getNombreEmpresa());
		if (empresaExistente.isPresent()) {
			String mensaje = "Ya existe una empresa con el nombre: " + empresa.getNombreEmpresa();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}else {
		EmpresaModel guardarEmpresa = empresaService.guardarEmpresa(empresa);
		return ResponseEntity.ok(guardarEmpresa);
		}
    }

    @PutMapping("/actualizar/{nombreEmpresa}")
    public ResponseEntity<?> actualizarEmpresa(@PathVariable String nombreEmpresa, @RequestBody EmpresaModel nuevoNombre) {
    	 Optional<EmpresaModel> empresa = empresaService.consultarNombreEmpresa(nombreEmpresa);
         Optional<EmpresaModel> empresaNueva = empresaService.consultarNombreEmpresa(nuevoNombre.getNombreEmpresa());
         if (empresaNueva.isPresent()) {
        	 String mensaje = "Ya existe una empresa con el nombre: " + nuevoNombre.getNombreEmpresa();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
         } else if (empresa.isPresent()) {
             empresa.get().setNombreEmpresa(nuevoNombre.getNombreEmpresa());
             EmpresaModel guardarEmpresa = empresaService.actualizarEmpresa(empresa.get());
             return ResponseEntity.ok(guardarEmpresa);
         } else {
             String mensaje = "No existe una empresa con nombre " + nombreEmpresa;
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
         }
     }
    

    // Método DELETE para eliminar una empresa por su ID
    @PutMapping("/desactivar/{nombreEmpresa}")
    public String eliminarempresa(@PathVariable String nombreEmpresa) {
        Optional<EmpresaModel> empresa = empresaService.consultarNombreEmpresa(nombreEmpresa);
        if (empresa.isPresent()) {
            empresa.get().setActivo(false);
            return "Empresa desactivada";
        } else {
            return "Empresa no encontrada";
        }
    }
}

