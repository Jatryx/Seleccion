package com.project.Soltel.models;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "codope", referencedColumnName = "codope")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "recruitingId", referencedColumnName = "IdRecruiting")
    private RecruitingModel recruiting;

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

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(nullable = true, precision = 5, scale = 2)
    private BigDecimal tarifa;
    
    @Column(nullable = true, precision = 4, scale = 2)
    private BigDecimal rentabilidadCliente;
    
    @Column(nullable = true, precision = 4, scale = 2)
    private BigDecimal rentabilidadClienteIncorpor;
    
    @ManyToOne
    @JoinColumn(name = "estadoId", referencedColumnName = "IdEstado")
    private EstadoModel estado;
    
    @ManyToOne
    @JoinColumn(name = "proveedorId", referencedColumnName = "IdProveedor")
    private ProveedorModel proveedor;

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

	public ProveedorModel getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorModel proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	public BigDecimal getRentabilidadCliente() {
		return rentabilidadCliente;
	}

	public void setRentabilidadCliente(BigDecimal rentabilidadCliente) {
		this.rentabilidadCliente = rentabilidadCliente;
	}

	public BigDecimal getRentabilidadClienteIncorpor() {
		return rentabilidadClienteIncorpor;
	}

	public void setRentabilidadClienteIncorpor(BigDecimal rentabilidadClienteIncorpor) {
		this.rentabilidadClienteIncorpor = rentabilidadClienteIncorpor;
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

	public OfertasModel(Integer idOferta, UsuarioModel usuario, RecruitingModel recruiting, UbicacionModel ubicacion,
			PuestoModel puesto, String tecnologias, BigDecimal experiencia, BigDecimal salario, BigDecimal tarifa,
			BigDecimal rentabilidadCliente, BigDecimal rentabilidadClienteIncorpor, EstadoModel estado,
			LocalDate fechaActualizacion, String observaciones, String historicoCambioEstados,
			CandidatosModel candidato, Boolean activo) {
		super();
		IdOferta = idOferta;
		this.usuario = usuario;
		this.recruiting = recruiting;
		this.ubicacion = ubicacion;
		this.puesto = puesto;
		this.tecnologias = tecnologias;
		this.experiencia = experiencia;
		this.salario = salario;
		this.tarifa = tarifa;
		this.rentabilidadCliente = rentabilidadCliente;
		this.rentabilidadClienteIncorpor = rentabilidadClienteIncorpor;
		this.estado = estado;
		this.fechaActualizacion = fechaActualizacion;
		this.observaciones = observaciones;
		this.historicoCambioEstados = historicoCambioEstados;
		this.candidato = candidato;
		this.activo = activo;
	}


}

