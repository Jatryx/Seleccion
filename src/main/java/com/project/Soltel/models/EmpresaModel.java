package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmpresaModel")
public class EmpresaModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdEmpresa;

    @Column(nullable = false, length = 100)
    private String nombreEmpresa;
    
    @Column
    private Boolean activo;

    
    
    	// Getters y Setters
    
	public Integer getIdEmpresa() {
		return IdEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		IdEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
		
		// Constructor
	
	public EmpresaModel() {
	}

	public EmpresaModel(String nombreEmpresa, Boolean activo) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.activo = activo;
	}
   
}