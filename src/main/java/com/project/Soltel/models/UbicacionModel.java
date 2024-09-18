package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UbicacionModel")
public class UbicacionModel {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUbicacion;

    @Column(nullable = false, length = 100)
    private String nombreProvincia;

    @Column
    private Boolean activo;
    
    
    
    // Getters y Setters

	public Integer getIdUbicacion() {
		return IdUbicacion;
	}

	public void setIdUbicacion(Integer idUbicacion) {
		IdUbicacion = idUbicacion;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	
		// Constructor
	
	public UbicacionModel() {
	}

	public UbicacionModel(String nombreProvincia, Boolean activo) {
		this.nombreProvincia = nombreProvincia;
		this.activo = activo;
	}
}