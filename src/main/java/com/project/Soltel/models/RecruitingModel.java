package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RecruitingModel")
public class RecruitingModel {
	
    @Id
    private Integer IdRecruiting;

    @ManyToOne
    @JoinColumn(name = "empresaId", referencedColumnName = "IdEmpresa", nullable = false)
    private EmpresaModel empresa;

    @Column(nullable = false, length = 50)
    private String nombreProyecto;
    
    @Column(nullable = false, length = 50)
    private String URL;
    
    @Column
    private Boolean activo;
    
    
    
    // Getters y Setters

	public Integer getIdRecruiting() {
		return IdRecruiting;
	}

	public void setIdRecruiting(Integer idRecruiting) {
		IdRecruiting = idRecruiting;
	}

	public EmpresaModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaModel empresa) {
		this.empresa = empresa;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	
	// Constructor

	public RecruitingModel() {
		super();
	}

	public RecruitingModel(Integer idRecruiting, EmpresaModel empresa, String nombreProyecto, String uRL,
			Boolean activo) {
		super();
		IdRecruiting = idRecruiting;
		this.empresa = empresa;
		this.nombreProyecto = nombreProyecto;
		URL = uRL;
		this.activo = activo;
	}


    
}
