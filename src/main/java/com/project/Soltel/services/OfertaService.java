package com.project.Soltel.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Soltel.models.OfertasModel;
import com.project.Soltel.repositories.IOfertaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {

    @Autowired
    private IOfertaRepository ofertaRepository;

    public List<OfertasModel> consultarOfertasActivas() {
        return ofertaRepository.findByActivoTrue();
    }

    public List<OfertasModel> consultarOfertasInactivas() {
        return ofertaRepository.findByActivoFalse();
    }

    public List<OfertasModel> consultarTodasOfertas() {
        return ofertaRepository.findAll();
    }

    public Optional<OfertasModel> consultarOfertasId(int id) {
        return ofertaRepository.findById(id);
    }

    public OfertasModel guardarOferta(OfertasModel oferta){
        return ofertaRepository.save(oferta);
    }

    public OfertasModel actualizarOferta(OfertasModel oferta){
        return ofertaRepository.save(oferta);
    }

    public Optional<OfertasModel> consultarPorNombreCandidatoAndIdPeticion(String nombreCandidato, Integer idPeticion) {
        return ofertaRepository.findByNombreCandidatoAndRecruitingId(nombreCandidato, idPeticion);
    }

}
