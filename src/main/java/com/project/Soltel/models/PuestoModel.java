package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PuestoModel")
public class PuestoModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdPuesto;

    @Column(nullable = false, length = 100)
    private String nombrePuesto;
    
    @Column
    private Boolean activo;
    
    
    
    // Getters y Setters

	public Integer getIdPuesto() {
		return IdPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		IdPuesto = idPuesto;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	
		// Constructor
	
	public PuestoModel() {
		super();
	}

	public PuestoModel(String nombrePuesto, Boolean activo) {
		super();
		this.nombrePuesto = nombrePuesto;
		this.activo = activo;
	}
    
}