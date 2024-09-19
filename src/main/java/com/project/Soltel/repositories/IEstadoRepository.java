package com.project.Soltel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.EstadoModel;

@Repository
public interface IEstadoRepository extends JpaRepository<EstadoModel, Integer> {
    
    @Query("SELECT a FROM EstadoModel a WHERE a.activo = true")
    EstadoModel findEstadoActivo();

    @Query("SELECT a FROM EstadoModel a WHERE a.activo = false")
    EstadoModel findEstadoInactivo();

    @Query("SELECT a FROM EstadoModel a WHERE a.estado = :nombre")
    Optional<EstadoModel> findEstadoByNombre(String nombre);


}
