package com.project.Soltel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.UbicacionModel;

@Repository
public interface IUbicacionRepository extends JpaRepository<UbicacionModel, Integer> {
    
    @Query("SELECT a FROM UbicacionModel a WHERE a.nombreProvincia = :nombre")
    Optional<UbicacionModel> findUbicacionByNombre(String nombre);

}
