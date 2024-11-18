package com.project.Soltel.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProveedorModel")
public class ProveedorModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProveedor;
    
    @Column(nullable = true, length = 100)
    private String nombreProveedor;
    
    @Column(nullable = true, precision = 4, scale = 2)
    private BigDecimal rentabilidadProveedor;

    @Column
    private Boolean activo; // Campo para indicar si está activo

    // Getters y Setters
    
    public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
    public String getNombreProveedor() {
        return nombreProveedor;
    }

	public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public BigDecimal getRentabilidadProveedor() {
		return rentabilidadProveedor;
	}

	public void setRentabilidadProveedor(BigDecimal rentabilidadProveedor) {
		this.rentabilidadProveedor = rentabilidadProveedor;
	}

	public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // Constructores

    public ProveedorModel() {
    }

    public ProveedorModel(String nombreProveedor, BigDecimal rentabilidadProveedor, Boolean activo) {
        this.nombreProveedor = nombreProveedor;
        this.rentabilidadProveedor = rentabilidadProveedor;
        this.activo = activo;
    }
}
