package com.project.Soltel.controllers;

import com.project.Soltel.models.RecruitingModel;
import com.project.Soltel.services.RecruitingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruitings")
public class RecruitingController {

    @Autowired
    private RecruitingService recruitingservice;

    @GetMapping("/todosRecruitings")
    public List<RecruitingModel> getAllRecruitings() {
        return recruitingservice.consultarTodosRecruiting();
    }

    @GetMapping("/idRecruiting/{id}")
    public RecruitingModel getRecruitingById(@PathVariable("id") int id) {
    
        return recruitingservice.consultarRecruitingPorId(id);
    }

    @PostMapping("/añadirRecruiting")
    public ResponseEntity<?> createRecruiting(@RequestBody RecruitingModel recruiting) {
       RecruitingModel nuevoRecruiting = new RecruitingModel();
       nuevoRecruiting.setNombreProyecto(recruiting.getNombreProyecto());
       RecruitingModel guardarRecruiting = recruitingservice.guardarRecruiting(nuevoRecruiting);
       return ResponseEntity.ok(guardarRecruiting);

    }

    @PutMapping("/actualizarRecruiting/{id}")
    public ResponseEntity<?> updateRecruiting(@PathVariable("id") int id, @RequestBody RecruitingModel recruitingDetails) {
        RecruitingModel recruiting = recruitingservice.consultarRecruitingPorId(id);
        if (recruiting != null) {
            recruiting.setNombreProyecto(recruitingDetails.getNombreProyecto());
            RecruitingModel updatedRecruiting = recruitingservice.actualizarRecruiting(recruiting);
            return ResponseEntity.ok(updatedRecruiting);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/eliminarRecruiting/{id}")
    public String eliminarRecruiting(@PathVariable int id) {
        RecruitingModel recruiting = recruitingservice.consultarRecruitingPorId(id);
        if (recruiting != null) {
            recruitingservice.actualizarRecruiting(recruiting);
            return "Recruiting eliminado";
        } else {
            return "Recruiting no encontrado";
        }
    }
}
