package com.educonnect.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "matricula_curso")
public class MatriculaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_matricula", nullable = false, unique = true, length = 30)
    private String codigoMatricula;

    // Relación Muchos a Uno con Estudiante
    @ManyToOne(optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    // Relación Muchos a Uno con Profesor
    @ManyToOne(optional = false)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @Column(name = "nombre_curso", nullable = false, length = 100)
    private String nombreCurso;

    @Column(nullable = false)
    private Integer creditos;

    @Column(nullable = false, length = 20)
    private String periodo;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "monto_arancel", nullable = false)
    private BigDecimal montoArancel;

    @Column(name = "fecha_matricula", nullable = false)
    private LocalDateTime fechaMatricula;

    // Constructores
    public MatriculaCurso() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMontoArancel() {
        return montoArancel;
    }

    public void setMontoArancel(BigDecimal montoArancel) {
        this.montoArancel = montoArancel;
    }

    public LocalDateTime getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDateTime fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
}