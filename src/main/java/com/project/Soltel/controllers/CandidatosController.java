package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.services.CandidatosService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatosController {
	
    @Autowired
    private CandidatosService candidatosService;

    // Método GET para obtener la lista de candidatos
    @GetMapping("/consultar")
	public ResponseEntity<List<CandidatosModel>> getAllCandidatos(){
		return ResponseEntity.ok(candidatosService.consultarTodosCandidatos());
				
	}
    
    @GetMapping("/consultar/{nombreCandidato}")
	public ResponseEntity<?> getCandidatoPorNombre(@PathVariable String nombreCandidato){
    	Optional<CandidatosModel> candidatoABuscar = candidatosService.consultarNombreCandidatos(nombreCandidato);
    	if (candidatoABuscar.isPresent()) {
		return ResponseEntity.ok(candidatosService.consultarNombreCandidatos(nombreCandidato));			
    	} else {
    		String mensaje = "No se ha encontrado a: " + nombreCandidato;
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
    	} 	
    }

    // Método POST para agregar un nuevo candidato
    
    
    @PostMapping("/insertar")
    public ResponseEntity<?> agregarCandidato(@RequestBody CandidatosModel Candidato) {
		Optional<CandidatosModel> candidato = candidatosService.consultarNombreCandidatos(Candidato.getNombreCandidato());
		if (candidato.isPresent()) {
			String mensaje = "Ya existe un candidato con el nombre: " + Candidato.getNombreCandidato();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}else {
		CandidatosModel guardarCandidato = candidatosService.guardarCandidatos(Candidato);
		return ResponseEntity.ok(guardarCandidato);
		}
    }
    
	@PutMapping("/actualizar/{nombreCandidato}")
	public ResponseEntity<?> actualizarcandidato(String nombreCandidato, @RequestBody CandidatosModel nombreCandidatoNuevo) {
		Optional<CandidatosModel> candidato = candidatosService.consultarNombreCandidatos(nombreCandidato);
		Optional<CandidatosModel> candidatoNuevo = candidatosService.consultarNombreCandidatos(nombreCandidatoNuevo.getNombreCandidato());
		int candidatoDatos;
		int candidatoNuevoDatos;
		
		if (candidato.isPresent() && candidatoNuevo.isPresent()) {
			candidatoDatos = candidato.get().getIdCandidato();
			candidatoNuevoDatos = candidatoNuevo.get().getIdCandidato();
		}else {
			candidatoDatos = candidato.map(CandidatosModel::getIdCandidato).orElse(0); // Asigna 0 si el expediente está vacío
		    candidatoNuevoDatos = candidatoNuevo.map(CandidatosModel::getIdCandidato).orElse(1); // Asigna 1 si el expedienteNuevo está vacío
		}
		if (candidato.isPresent() && !candidatoNuevo.isPresent() || 
				candidatoDatos == candidatoNuevoDatos) {
	
			CandidatosModel candidatoActualizado = candidato.get();
			candidatoActualizado.setNombreCandidato(nombreCandidatoNuevo.getNombreCandidato());
			candidatoActualizado.setActivo(nombreCandidatoNuevo.getActivo());
			CandidatosModel guardarTipo = candidatosService.actualizarCandidatos(candidatoActualizado);
			return ResponseEntity.ok(guardarTipo);
			
		} else if (!candidato.isPresent()){
			String mensaje = "No existe un candidato con el nombre: " + nombreCandidato;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
			
			}else{
				String mensaje = "Ya existe un canidadato con el nombre: " + nombreCandidatoNuevo.getNombreCandidato();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
			}
	}
	
	@PutMapping("/desactivar/{nombreCandidato}")
	public ResponseEntity<?> activarTiposExpediente(String nombreCandidato) {
		Optional<CandidatosModel> candidato = candidatosService.consultarNombreCandidatos(nombreCandidato);
		
		if (candidato.isPresent()) {
			
			CandidatosModel puestoBorrarLogico = candidato.get();
			puestoBorrarLogico.setActivo(false);
			CandidatosModel guardarCandidato = candidatosService.actualizarCandidatos(puestoBorrarLogico);
			return ResponseEntity.ok(guardarCandidato);
			
		}else{
			String mensaje = "No existe un tipo de expediente con el nombre: " + nombreCandidato;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}
	}

}

