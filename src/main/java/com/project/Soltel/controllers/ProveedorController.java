package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.ProveedorModel;
import com.project.Soltel.services.ProveedorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Método GET para obtener la lista de proveedores
    @GetMapping("/consultar")
    public List<ProveedorModel> obtenerTodosProveedores() {
        return proveedorService.consultarTodosProveedores();
    }

    // Método GET para obtener un proveedor por su nombre
    @GetMapping("/consultar/{nombreProveedor}")
    public Optional<ProveedorModel> obtenerProveedorPorNombre(@PathVariable String nombreProveedor) {
        return proveedorService.consultarNombreProveedor(nombreProveedor);
    }

    // Método POST para agregar un nuevo proveedor
    @PostMapping("/insertar")
    public ResponseEntity<?> agregarProveedor(@RequestBody ProveedorModel proveedor) {
        Optional<ProveedorModel> proveedorExistente = proveedorService.consultarNombreProveedor(proveedor.getNombreProveedor());
        if (proveedorExistente.isPresent()) {
            String mensaje = "Ya existe un proveedor con el nombre: " + proveedor.getNombreProveedor();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        } else {
            ProveedorModel guardarProveedor = proveedorService.guardarProveedor(proveedor);
            return ResponseEntity.ok(guardarProveedor);
        }
    }

    // Método PUT para actualizar un proveedor por su nombre
    @PutMapping("/actualizar/{nombreProveedor}")
    public ResponseEntity<?> actualizarProveedor(String nombreProveedor, @RequestBody ProveedorModel nuevoProveedor) {
        Optional<ProveedorModel> proveedor = proveedorService.consultarNombreProveedor(nombreProveedor);
        Optional<ProveedorModel> proveedorNuevo = proveedorService.consultarNombreProveedor(nuevoProveedor.getNombreProveedor());
        int proveedorDatos;
        int proveedorNuevoDatos;

        if (proveedor.isPresent() && proveedorNuevo.isPresent()) {
            proveedorDatos = proveedor.get().getIdProveedor();  // Usando hashCode para comparación de nombre
            proveedorNuevoDatos = proveedorNuevo.get().getIdProveedor();
        } else {
            proveedorDatos = proveedor.map(ProveedorModel::getIdProveedor).orElse(0);  // Asigna vacío si no existe
            proveedorNuevoDatos = proveedorNuevo.map(ProveedorModel::getIdProveedor).orElse(1);  // Asigna vacío si no existe
        }

        if (proveedor.isPresent() && !proveedorNuevo.isPresent() || 
            proveedorDatos == proveedorNuevoDatos) {

            ProveedorModel proveedorActualizado = proveedor.get();
            proveedorActualizado.setNombreProveedor(nuevoProveedor.getNombreProveedor());
            proveedorActualizado.setRentabilidadProveedor(nuevoProveedor.getRentabilidadProveedor());
            proveedorActualizado.setActivo(nuevoProveedor.getActivo());
            ProveedorModel guardarProveedor = proveedorService.actualizarProveedor(proveedorActualizado);
            return ResponseEntity.ok(guardarProveedor);

        } else if (!proveedor.isPresent()) {
            String mensaje = "No existe un proveedor con el nombre: " + nombreProveedor;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);

        } else {
            String mensaje = "Ya existe un proveedor con el nombre: " + nuevoProveedor.getNombreProveedor();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    // Método PUT para desactivar un proveedor por su nombre
    @PutMapping("/desactivar/{nombreProveedor}")
    public ResponseEntity<?> desactivarProveedor(@PathVariable String nombreProveedor) {
        Optional<ProveedorModel> proveedor = proveedorService.consultarNombreProveedor(nombreProveedor);
        if (proveedor.isPresent()) {
            proveedor.get().setActivo(false);
            proveedorService.actualizarProveedor(proveedor.get());
            return ResponseEntity.ok("Proveedor desactivado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
        }
    }
}
