package com.mycompany.proyectojava.model.entities.Citas;

import java.util.Date;

public class Citas {
    private Integer id;
    private Integer mascota_id;
    private Date fecha_hora;
    private Integer estado_id;
    private Integer veterinario_id;
    private String motivo;
    private String observaciones;

    public Citas(Integer mascota_id, Date fecha_hora, Integer estado_id, Integer veterinario_id, String motivo, String observaciones) {
        this.mascota_id = mascota_id;
        this.fecha_hora = fecha_hora;
        this.estado_id = estado_id;
        this.veterinario_id = veterinario_id;
        this.motivo = motivo;
        this.observaciones = observaciones;
    }

    public Citas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(Integer mascota_id) {
        this.mascota_id = mascota_id;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public Integer getVeterinario_id() {
        return veterinario_id;
    }

    public void setVeterinario_id(Integer veterinario_id) {
        this.veterinario_id = veterinario_id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}




