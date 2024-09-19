package com.project.Soltel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.repositories.ICandidatosRepository;
import com.project.Soltel.services.CandidatosService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatosController {

    // Lista en memoria para almacenar candidatos temporalmente
    private List<String> candidatos = new ArrayList<>();
    @Autowired
    private CandidatosService candidatosService;

    // Constructor vacío
    public CandidatosController() {
        // Inicialmente puedes añadir algún candidato a la lista
        candidatos.add("Candidato 1");
        candidatos.add("Candidato 2");
    }

    // Método GET para obtener la lista de candidatos
    @GetMapping("/consultar")
	public ResponseEntity<List<CandidatosModel>> getAllCandidatos(){
		return ResponseEntity.ok(candidatosService.consultarTodosCandidatos());
				
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


    // Método DELETE para eliminar un candidato por su ID (índice)
    @DeleteMapping("/{id}")
    public String eliminarCandidato(@PathVariable int id) {
        if (id >= 0 && id < candidatos.size()) {
            String eliminado = candidatos.remove(id);
            return "Candidato eliminado: " + eliminado;
        } else {
            return "Candidato no encontrado";
        }
    }
}

