package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.CandidatosModel;
import com.project.Soltel.repositories.ICandidatosRepository;

@Service
public class CandidatosService {

    @Autowired
    private ICandidatosRepository candidatosRepository;

    public List<CandidatosModel> consultarTodosCandidatos() {
        return candidatosRepository.findAll();
    }

    public CandidatosModel guardarCandidatos(CandidatosModel candidatos){
        return candidatosRepository.save(candidatos);
    }

    public CandidatosModel actualizarCandidatos(CandidatosModel candidatos){
        return candidatosRepository.save(candidatos);
    }

    public Optional<CandidatosModel> consultarCandidatosNombre(String nombre){
        return candidatosRepository.findByNombre(nombre);
    }
    
}
