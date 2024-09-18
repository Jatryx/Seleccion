package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.PuestoModel;
import com.project.Soltel.repositories.IPuestoRepository;

@Service
public class PuestoService {

    @Autowired
    private IPuestoRepository puestoRepository;

    public List<PuestoModel> consultarTodosPuestos() {
        return puestoRepository.findAll();
    }

    public PuestoModel guardarPuesto(PuestoModel puesto){
        return puestoRepository.save(puesto);
    }

    public PuestoModel actualizarPuesto(PuestoModel puesto){
        return puestoRepository.save(puesto);
    }

    public Optional<PuestoModel> consultarNombrePuesto(String nombre){
        return puestoRepository.findPuestoByNombre(nombre);
    }
    
}
