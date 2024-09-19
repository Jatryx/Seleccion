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

        if (ofertaAcoparar != null && nombreCandidato.equals(ofertaAcoparar.getNombreCandidato()) && idPeticion.equals(ofertaAcoparar.getRecruiting().getIdRecruiting())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una oferta con el nombre del candidato: " + nombreCandidato + " y el id de la petición: " + idPeticion);
        }

        Optional<CandidatosModel> candidatoBuscado = candidatosService.consultarCandidatosNombre(nombreCandidato);
        Optional<EmpresaModel> empresaBuscada = empresaService.consultarNombreEmpresa(oferta.getEmpresa().getNombreEmpresa());
        Optional<EstadoModel> estadoBuscado = estadoService.consultarNombreEstado(oferta.getEstado().getEstado());
        Optional<UsuarioModel> usuarioBuscado = usuarioService.consultarUsuarioCodope(oferta.getUsuario().getCodope());
        UbicacionModel ubicacionBuscada = ubicacionService.consultarNombreUbicacion(oferta.getUbicacion().getNombreProvincia());
        Optional<PuestoModel> puestoBuscado = puestoService.consultarNombrePuesto(oferta.getPuesto().getNombrePuesto());
        RecruitingModel recruitingBuscado = recruitingService.consultarRecruitingPorId(oferta.getRecruiting().getIdRecruiting());

        OfertasModel nuevaOferta = new OfertasModel();

        if (candidatoBuscado.isPresent()) {
            // El candidato existe
            CandidatosModel candidato = candidatoBuscado.get();
            nuevaOferta.setCandidato(candidato);
            nuevaOferta.setNombreCandidato(candidato.getNombreCandidato());
        } else {
            
            // El candidato no existe
            ResponseEntity<?> response = candidatoController.agregarCandidato(oferta.getCandidato());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                nuevaOferta.setCandidato((CandidatosModel) response.getBody());
            }
        }

        if (empresaBuscada.isPresent()) {
            // La empresa existe
            EmpresaModel empresa = empresaBuscada.get();
            nuevaOferta.setEmpresa(empresa);
        } else {
            // La empresa no existe
            ResponseEntity<?> response = empresaController.agregarEmpresa(oferta.getEmpresa().getNombreEmpresa());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                nuevaOferta.setEmpresa((EmpresaModel) response.getBody());
            }
        }

        if (estadoBuscado.isPresent()) {
            // El estado existe
            EstadoModel estado = estadoBuscado.get();
            nuevaOferta.setEstado(estado);
        } else {
            // El estado no existe
            ResponseEntity<?> response = estadoController.agregarEstado(oferta.getEstado());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                nuevaOferta.setEstado((EstadoModel) response.getBody());
            }
        }

        if (usuarioBuscado.isPresent()) {
            // El usuario existe
            UsuarioModel usuario = usuarioBuscado.get();
            nuevaOferta.setUsuario(usuario);
        } else {
            // El usuario no existe
            ResponseEntity<?> response = usuarioController.createUsuario(oferta.getUsuario());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                nuevaOferta.setUsuario((UsuarioModel) response.getBody());
            }
        }

        if (ubicacionBuscada != null) {
            // La ubicación existe
            nuevaOferta.setUbicacion(ubicacionBuscada);
        } else {
            // La ubicación no existe
            ResponseEntity<?> response = ubicacionController.createUbicacion(oferta.getUbicacion());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                nuevaOferta.setUbicacion((UbicacionModel) response.getBody());
            }
        }

        if (puestoBuscado.isPresent()) {
            // El puesto existe
            PuestoModel puesto = puestoBuscado.get();
            nuevaOferta.setPuesto(puesto);
        } else {
            // El puesto no existe
            ResponseEntity<?> response = puestoController.guardarPuesto(oferta.getPuesto().getNombrePuesto());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                nuevaOferta.setPuesto((PuestoModel) response.getBody());
            }
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

    @PutMapping("/actualizarOferta/{id}")
    public ResponseEntity<?> updateOferta(@PathVariable("id") int id, @RequestBody OfertasModel ofertaDetails) {
        Optional<OfertasModel> oferta = ofertaService.consultarOfertasId(id);
        if (oferta.isPresent()) {
            OfertasModel ofertaActualizada = oferta.get();

            // Verificar y actualizar el candidato
            if (ofertaDetails.getCandidato() != null) {
                Optional<CandidatosModel> candidatoBuscado = candidatosService.consultarCandidatosNombre(ofertaDetails.getCandidato().getNombreCandidato());
                if (candidatoBuscado.isPresent()) {
                    ofertaActualizada.setCandidato(candidatoBuscado.get());
                } else {
                    CandidatosModel nuevoCandidato = candidatosService.guardarCandidatos(ofertaDetails.getCandidato());
                    ofertaActualizada.setCandidato(nuevoCandidato);
                }
            }

            // Verificar y actualizar la empresa
            if (ofertaDetails.getEmpresa() != null) {
                Optional<EmpresaModel> empresaBuscada = empresaService.consultarNombreEmpresa(ofertaDetails.getEmpresa().getNombreEmpresa());
                if (empresaBuscada.isPresent()) {
                    ofertaActualizada.setEmpresa(empresaBuscada.get());
                } else {
                    EmpresaModel nuevaEmpresa = empresaService.guardarEmpresa(ofertaDetails.getEmpresa());
                    ofertaActualizada.setEmpresa(nuevaEmpresa);
                }
            }

            // Verificar y actualizar el estado
            if (ofertaDetails.getEstado() != null) {
                Optional<EstadoModel> estadoBuscado = estadoService.consultarNombreEstado(ofertaDetails.getEstado().getEstado());
                if (estadoBuscado.isPresent()) {
                    ofertaActualizada.setEstado(estadoBuscado.get());
                } else {
                    EstadoModel nuevoEstado = estadoService.guardarEstado(ofertaDetails.getEstado());
                    ofertaActualizada.setEstado(nuevoEstado);
                }
            }

            // Verificar y actualizar el usuario
            if (ofertaDetails.getUsuario() != null) {
                Optional<UsuarioModel> usuarioBuscado = usuarioService.consultarUsuarioCodope(ofertaDetails.getUsuario().getCodope());
                if (usuarioBuscado.isPresent()) {
                    ofertaActualizada.setUsuario(usuarioBuscado.get());
                } else {
                    UsuarioModel nuevoUsuario = usuarioService.guardarUsuario(ofertaDetails.getUsuario());
                    ofertaActualizada.setUsuario(nuevoUsuario);
                }
            }

            // Verificar y actualizar el recruiting
            if (ofertaDetails.getRecruiting() != null) {
                RecruitingModel recruitingBuscado = recruitingService.consultarRecruitingPorId(ofertaDetails.getRecruiting().getIdRecruiting());
                if (recruitingBuscado != null) {
                    ofertaActualizada.setRecruiting(recruitingBuscado);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la petición con el id: " + ofertaDetails.getRecruiting().getIdRecruiting());
                }
            }

            // Verificar y actualizar la ubicación
            if (ofertaDetails.getUbicacion() != null) {
                UbicacionModel ubicacionBuscada = ubicacionService.consultarNombreUbicacion(ofertaDetails.getUbicacion().getNombreProvincia());
                if (ubicacionBuscada != null) {
                    ofertaActualizada.setUbicacion(ubicacionBuscada);
                } else {
                    UbicacionModel nuevaUbicacion = ubicacionService.guardarUbicacion(ofertaDetails.getUbicacion());
                    ofertaActualizada.setUbicacion(nuevaUbicacion);
                }
            }

            // Verificar y actualizar el puesto
            if (ofertaDetails.getPuesto() != null) {
                Optional<PuestoModel> puestoBuscado = puestoService.consultarNombrePuesto(ofertaDetails.getPuesto().getNombrePuesto());
                if (puestoBuscado.isPresent()) {
                    ofertaActualizada.setPuesto(puestoBuscado.get());
                } else {
                    PuestoModel nuevoPuesto = puestoService.guardarPuesto(ofertaDetails.getPuesto());
                    ofertaActualizada.setPuesto(nuevoPuesto);
                }
            }

            // Actualizar otros campos
            ofertaActualizada.setExperiencia(ofertaDetails.getExperiencia());
            ofertaActualizada.setFechaActualizacion(ofertaDetails.getFechaActualizacion());
            ofertaActualizada.setObservaciones(ofertaDetails.getObservaciones());
            ofertaActualizada.setNombreCandidato(ofertaDetails.getNombreCandidato());
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
