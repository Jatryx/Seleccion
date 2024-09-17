package com.project.Soltel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class UsuarioModel {
<<<<<<< HEAD
    
=======
	
>>>>>>> a10c3fe4223e28fbf9fbbf0deb372ab13fc4f35f
    @Id
    private String codope;

    @Column(nullable = false, length = 50)
    private String contraseña;
    
    @Column
    private Boolean activo;
<<<<<<< HEAD
=======
    
    
    
    	// Getters y Setters
    
	public Integer getCodope() {
		return codope;
	}
>>>>>>> a10c3fe4223e28fbf9fbbf0deb372ab13fc4f35f

    // Getters y Setters
    public String getCodope() {
        return codope;
    }

<<<<<<< HEAD
    public void setCodope(String codope) {
        this.codope = codope;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // Constructor
    public UsuarioModel() {
    }

    public UsuarioModel(String codope, String contraseña, Boolean activo) {
        this.codope = codope;
        this.contraseña = contraseña;
        this.activo = activo;
    }

	
}
=======
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	
	
		// Constructor
	
	
	public UsuarioModel() {
	}

	public UsuarioModel(Integer codope, String contraseña, Boolean activo) {
		this.codope = codope;
		this.contraseña = contraseña;
		this.activo = activo;
	}
	
	
}
>>>>>>> a10c3fe4223e28fbf9fbbf0deb372ab13fc4f35f
