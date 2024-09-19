package com.project.Soltel.controllers;

import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.services.UbicacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping("/todasUbicaciones")
    public List<UbicacionModel> getAllUbicaciones() {
        return ubicacionService.consultarTodasUbicaciones();
    }

    @GetMapping("/consultarUbicacionId/{nombre}")
    public ResponseEntity<UbicacionModel> getUbicacionById(@PathVariable("nombre") String nombre) {
       UbicacionModel ubicacion = ubicacionService.consultarNombreUbicacion(nombre);
        if (ubicacion != null) {
            return ResponseEntity.ok(ubicacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/añadirUbicacion")
    public ResponseEntity<?> createUbicacion(@RequestBody UbicacionModel ubicacion) {
       UbicacionModel nuevaUbicacion = new UbicacionModel();
        nuevaUbicacion.setNombreProvincia(ubicacion.getNombreProvincia());
        nuevaUbicacion.setActivo(ubicacion.getActivo());
        UbicacionModel savedUbicacion = ubicacionService.guardarUbicacion(nuevaUbicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUbicacion);
    }

    @PutMapping("/actualizarUbicacion/{nombre}")
    public ResponseEntity<?> updateUbicacion(@PathVariable("nombre") String nombre, @RequestBody UbicacionModel ubicacionDetails) {
        UbicacionModel ubicacion = ubicacionService.consultarNombreUbicacion(nombre);
        if (ubicacion != null) {
            ubicacion.setNombreProvincia(ubicacionDetails.getNombreProvincia());
            ubicacion.setActivo(ubicacionDetails.getActivo());
            UbicacionModel updatedUbicacion = ubicacionService.actualizarUbicacion(ubicacion);
            return ResponseEntity.ok(updatedUbicacion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/eliminarUbicacion/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable("nombre") String nombre) {
        
        UbicacionModel ubicacion = ubicacionService.consultarNombreUbicacion(nombre);
        if (ubicacion != null) {
            ubicacion.setActivo(false);
            ubicacionService.actualizarUbicacion(ubicacion);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
