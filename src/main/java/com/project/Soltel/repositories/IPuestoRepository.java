package com.project.Soltel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.PuestoModel;

@Repository
public interface IPuestoRepository extends JpaRepository<PuestoModel, Integer> {
    
    @Query("SELECT a FROM PuestoModel a WHERE a.activo = true")
    PuestoModel findPuestoActivo();

    @Query("SELECT a FROM PuestoModel a WHERE a.activo = false")
    PuestoModel findPuestoInactivo();

    @Query("SELECT a FROM PuestoModel a WHERE a.nombrePuesto = :nombre")
    Optional<PuestoModel>findPuestoByNombre(String nombre);

}
