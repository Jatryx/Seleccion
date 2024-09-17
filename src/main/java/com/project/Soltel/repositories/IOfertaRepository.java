package com.project.Soltel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.OfertasModel;

@Repository
public interface IOfertaRepository extends JpaRepository<OfertasModel, Integer> {

    @Query("SELECT a FROM OfertasModel a WHERE a.activo = true")
    List<OfertasModel> findOfertaActiva();

    @Query("SELECT a FROM OfertasModel a WHERE a.activo = false")
    List<OfertasModel> findOfertaInactiva();

}
