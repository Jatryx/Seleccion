package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class UsuarioModel {
    
    @Id
    private String codope;

    @Column(nullable = false, length = 50)
    private String contraseña;
    
    @Column
    private Boolean activo;

    // Getters y Setters
    public String getCodope() {
        return codope;
    }

    public void setCodope(String codope) {
        this.codope = codope;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // Constructor
    public UsuarioModel() {
    }

    public UsuarioModel(String codope, String contraseña, Boolean activo) {
        this.codope = codope;
        this.contraseña = contraseña;
        this.activo = activo;
    }

	
}