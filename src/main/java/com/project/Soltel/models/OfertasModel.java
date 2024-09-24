package com.project.Soltel.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OfertasModel")
public class OfertasModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdOferta;

    /*@Column(nullable = false, length = 100)
    private String nombreCandidato;
 	*/
    @ManyToOne
    @JoinColumn(name = "codope", referencedColumnName = "codope")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "recruitingId", referencedColumnName = "IdRecruiting")
    private RecruitingModel recruiting;

    /*@ManyToOne
    @JoinColumn(name = "empresaId", referencedColumnName = "IdEmpresa")
    private EmpresaModel empresa;*/

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
    

	

	public Integer getIdOferta() {
		return IdOferta;
	}

	public void setIdOferta(Integer idOferta) {
		IdOferta = idOferta;
	}

	/*public String getNombreCandidato() {
		return nombreCandidato;
	}

	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}*/

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public RecruitingModel getRecruiting() {
		return recruiting;
	}

	public void setRecruiting(RecruitingModel recruiting) {
		this.recruiting = recruiting;
	}

	/*public EmpresaModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaModel empresa) {
		this.empresa = empresa;
	}*/

	public UbicacionModel getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionModel ubicacion) {
		this.ubicacion = ubicacion;
	}

	public PuestoModel getPuesto() {
		return puesto;
	}

	public void setPuesto(PuestoModel puesto) {
		this.puesto = puesto;
	}

	public String getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}

	public BigDecimal getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(BigDecimal experiencia) {
		this.experiencia = experiencia;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public EstadoModel getEstado() {
		return estado;
	}

	public void setEstado(EstadoModel estado) {
		this.estado = estado;
	}

	public LocalDate getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getHistoricoCambioEstados() {
		return historicoCambioEstados;
	}

	public void setHistoricoCambioEstados(String historicoCambioEstados) {
		this.historicoCambioEstados = historicoCambioEstados;
	}

	public CandidatosModel getCandidato() {
		return candidato;
	}

	public void setCandidato(CandidatosModel candidato) {
		this.candidato = candidato;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public OfertasModel() {
		super();
	}

	public OfertasModel(/*String nombreCandidato,*/ UsuarioModel usuario, RecruitingModel recruiting,
			/*EmpresaModel empresa,*/ UbicacionModel ubicacion, PuestoModel puesto, String tecnologias,
			BigDecimal experiencia, BigDecimal salario, EstadoModel estado, LocalDate fechaActualizacion,
			String observaciones, String historicoCambioEstados, CandidatosModel candidato, Boolean activo) {
		super();
		/*this.nombreCandidato = nombreCandidato;*/
		this.usuario = usuario;
		this.recruiting = recruiting;
		/*this.empresa = empresa;*/
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

