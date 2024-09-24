package com.project.Soltel.controllers;

import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.models.EstadoModel;
import com.project.Soltel.models.OfertasModel;
import com.project.Soltel.models.PuestoModel;
import com.project.Soltel.models.RecruitingModel;
import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.models.UsuarioModel;
import com.project.Soltel.services.CandidatosService;
import com.project.Soltel.services.EmpresaService;
import com.project.Soltel.services.EstadoService;
import com.project.Soltel.services.OfertaService;
import com.project.Soltel.services.PuestoService;
import com.project.Soltel.services.RecruitingService;
import com.project.Soltel.services.UbicacionService;
import com.project.Soltel.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasController {

    @Autowired
    private  OfertaService ofertaService;

    @Autowired
    private  CandidatosService candidatosService;

    @Autowired
    private  EmpresaService empresaService;

    @Autowired
    private  EstadoService estadoService;

    @Autowired
    private  PuestoService puestoService;

    @Autowired
    private  RecruitingService recruitingService;

    @Autowired
    private  UbicacionService ubicacionService;

    @Autowired
    private  UsuarioService usuarioService;

    // Controladores de las entidades relacionadas
    CandidatosController candidatoController = new CandidatosController();
    EmpresaController empresaController = new EmpresaController();
    EstadoController estadoController = new EstadoController();
    PuestoController puestoController = new PuestoController(puestoService);
    RecruitingController recruitingController = new RecruitingController();
    UbicacionController ubicacionController = new UbicacionController();
    UsuarioController usuarioController = new UsuarioController();
    

    @GetMapping("/todasOfertas")
    public List<OfertasModel> getAllOfertas() {
        return ofertaService.consultarTodasOfertas();
    }

    @GetMapping("/consultarOferta/{nombreCandidato}/{idRecruiting}")
    public ResponseEntity<OfertasModel> getOfertaById(@PathVariable("nombreCandidato") String nombreCandidato, @PathVariable("idRecruiting") int idRecruiting) {
        Optional<OfertasModel> oferta = ofertaService.consultarPorNombreCandidatoAndIdPeticion(nombreCandidato, idRecruiting);
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

        Optional<OfertasModel> ofertaAcoparar = ofertaService.consultarPorNombreCandidatoAndIdPeticion(nombreCandidato, idPeticion);

        if (ofertaAcoparar.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una oferta con el nombre del candidato: " + nombreCandidato + " y el id de la petición: " + idPeticion);
        }

        Optional<CandidatosModel> candidatoBuscado = candidatosService.consultarCandidatosNombre(nombreCandidato);
        Optional<EstadoModel> estadoBuscado = estadoService.consultarNombreEstado(oferta.getEstado().getEstado());
        Optional<UsuarioModel> usuarioBuscado = usuarioService.consultarUsuarioCodope(oferta.getUsuario().getCodope());
        Optional<UbicacionModel> ubicacionBuscada = ubicacionService.consultarNombreUbicacion(oferta.getUbicacion().getNombreProvincia());
        Optional<PuestoModel> puestoBuscado = puestoService.consultarNombrePuesto(oferta.getPuesto().getNombrePuesto());
        Optional<RecruitingModel> recruitingBuscado = recruitingService.consultarRecruitingPorId(idPeticion);

        OfertasModel nuevaOferta = new OfertasModel();

        if (candidatoBuscado.isPresent()) {
            // El candidato existe
            CandidatosModel candidato = candidatoBuscado.get();
            nuevaOferta.setCandidato(candidato);
            /*nuevaOferta.setNombreCandidato(candidato.getNombreCandidato());*/
        } else {
            
            // El candidato no existe
            CandidatosModel candidato = candidatosService.guardarCandidatos(oferta.getCandidato());
            nuevaOferta.setCandidato(candidato);
        }

        if (estadoBuscado.isPresent()) {
            // El estado existe
            EstadoModel estado = estadoBuscado.get();
            nuevaOferta.setEstado(estado);
        } else {
            // El estado no existe
            EstadoModel estado = estadoService.guardarEstado(oferta.getEstado());
            nuevaOferta.setEstado(estado);
        }

        if (usuarioBuscado.isPresent()) {
            // El usuario existe
            UsuarioModel usuario = usuarioBuscado.get();
            nuevaOferta.setUsuario(usuario);
        } else {
            // El usuario no existe
            UsuarioModel usuario = usuarioService.guardarUsuario(oferta.getUsuario());
            nuevaOferta.setUsuario(usuario);
        }

        if (ubicacionBuscada.isPresent()) {
            // La ubicación existe
            UbicacionModel ubicacion = ubicacionBuscada.get();
            nuevaOferta.setUbicacion(ubicacion);
        } else {
            // La ubicación no existe
            UbicacionModel ubicacion = ubicacionService.guardarUbicacion(oferta.getUbicacion());
            nuevaOferta.setUbicacion(ubicacion);

        }

        if (puestoBuscado.isPresent()) {
            // El puesto existe
            PuestoModel puesto = puestoBuscado.get();
            nuevaOferta.setPuesto(puesto);
        } else {
            // El puesto no existe
            PuestoModel puesto = puestoService.guardarPuesto(oferta.getPuesto());
            nuevaOferta.setPuesto(puesto);
        }
        
        if (recruitingBuscado.isPresent()) {
        	// Proceso existe
        	RecruitingModel recruiting = recruitingBuscado.get();
        	nuevaOferta.setRecruiting(recruiting);
        } else {
        	// Proceso no existe
        	EmpresaModel empresa = empresaService.guardarEmpresa(oferta.getRecruiting().getEmpresa());
        	oferta.getRecruiting().setEmpresa(empresa); 
        	RecruitingModel recruiting = recruitingService.guardarRecruiting(oferta.getRecruiting());
        	nuevaOferta.setRecruiting(recruiting);
        }
        

        nuevaOferta.setExperiencia(oferta.getExperiencia());
        nuevaOferta.setFechaActualizacion(oferta.getFechaActualizacion());
        nuevaOferta.setObservaciones(oferta.getObservaciones());
        nuevaOferta.setTecnologias(oferta.getTecnologias());
        nuevaOferta.setSalario(oferta.getSalario());
        nuevaOferta.setActivo(true);

        String informacion = "Se ha creado la oferta el dia " + oferta.getFechaActualizacion() + " con el estado " + oferta.getEstado().getEstado();
        nuevaOferta.setHistoricoCambioEstados(informacion);

        OfertasModel savedOferta = ofertaService.guardarOferta(nuevaOferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOferta);
    }

    @PutMapping("/actualizarOferta/{nombreCandidato}/{idRecruiting}")
    public ResponseEntity<?> updateOferta(@PathVariable("nombreCandidato") String nombreCandadito,
    		@PathVariable("idRecruiting") int idRecruiting, @RequestBody OfertasModel ofertaDetails) {
    	
    	Optional<OfertasModel> oferta = ofertaService.consultarPorNombreCandidatoAndIdPeticion(nombreCandadito, idRecruiting);
		Optional<OfertasModel> ofertaNueva = ofertaService.consultarPorNombreCandidatoAndIdPeticion(ofertaDetails.getCandidato().getNombreCandidato(), ofertaDetails.getRecruiting().getIdRecruiting());
		int ofertaDatos;
		int ofertaNuevaDatos;
		
		if (oferta.isPresent() && ofertaNueva.isPresent()) {
			ofertaDatos = oferta.get().getIdOferta();
			ofertaNuevaDatos = ofertaNueva.get().getIdOferta();
		}else {
			ofertaDatos = oferta.map(OfertasModel::getIdOferta).orElse(0); // Asigna 0 si el expediente está vacío
		    ofertaNuevaDatos = ofertaNueva.map(OfertasModel::getIdOferta).orElse(1); // Asigna 1 si el expedienteNuevo está vacío
		}
		if (oferta.isPresent() && !ofertaNueva.isPresent() || 
				ofertaDatos == ofertaNuevaDatos) {
	
			
	        
			
	        Optional<CandidatosModel> candidatoBuscado = candidatosService.consultarCandidatosNombre(ofertaDetails.getCandidato().getNombreCandidato());
	        Optional<EstadoModel> estadoBuscado = estadoService.consultarNombreEstado(ofertaDetails.getEstado().getEstado());
	        Optional<UsuarioModel> usuarioBuscado = usuarioService.consultarUsuarioCodope(ofertaDetails.getUsuario().getCodope());
	        Optional<UbicacionModel> ubicacionBuscada = ubicacionService.consultarNombreUbicacion(ofertaDetails.getUbicacion().getNombreProvincia());
	        Optional<PuestoModel> puestoBuscado = puestoService.consultarNombrePuesto(ofertaDetails.getPuesto().getNombrePuesto());
	        Optional<RecruitingModel> recruitingBuscado = recruitingService.consultarRecruitingPorId(ofertaDetails.getRecruiting().getIdRecruiting());

	        OfertasModel ofertaActualizada = oferta.get();

	        if (candidatoBuscado.isPresent()) {
	            // El candidato existe
	            CandidatosModel candidato = candidatoBuscado.get();
	            ofertaActualizada.setCandidato(candidato);
	            /*nuevaOferta.setNombreCandidato(candidato.getNombreCandidato());*/
	        } else {
	            
	            // El candidato no existe
	            CandidatosModel candidato = candidatosService.guardarCandidatos(ofertaDetails.getCandidato());
	            ofertaActualizada.setCandidato(candidato);
	        }

	        if (usuarioBuscado.isPresent()) {
	            // El usuario existe
	            UsuarioModel usuario = usuarioBuscado.get();
	            ofertaActualizada.setUsuario(usuario);
	        } else {
	            // El usuario no existe
	            UsuarioModel usuario = usuarioService.guardarUsuario(ofertaDetails.getUsuario());
	            ofertaActualizada.setUsuario(usuario);
	        }

	        if (ubicacionBuscada.isPresent()) {
	            // La ubicación existe
	            UbicacionModel ubicacion = ubicacionBuscada.get();
	            ofertaActualizada.setUbicacion(ubicacion);
	        } else {
	            // La ubicación no existe
	            UbicacionModel ubicacion = ubicacionService.guardarUbicacion(ofertaDetails.getUbicacion());
	            ofertaActualizada.setUbicacion(ubicacion);

	        }

	        if (puestoBuscado.isPresent()) {
	            // El puesto existe
	            PuestoModel puesto = puestoBuscado.get();
	            ofertaActualizada.setPuesto(puesto);
	        } else {
	            // El puesto no existe
	            PuestoModel puesto = puestoService.guardarPuesto(ofertaDetails.getPuesto());
	            ofertaActualizada.setPuesto(puesto);
	        }
	        
	        if (recruitingBuscado.isPresent()) {
	        	// Proceso existe
	        	RecruitingModel recruiting = recruitingBuscado.get();
	        	ofertaActualizada.setRecruiting(recruiting);
	        } else {
	        	// Proceso no existe
	        	EmpresaModel empresa = empresaService.guardarEmpresa(ofertaDetails.getRecruiting().getEmpresa());
	        	ofertaDetails.getRecruiting().setEmpresa(empresa); 
	        	RecruitingModel recruiting = recruitingService.guardarRecruiting(ofertaDetails.getRecruiting());
	        	ofertaActualizada.setRecruiting(recruiting);
	        }
	        

	        ofertaActualizada.setExperiencia(ofertaDetails.getExperiencia());
	        ofertaActualizada.setFechaActualizacion(ofertaDetails.getFechaActualizacion());
	        ofertaActualizada.setObservaciones(ofertaDetails.getObservaciones());
	        ofertaActualizada.setTecnologias(ofertaDetails.getTecnologias());
	        ofertaActualizada.setSalario(ofertaDetails.getSalario());
	        ofertaActualizada.setActivo(true);
	        
	        
	        String estadoAntiguo = oferta.get().getEstado().getEstado();
	        String estadoAComprobar = ofertaDetails.getEstado().getEstado();
	        
	        if (estadoBuscado.isPresent()) {
	            // El estado existe
	            EstadoModel estadoNuevo = estadoBuscado.get();
	            ofertaActualizada.setEstado(estadoNuevo);
	        } else {
	            // El estado no existe
	            EstadoModel estadoNuevo = estadoService.guardarEstado(ofertaDetails.getEstado());
	            ofertaActualizada.setEstado(estadoNuevo);
	        }
	        
	        if (!estadoAntiguo.equals(estadoAComprobar)) {
	     // Formatear la fecha
	        String estado = ofertaDetails.getEstado().getEstado();
	        LocalDate fechaActualizacion = ofertaDetails.getFechaActualizacion(); // Es LocalDate
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String fechaFormateada = fechaActualizacion.format(formatter);
	        
	        // Actualizar el historial
	        String historial = oferta.get().getHistoricoCambioEstados();
	        historial += System.lineSeparator() + " Fue " + estado + " el " + fechaFormateada;
	        ofertaActualizada.setHistoricoCambioEstados(historial);
	        }
	        OfertasModel savedOferta = ofertaService.guardarOferta(ofertaActualizada);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedOferta);
	        
		} else if (!oferta.isPresent()){
			String mensaje = "No existe una oferta del candidato: "  + nombreCandadito + " para el proceso: " + idRecruiting;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
			
			}else{
				String mensaje = "Ya existe una oferta del candidato: "  + ofertaDetails.getCandidato().getNombreCandidato() + " para el proceso: " + ofertaDetails.getRecruiting().getIdRecruiting();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
			}
	}

}
