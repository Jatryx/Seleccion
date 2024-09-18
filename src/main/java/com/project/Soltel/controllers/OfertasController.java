package com.project.Soltel.controllers;

import com.project.Soltel.models.OfertasModel;
import com.project.Soltel.repositories.IOfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasController {

    @Autowired
    private IOfertaRepository ofertaRepository;

    @GetMapping
    public List<OfertasModel> getAllOfertas() {
        return ofertaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertasModel> getOfertaById(@PathVariable("id") int id) {
        Optional<OfertasModel> oferta = ofertaRepository.findById(id);
        return oferta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OfertasModel> createOferta(@RequestBody OfertasModel oferta) {
        OfertasModel savedOferta = ofertaRepository.save(oferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOferta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertasModel> updateOferta(@PathVariable("id") int id, @RequestBody OfertasModel ofertaDetails) {
        Optional<OfertasModel> oferta = ofertaRepository.findById(id);
        if (oferta.isPresent()) {
            OfertasModel existingOferta = oferta.get();
            // Actualiza los campos necesarios aquí
            existingOferta.setNombreCandidato(ofertaDetails.getNombreCandidato());
            existingOferta.setCodope(ofertaDetails.getCodope());
            // Añadir actualización de otros campos
            OfertasModel updatedOferta = ofertaRepository.save(existingOferta);
            return ResponseEntity.ok(updatedOferta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOferta(@PathVariable("id") int id) {
        if (ofertaRepository.existsById(id)) {
            ofertaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
