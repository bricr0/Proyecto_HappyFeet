package com.mycompany.proyectojava.model.entities.JornadasVacunacion;

import com.mycompany.proyectojava.model.enums.JornadasVacunacion.JornadaDeVacunacionEstado;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JornadasDeVacunacion {
    private Integer id;
    private String nombre;
    private LocalDate fecha;
    private String ubicacion;
    private String notas;
    private String creadoPor;
    private String estado;
    private LocalDateTime createdAt;

    public JornadasDeVacunacion(Integer id, String nombre, LocalDate fecha, String ubicacion, String notas, String creadoPor, String estado, LocalDateTime createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.notas = notas;
        this.creadoPor = creadoPor;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    public JornadasDeVacunacion() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
