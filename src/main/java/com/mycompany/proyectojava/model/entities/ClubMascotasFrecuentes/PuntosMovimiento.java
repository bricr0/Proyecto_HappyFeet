package com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes;

import com.mycompany.proyectojava.model.enums.ClubMascotasFrecuentes.PuntosTipo;

import java.time.LocalDateTime;

public class PuntosMovimiento {
    private Integer id;
    private String nombreDueno;
    private String documentoDueno;
    private Integer puntos;
    private PuntosTipo tipo;
    private String descripcion;
    private LocalDateTime fecha;

    public PuntosMovimiento (){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PuntosMovimiento(Integer id, String nombreDueno, String documentoDueno, Integer puntos, PuntosTipo tipo, String descripcion, LocalDateTime fecha) {
        this.id = id;
        this.nombreDueno = nombreDueno;
        this.documentoDueno = documentoDueno;
        this.puntos = puntos;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getDocumentoDueno() {
        return documentoDueno;
    }

    public void setDocumentoDueno(String documentoDueno) {
        this.documentoDueno = documentoDueno;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public PuntosTipo getTipo() {
        return tipo;
    }

    public void setTipo(PuntosTipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
