package com.mycompany.proyectojava.model.entities.Adopcion;

import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEnum;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEstadoEnum;

import java.time.LocalDateTime;

public class Adopcion {
    private Integer id;
    private Integer mascotaId;
    private Integer adoptanteId;
    private LocalDateTime fechaAdopcion;
    private AdopcionEnum tipo;
    private AdopcionEstadoEnum estado;
    private Integer contratoId;
    private String notas;

    public Adopcion(Integer id, Integer mascotaId, Integer adoptanteId, LocalDateTime fechaAdopcion, AdopcionEnum tipo, AdopcionEstadoEnum estado, Integer contratoId, String notas) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.adoptanteId = adoptanteId;
        this.fechaAdopcion = fechaAdopcion;
        this.tipo = tipo;
        this.estado = estado;
        this.contratoId = contratoId;
        this.notas = notas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Integer mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Integer getAdoptanteId() {
        return adoptanteId;
    }

    public void setAdoptanteId(Integer adoptanteId) {
        this.adoptanteId = adoptanteId;
    }

    public LocalDateTime getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDateTime fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public AdopcionEnum getTipo() {
        return tipo;
    }

    public void setTipo(AdopcionEnum tipo) {
        this.tipo = tipo;
    }

    public AdopcionEstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(AdopcionEstadoEnum estado) {
        this.estado = estado;
    }

    public Integer getContratoId() {
        return contratoId;
    }

    public void setContratoId(Integer contratoId) {
        this.contratoId = contratoId;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
