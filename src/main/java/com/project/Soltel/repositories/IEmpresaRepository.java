package com.project.Soltel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.EmpresaModel;

@Repository
public interface IEmpresaRepository extends JpaRepository<EmpresaModel, Integer> {

    @Query("SELECT a from EmpresaModel a WHERE a.activo = false")
    List<EmpresaModel> findEmpresasActivas();

    @Query("SELECT a from EmpresaModel a WHERE a.activo = true")
    List<EmpresaModel> findEmpresasInactivas();

    @Query("SELECT a FROM EmpresaModel a WHERE a.nombreEmpresa = :nombre")
    EmpresaModel findEmpresaByNombre(String nombre);
    
}
