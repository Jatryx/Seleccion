package com.project.Soltel.controllers;

import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.models.RecruitingModel;
import com.project.Soltel.services.EmpresaService;
import com.project.Soltel.services.RecruitingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recruitings")
public class RecruitingController {

    @Autowired
    private RecruitingService recruitingService;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/todosRecruitings")
    public List<RecruitingModel> getAllRecruitings() {
        return recruitingService.consultarTodosRecruiting();
    }

    @GetMapping("/idRecruiting/{id}")
    public Optional<RecruitingModel> getRecruitingById(@PathVariable("id") int id) {
        return recruitingService.consultarRecruitingPorId(id);
    }

    @PostMapping("/añadirRecruiting")
    public ResponseEntity<?> createRecruiting(@RequestBody RecruitingModel recruiting) {
    	Optional<RecruitingModel> recruitingExistente = recruitingService.consultarRecruitingPorId(recruiting.getIdRecruiting());
		if (recruitingExistente.isPresent()) {
			String mensaje = "Ya existe el registro: " + recruiting.getIdRecruiting();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}else {
		Optional<EmpresaModel> empresaExistente = empresaService.consultarNombreEmpresa(recruiting.getEmpresa().getNombreEmpresa());
		if (empresaExistente.isPresent()) {
			recruiting.setEmpresa(empresaExistente.get());
			RecruitingModel guardarRecruiting = recruitingService.guardarRecruiting(recruiting);
			return ResponseEntity.ok(guardarRecruiting);
		}else {
			EmpresaModel empresa = empresaService.guardarEmpresa(recruiting.getEmpresa());
			recruiting.setEmpresa(empresa);
			RecruitingModel guardarRecruiting = recruitingService.guardarRecruiting(recruiting);
			return ResponseEntity.ok(guardarRecruiting);
			}
		}
    }

    @PutMapping("/actualizarRecruiting/{id}")
    public ResponseEntity<?> updateRecruiting(@PathVariable("id") int id, @RequestBody RecruitingModel recruitingDetails) {
        Optional<RecruitingModel> recruiting = recruitingService.consultarRecruitingPorId(id);
        if (!recruiting.isPresent()) {
            String mensaje = "No esxiste el ID de proceso: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        } else {
        	Optional<EmpresaModel> empresaExistente = empresaService.consultarNombreEmpresa(recruitingDetails.getEmpresa().getNombreEmpresa());
    		if (empresaExistente.isPresent()) {
    			recruiting.get().setEmpresa(empresaExistente.get());
    			RecruitingModel guardarRecruiting = recruitingService.guardarRecruiting(recruiting.get());
    			return ResponseEntity.ok(guardarRecruiting);
    		} else {
    			EmpresaModel empresa = empresaService.guardarEmpresa(recruitingDetails.getEmpresa());
    			recruitingDetails.setEmpresa(empresa);
    			RecruitingModel guardarRecruiting = recruitingService.guardarRecruiting(recruiting.get());
    			return ResponseEntity.ok(guardarRecruiting);
    			}
        }
    }

    @PutMapping("/eliminarRecruiting/{id}")
    public String eliminarRecruiting(@PathVariable int id) {
    	Optional<RecruitingModel> recruiting = recruitingService.consultarRecruitingPorId(id);
        if (recruiting.isPresent()) {
            recruiting.get().setActivo(false);
            return "Recruiting eliminado";
        } else {
            return "Recruiting no encontrado";
        }
   } 
}
