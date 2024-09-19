package com.project.Soltel.controllers;

import com.project.Soltel.models.OfertasModel;
import com.project.Soltel.services.OfertaService;

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
    private  OfertaService ofertaService;

    @GetMapping("/todasOfertas")
    public List<OfertasModel> getAllOfertas() {
        return ofertaService.consultarTodasOfertas();
    }

    @GetMapping("/consultarOferta/{id}")
    public ResponseEntity<OfertasModel> getOfertaById(@PathVariable("id") int id) {
        Optional<OfertasModel> oferta = ofertaService.consultarOfertasId(id);
        if (oferta.isPresent()) {
            return ResponseEntity.ok(oferta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/añadirOferta")
    public ResponseEntity<?> createOferta(@RequestBody OfertasModel oferta) {

        String nombreCandidato = oferta.getCandidato().getNombreCandidato();
        Integer idPeticion = oferta.getRecruiting().getIdRecruiting();

        OfertasModel ofertaAcoparar = ofertaService.consultarPorNombreCandidatoAndIdPeticion(nombreCandidato, idPeticion);

        if (nombreCandidato.equals(ofertaAcoparar.getNombreCandidato()) && idPeticion.equals(ofertaAcoparar.getRecruiting().getIdRecruiting())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una oferta con el nombre del candidato: " + nombreCandidato + " y el id de la petición: " + idPeticion);
        }

        OfertasModel nuevaOferta = new OfertasModel();
        nuevaOferta.setCandidato(oferta.getCandidato());
        nuevaOferta.setEmpresa(oferta.getEmpresa());
        nuevaOferta.setEstado(oferta.getEstado());
        nuevaOferta.setExperiencia(oferta.getExperiencia());
        nuevaOferta.setFechaActualizacion(oferta.getFechaActualizacion());
        nuevaOferta.setObservaciones(oferta.getObservaciones());
        nuevaOferta.setNombreCandidato(oferta.getNombreCandidato());
        nuevaOferta.setUsuario(oferta.getUsuario());
        nuevaOferta.setRecruiting(oferta.getRecruiting());
        nuevaOferta.setUbicacion(oferta.getUbicacion());
        nuevaOferta.setPuesto(oferta.getPuesto());
        nuevaOferta.setTecnologias(oferta.getTecnologias());
        nuevaOferta.setSalario(oferta.getSalario());
        nuevaOferta.setActivo(true);

        String informacion = "Se ha creado la oferta el dia " + oferta.getFechaActualizacion() + " con el estado " + oferta.getEstado().getEstado();
        nuevaOferta.setHistoricoCambioEstados(informacion);

        OfertasModel savedOferta = ofertaService.guardarOferta(nuevaOferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOferta);

    }

    @PutMapping("/actualizarOferta/{id}")
    public ResponseEntity<?> updateOferta(@PathVariable("id") int id, @RequestBody OfertasModel ofertaDetails) {
        Optional<OfertasModel> oferta = ofertaService.consultarOfertasId(id);
        if (oferta.isPresent()) {
            OfertasModel ofertaActualizada = oferta.get();
            ofertaActualizada.setCandidato(ofertaDetails.getCandidato());
            ofertaActualizada.setEmpresa(ofertaDetails.getEmpresa());
            ofertaActualizada.setEstado(ofertaDetails.getEstado());
            ofertaActualizada.setExperiencia(ofertaDetails.getExperiencia());
            ofertaActualizada.setFechaActualizacion(ofertaDetails.getFechaActualizacion());
            ofertaActualizada.setObservaciones(ofertaDetails.getObservaciones());
            ofertaActualizada.setNombreCandidato(ofertaDetails.getNombreCandidato());
            ofertaActualizada.setUsuario(ofertaDetails.getUsuario());
            ofertaActualizada.setRecruiting(ofertaDetails.getRecruiting());
            ofertaActualizada.setUbicacion(ofertaDetails.getUbicacion());
            ofertaActualizada.setPuesto(ofertaDetails.getPuesto());
            ofertaActualizada.setTecnologias(ofertaDetails.getTecnologias());
            ofertaActualizada.setSalario(ofertaDetails.getSalario());

            // Obtener la información existente en historicoCambioEstados
            String historicoActual = ofertaActualizada.getHistoricoCambioEstados();

            // Crear la nueva información a añadir
            String nuevaInformacion = "Se ha actualizado la oferta el dia " + ofertaDetails.getFechaActualizacion() + " con el estado " + ofertaDetails.getEstado().getEstado();

            // Concatenar la nueva información con la existente
            String informacionActualizada = (historicoActual != null ? historicoActual + "\n" : "") + nuevaInformacion;

            // Actualizar el campo historicoCambioEstados con la información acumulada
            ofertaActualizada.setHistoricoCambioEstados(informacionActualizada);

            OfertasModel updatedOferta = ofertaService.actualizarOferta(ofertaActualizada);
            return ResponseEntity.ok(updatedOferta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la oferta con el id: " + id);
        }
    }

    @PutMapping("/eliminarOferta/{id}")
    public ResponseEntity<?> deleteOferta(@PathVariable("id") int id) {
        Optional<OfertasModel> oferta = ofertaService.consultarOfertasId(id);
        if (oferta.isPresent()) {
            OfertasModel ofertaEliminada = oferta.get();
            ofertaEliminada.setActivo(false);
            OfertasModel updatedOferta = ofertaService.actualizarOferta(ofertaEliminada);
            return ResponseEntity.ok(updatedOferta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la oferta con el id: " + id);
        }
    }
}
