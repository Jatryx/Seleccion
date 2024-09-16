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
    
    	// Getters y Setters
    
	public String getNombreCandidato() {
		return nombreCandidato;
	}

	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
	
	public CandidatosModel() {
	}

	public CandidatosModel(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
}
