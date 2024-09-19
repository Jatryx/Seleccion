package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.repositories.IUbicacionRepository;

@Service
public class UbicacionService {

    @Autowired
    private IUbicacionRepository ubicacionRepository;

    public List<UbicacionModel> consultarTodasUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public UbicacionModel guardarUbicacion(UbicacionModel ubicacion){
        return ubicacionRepository.save(ubicacion);
    }

    public UbicacionModel actualizarUbicacion(UbicacionModel ubicacion){
        return ubicacionRepository.save(ubicacion);
    }

    public Optional<UbicacionModel> consultarNombreUbicacion(String nombre){
        return ubicacionRepository.findUbicacionByNombre(nombre);
    }

}
