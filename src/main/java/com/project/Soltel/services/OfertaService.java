package com.project.Soltel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.OfertasModel;
import com.project.Soltel.repositories.IOfertaRepository;

@Service
public class OfertaService {

    @Autowired
    private IOfertaRepository ofertaRepository;

    public List<OfertasModel> consultarOfertasActivas() {
        return ofertaRepository.findOfertaActiva();
    }

    public List<OfertasModel> consultarOfertasInactivas() {
        return ofertaRepository.findOfertaInactiva();
    }

    public List<OfertasModel> consultarTodasOfertas() {
        return ofertaRepository.findAll();
    }

    public OfertasModel guardarOferta(OfertasModel oferta){
        return ofertaRepository.save(oferta);
    }

    public OfertasModel actualizarOferta(OfertasModel oferta){
        return ofertaRepository.save(oferta);
    }

    

}
