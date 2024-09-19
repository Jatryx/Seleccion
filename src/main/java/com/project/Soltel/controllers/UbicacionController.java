package com.project.Soltel.controllers;

import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.repositories.IUbicacionRepository;
import com.project.Soltel.services.UbicacionService;

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
    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public List<UbicacionModel> getAllUbicaciones() {
        return ubicacionService.consultarTodasUbicaciones();
    }

    @GetMapping("/consultar")
    public ResponseEntity<UbicacionModel> getUbicacionByNombre(String nombre) {
        Optional<UbicacionModel> ubicacion = ubicacionService.consultarNombreUbicacion(nombre);
        return ubicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar")
    public ResponseEntity<UbicacionModel> createUbicacion(@RequestBody UbicacionModel ubicacion) {
        UbicacionModel savedUbicacion = ubicacionRepository.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUbicacion);
    }

    @PutMapping("/actualizar/{provincia}")
    public ResponseEntity<?> updateUbicacion(@PathVariable("nombre") String nombre, @RequestBody UbicacionModel ubicacionDetails) {
    	Optional<UbicacionModel> ubicacion = ubicacionService.consultarNombreUbicacion(nombre);
    	Optional<UbicacionModel> ubicacionNueva = ubicacionService.consultarNombreUbicacion(ubicacionDetails.getNombreProvincia());
        if (ubicacionNueva.isPresent()) {
        	String mensaje = "Ya existe una provincia con el nombre: " + ubicacionDetails.getNombreProvincia();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            
        } else if(ubicacion.isPresent()){
        	UbicacionModel existingUbicacion = ubicacion.get();
            existingUbicacion.setNombreProvincia(ubicacionDetails.getNombreProvincia());
            existingUbicacion.setActivo(ubicacionDetails.getActivo());
            UbicacionModel updatedUbicacion = ubicacionRepository.save(existingUbicacion);
            return ResponseEntity.ok(updatedUbicacion);
        } else {
        	String mensaje = "No existe una provincia con el nombre: " + nombre;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
    
    @PutMapping("/desactivar/{provincia}")
	public ResponseEntity<?> activarTiposExpediente(String ubicacion) {
		Optional<UbicacionModel> provincia = ubicacionService.consultarNombreUbicacion(ubicacion);
		
		if (provincia.isPresent()) {
			
			UbicacionModel provinciaBorrarLogico = provincia.get();
			provinciaBorrarLogico.setActivo(false);
			UbicacionModel guardarProvincia = ubicacionService.actualizarUbicacion(provinciaBorrarLogico);
			return ResponseEntity.ok(guardarProvincia);
			
		}else{
			String mensaje = "No existe una provincia con el nombre: " + ubicacion;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}
	}

    @DeleteMapping("/{provincia}")
    public ResponseEntity<Void> deleteUbicacion(String nombre) {
    	Optional<UbicacionModel> ubicacion = ubicacionService.consultarNombreUbicacion(nombre);
        if (ubicacion.isPresent()) {
        	UbicacionModel existingUbicacion = ubicacion.get();
            ubicacionRepository.deleteById(existingUbicacion.getIdUbicacion());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
