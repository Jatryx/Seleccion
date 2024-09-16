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
    private Integer idRecruiting;

    @ManyToOne
    @JoinColumn(name = "IdEmpresa", referencedColumnName = "id")
    private EmpresaModel empresa;

    @Column(nullable = false, length = 50)
    private String nombreProyecto;

    // Getters y Setters
}
