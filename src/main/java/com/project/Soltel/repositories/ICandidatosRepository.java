package com.project.Soltel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.CandidatosModel;

@Repository
public interface ICandidatosRepository extends JpaRepository<CandidatosModel, Integer> {

    @Query("SELECT a from CandidatosModel a WHERE a.activo = false")
    List<CandidatosModel> findCandidatosActivos();
    
    @Query("SELECT a from CandidatosModel a WHERE a.activo = true")
    List<CandidatosModel> findCandidatosInactivos();

    @Query("SELECT a from CandidatosModel a WHERE a.nombreCandidato = :nombre")
    CandidatosModel findCandidatoByNombre(String nombre);

    @Query("SELECT a from CandidatosModel a WHERE a.nombreCandidato = :nombre")
    Optional<CandidatosModel> findByNombre(String nombre);

}
