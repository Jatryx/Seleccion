package com.project.Soltel.controllers;

import com.project.Soltel.models.RecruitingModel;
import com.project.Soltel.repositories.IRecruitingRepository;
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
    private IRecruitingRepository recruitingRepository;

    @GetMapping
    public List<RecruitingModel> getAllRecruitings() {
        return recruitingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruitingModel> getRecruitingById(@PathVariable("id") int id) {
        Optional<RecruitingModel> recruiting = recruitingRepository.findById(id);
        return recruiting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecruitingModel> createRecruiting(@RequestBody RecruitingModel recruiting) {
        RecruitingModel savedRecruiting = recruitingRepository.save(recruiting);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecruiting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruitingModel> updateRecruiting(@PathVariable("id") int id, @RequestBody RecruitingModel recruitingDetails) {
        Optional<RecruitingModel> recruiting = recruitingRepository.findById(id);
        if (recruiting.isPresent()) {
            RecruitingModel existingRecruiting = recruiting.get();
            existingRecruiting.setNombreProyecto(recruitingDetails.getNombreProyecto());
            existingRecruiting.setActivo(recruitingDetails.getActivo());
            existingRecruiting.setEmpresa(recruitingDetails.getEmpresa());
            RecruitingModel updatedRecruiting = recruitingRepository.save(existingRecruiting);
            return ResponseEntity.ok(updatedRecruiting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruiting(@PathVariable("id") int id) {
        if (recruitingRepository.existsById(id)) {
            recruitingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
