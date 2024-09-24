package com.project.Soltel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.Soltel.models.OfertasModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOfertaRepository extends JpaRepository<OfertasModel, Integer> {
    
    List<OfertasModel> findByActivoTrue();
    
    List<OfertasModel> findByActivoFalse();
    
    @Query("SELECT o FROM OfertasModel o WHERE o.candidato.nombreCandidato = :nombreCandidato AND o.recruiting.IdRecruiting = :idRecruiting")
    Optional<OfertasModel> findByNombreCandidatoAndRecruitingId(String nombreCandidato, Integer idRecruiting);

}