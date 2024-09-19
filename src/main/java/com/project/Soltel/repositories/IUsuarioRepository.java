package com.project.Soltel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.UsuarioModel;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    @Query("SELECT a FROM UsuarioModel a WHERE a.activo = true")
    List<UsuarioModel> findUsuarioActivo();

    @Query("SELECT a FROM UsuarioModel a WHERE a.activo = false")
    List<UsuarioModel> findUsuarioInactivo();

    @Query("SELECT a FROM UsuarioModel a WHERE a.codope = :codope")
    Optional<UsuarioModel> findUsuarioByCodope(String codope);
}
