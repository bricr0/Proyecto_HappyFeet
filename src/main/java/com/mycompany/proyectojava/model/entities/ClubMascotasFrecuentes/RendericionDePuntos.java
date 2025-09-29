package com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes;

import com.mycompany.proyectojava.model.enums.ClubMascotasFrecuentes.RendericionEstados;

import java.time.LocalDateTime;

public class RendericionDePuntos {
    private String nombreDueno;
    private String documentoDueno;
    private int puntosUsados;
    private String descripcion;
    private RendericionEstados estado;
    private LocalDateTime fecha;

    public RendericionDePuntos (){}

    public RendericionDePuntos(String nombreDueno, String documentoDueno, int puntosUsados, String descripcion, RendericionEstados estado, LocalDateTime fecha) {
        this.nombreDueno = nombreDueno;
        this.documentoDueno = documentoDueno;
        this.puntosUsados = puntosUsados;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public int getPuntosUsados() {
        return puntosUsados;
    }

    public void setPuntosUsados(int puntosUsados) {
        this.puntosUsados = puntosUsados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public RendericionEstados getEstado() {
        return estado;
    }

    public void setEstado(RendericionEstados estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
