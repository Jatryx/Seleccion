package com.project.Soltel.controllers;

import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.repositories.IUbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

    @Autowired
    private IUbicacionRepository ubicacionRepository;

    @GetMapping
    public List<UbicacionModel> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionModel> getUbicacionById(@PathVariable("id") int id) {
        Optional<UbicacionModel> ubicacion = ubicacionRepository.findById(id);
        return ubicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UbicacionModel> createUbicacion(@RequestBody UbicacionModel ubicacion) {
        UbicacionModel savedUbicacion = ubicacionRepository.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUbicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionModel> updateUbicacion(@PathVariable("id") int id, @RequestBody UbicacionModel ubicacionDetails) {
        Optional<UbicacionModel> ubicacion = ubicacionRepository.findById(id);
        if (ubicacion.isPresent()) {
            UbicacionModel existingUbicacion = ubicacion.get();
            existingUbicacion.setNombreProvincia(ubicacionDetails.getNombreProvincia());
            existingUbicacion.setActivo(ubicacionDetails.getActivo());
            UbicacionModel updatedUbicacion = ubicacionRepository.save(existingUbicacion);
            return ResponseEntity.ok(updatedUbicacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable("id") int id) {
        if (ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
