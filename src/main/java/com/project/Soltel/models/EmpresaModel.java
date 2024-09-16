package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmpresaModel")
public class EmpresaModel {
	
	@Id
	private Integer IdEmpresa;

    @Column(nullable = false, length = 100)
    private String nombreEmpresa;
    
    	// Getters y Setters
    
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	//Constructor
	
	public EmpresaModel() {
	}
	
	public EmpresaModel(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
}
