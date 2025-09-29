package com.mycompany.proyectojava.model.entities.Adopcion;

import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEnum;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEstadoEnum;

import java.time.LocalDateTime;

public class Adopcion {
    private Integer id;
    private Integer mascotaId;
    private Integer adoptanteId;
    private String nombreMascota;
    private String nombreAdoptante;
    private LocalDateTime fechaAdopcion;
    private AdopcionEnum tipo;
    private AdopcionEstadoEnum estado;
    private Integer contratoId;
    private String notas;

    public Adopcion(Integer mascotaId, Integer adoptanteId, AdopcionEnum tipo, String notas) {
        this.mascotaId = mascotaId;
        this.adoptanteId = adoptanteId;
        this.tipo = tipo;
        this.notas = notas;
    }

    public Adopcion(String nombreMascota, String nombreAdoptante, LocalDateTime fechaAdopcion,
                    AdopcionEnum tipo, AdopcionEstadoEnum estado, Integer contratoId, String notas) {
        this.nombreMascota = nombreMascota;
        this.nombreAdoptante = nombreAdoptante;
        this.fechaAdopcion = fechaAdopcion;
        this.tipo = tipo;
        this.estado = estado;
        this.contratoId = contratoId;
        this.notas = notas;
    }

    public Adopcion(Integer adoptanteId, String nombreMascota, String nombreAdoptante, LocalDateTime fechaAdopcion,
                    AdopcionEnum tipo, AdopcionEstadoEnum estado, Integer contratoId, String notas) {
        this.adoptanteId = adoptanteId;
        this.nombreMascota = nombreMascota;
        this.nombreAdoptante = nombreAdoptante;
        this.fechaAdopcion = fechaAdopcion;
        this.tipo = tipo;
        this.estado = estado;
        this.contratoId = contratoId;
        this.notas = notas;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getNombreAdoptante() {
        return nombreAdoptante;
    }

    public void setNombreAdoptante(String nombreAdoptante) {
        this.nombreAdoptante = nombreAdoptante;
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

    public Integer getAdoptanteId() {
        return adoptanteId;
    }

    public void setAdoptanteId(Integer adoptanteId) {
        this.adoptanteId = adoptanteId;
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
