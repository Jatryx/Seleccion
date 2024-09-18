package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoModel")
public class EstadoModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdEstado;

    @Column(nullable = false, length = 50)
    private String estado;
    
    @Column
    private Boolean activo;
    
    
    
    	// Getters y Setters

	public Integer getIdEstado() {
		return IdEstado;
	}

	public void setIdEstado(Integer idEstado) {
		IdEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	
		// Constructor
	
	public EstadoModel() {
	}
	
	public EstadoModel( String estado, Boolean activo) {
		super();
		this.estado = estado;
		this.activo = activo;
	}    

}