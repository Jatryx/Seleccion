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

	public EmpresaModel(Integer idEmpresa, String nombreEmpresa, Boolean activo) {
		super();
		IdEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.activo = activo;
	}
   
<<<<<<< HEAD
}
=======
}
>>>>>>> a10c3fe4223e28fbf9fbbf0deb372ab13fc4f35f
