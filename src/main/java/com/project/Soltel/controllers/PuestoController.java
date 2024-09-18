package com.project.Soltel.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Soltel.models.PuestoModel;
import com.project.Soltel.services.PuestoService;

@RestController
@RequestMapping("/api/puesto")
public class PuestoController {

		private final PuestoService puestoService;
		
		public PuestoController (PuestoService puestoService) {
			this.puestoService = puestoService;
		}
		
		
		
		// Consultar
		
		
				//FindALL
		
		@GetMapping("/api/consultar")
		public ResponseEntity<List<PuestoModel>> getAllPuesto(){
			return ResponseEntity.ok(puestoService.consultarTodosPuestos());
					
		}
		
		
		
		// Insertar
		
		@PostMapping("/api/insertar/{nombrespuesto}")
		public ResponseEntity<?> guardarPuesto(String nombrePuesto) {
			Optional<PuestoModel> puesto = puestoService.consultarNombrePuesto(nombrePuesto);
			if (puesto.isPresent()) {
				String mensaje = "Ya existe un tipo de expediente con el nombre: " + nombrePuesto;
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
			}else {
			PuestoModel nuevoPuesto = new PuestoModel(nombrePuesto, true);
			PuestoModel guardarPuesto = puestoService.guardarPuesto(nuevoPuesto);
			return ResponseEntity.ok(guardarPuesto);
		}
	}
		
		// Actualizar
		
		@PutMapping("/api/actualizar/{nombrespuesto}/{nombrepuestonuevo}/{activo}")
		public ResponseEntity<?> actualizarPuesto(String nombrePuesto, String nombrePuestoNuevo, boolean activo) {
			Optional<PuestoModel> puesto = puestoService.consultarNombrePuesto(nombrePuesto);
			Optional<PuestoModel> puestoNuevo = puestoService.consultarNombrePuesto(nombrePuestoNuevo);
			int puestoDatos;
			int puestoNuevoDatos;
			
			if (puesto.isPresent() && puestoNuevo.isPresent()) {
				puestoDatos = puesto.get().getIdPuesto();
				puestoNuevoDatos = puestoNuevo.get().getIdPuesto();
			}else {
				puestoDatos = puesto.map(PuestoModel::getIdPuesto).orElse(0); // Asigna 0 si el expediente está vacío
			    puestoNuevoDatos = puestoNuevo.map(PuestoModel::getIdPuesto).orElse(1); // Asigna 1 si el expedienteNuevo está vacío
			}
			if (puesto.isPresent() && !puestoNuevo.isPresent() || 
					puestoDatos == puestoNuevoDatos) {
		
				PuestoModel puestoActualizado = puesto.get();
				puestoActualizado.setNombrePuesto(nombrePuestoNuevo);
				puestoActualizado.setActivo(activo);
				PuestoModel guardarTipo = puestoService.actualizarPuesto(puestoActualizado);
				return ResponseEntity.ok(guardarTipo);
				
			} else if (!puesto.isPresent()){
				String mensaje = "No existe un tipo de expediente con el nombre: " + nombrePuesto;
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
				
				}else{
					String mensaje = "Ya existe un tipo de expediente con el nombre: " + nombrePuestoNuevo;
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
				}
		}
		
		@PutMapping("/api/activar/{nombrePuesto}")
		public ResponseEntity<?> activarTiposExpediente(String nombrePuesto) {
			Optional<PuestoModel> puesto = puestoService.consultarNombrePuesto(nombrePuesto);
			
			if (puesto.isPresent()) {
				
				PuestoModel puestoBorrarLogico = puesto.get();
				puestoBorrarLogico.setActivo(false);
				PuestoModel guardarTipo = puestoService.actualizarPuesto(puestoBorrarLogico);
				return ResponseEntity.ok(guardarTipo);
				
			}else{
				String mensaje = "No existe un tipo de expediente con el nombre: " + nombrePuesto;
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
			}
		}
		
}
