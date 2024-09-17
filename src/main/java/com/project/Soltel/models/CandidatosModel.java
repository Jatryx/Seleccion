package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Candidatos")
public class CandidatosModel {
	
	@Id
	private Integer IdCandidato;

    @Column(nullable = false, length = 100)
    private String nombreCandidato;
    
    @Column
    private Boolean activo;

    
    	// Getters y Setters
    
	public Integer getIdCandidato() {
		return IdCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		IdCandidato = idCandidato;
	}

	public String getNombreCandidato() {
		return nombreCandidato;
	}

	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	
		// Constructors
	
	public CandidatosModel() {
		super();
	}

	public CandidatosModel(Integer idCandidato, String nombreCandidato, Boolean activo) {
		super();
		IdCandidato = idCandidato;
		this.nombreCandidato = nombreCandidato;
		this.activo = activo;
	}
    
   
}