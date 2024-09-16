package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UsuarioModel")
public class UsuarioModel {
    @Id
    private Integer codope;

    @Column(nullable = false, length = 50)
    private String contraseña;
    
    
    	// Getters y Setters
    
    
	public Integer getCodope() {
		return codope;
	}

	public void setCodope(Integer codope) {
		this.codope = codope;
	}
	
	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

		// Constructor
	
	public UsuarioModel() {
	}
	
	public UsuarioModel(Integer codope, String contraseña) {
		this.codope = codope;
		this.contraseña = contraseña;
	}
    
}
