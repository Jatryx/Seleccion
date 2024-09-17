package com.project.Soltel.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OfertasModel")
public class OfertasModel {
	
    @Id
    private Integer IdOferta;

    @Column(nullable = false, length = 100)
    private String nombreCandidato;

    @ManyToOne
    @JoinColumn(name = "codope", referencedColumnName = "codope")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "recruitingId", referencedColumnName = "IdRecruiting")
    private RecruitingModel recruiting;

    @ManyToOne
    @JoinColumn(name = "empresaId", referencedColumnName = "IdEmpresa")
    private EmpresaModel empresa;

    @ManyToOne
    @JoinColumn(name = "ubicacionId", referencedColumnName = "IdUbicacion")
    private UbicacionModel ubicacion;

    @ManyToOne
    @JoinColumn(name = "puestoId", referencedColumnName = "IdPuesto")
    private PuestoModel puesto;

    @Column(columnDefinition = "TEXT")
    private String tecnologias;

    @Column(precision = 3, scale = 1)
    private BigDecimal experiencia;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "estadoId", referencedColumnName = "IdEstado")
    private EstadoModel estado;

    @Column(nullable = false)
    private LocalDate fechaActualizacion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(columnDefinition = "TEXT")
    private String historicoCambioEstados;

    @ManyToOne
    @JoinColumn(name = "candidatoId", referencedColumnName = "IdCandidato")
    private CandidatosModel candidato;
    
    @Column
    private Boolean activo;
    
    

    // Getters y Setters

	public OfertasModel() {
		super();
	}

	public OfertasModel(Integer idOferta, String nombreCandidato, UsuarioModel usuario, RecruitingModel recruiting,
			EmpresaModel empresa, UbicacionModel ubicacion, PuestoModel puesto, String tecnologias,
			BigDecimal experiencia, BigDecimal salario, EstadoModel estado, LocalDate fechaActualizacion,
			String observaciones, String historicoCambioEstados, CandidatosModel candidato, Boolean activo) {
		super();
		IdOferta = idOferta;
		this.nombreCandidato = nombreCandidato;
		this.usuario = usuario;
		this.recruiting = recruiting;
		this.empresa = empresa;
		this.ubicacion = ubicacion;
		this.puesto = puesto;
		this.tecnologias = tecnologias;
		this.experiencia = experiencia;
		this.salario = salario;
		this.estado = estado;
		this.fechaActualizacion = fechaActualizacion;
		this.observaciones = observaciones;
		this.historicoCambioEstados = historicoCambioEstados;
		this.candidato = candidato;
		this.activo = activo;
	}
}

