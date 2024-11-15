package com.project.Soltel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.ProveedorModel;

@Repository
public interface IProveedoresRepository extends JpaRepository<ProveedorModel, String> {

    @Query("SELECT p FROM ProveedorModel p WHERE p.activo = true")
    List<ProveedorModel> findProveedoresActivos();

    @Query("SELECT p FROM ProveedorModel p WHERE p.activo = false")
    List<ProveedorModel> findProveedoresInactivos();

    @Query("SELECT p FROM ProveedorModel p WHERE p.nombreProveedor = :nombre")
    Optional<ProveedorModel> findProveedorByNombre(String nombre);
}
