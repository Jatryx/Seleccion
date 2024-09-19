package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.EstadoModel;
import com.project.Soltel.repositories.IEstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private IEstadoRepository estadoRepository;
    
    public List<EstadoModel> consultarTodosEstados() {
        return estadoRepository.findAll();
    }

    public EstadoModel guardarEstado(EstadoModel estado){
        return estadoRepository.save(estado);
    }

    public EstadoModel actualizarEstado(EstadoModel estado){
        return estadoRepository.save(estado);
    }

    public Optional<EstadoModel> consultarNombreEstado(String nombreEstado){
        return estadoRepository.findEstadoByNombre(nombreEstado);
    }

}
