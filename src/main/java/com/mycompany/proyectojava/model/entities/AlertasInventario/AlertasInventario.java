package com.mycompany.proyectojava.model.entities.AlertasInventario;

import java.util.Date;

public class AlertasInventario {
    private Integer id;
    private Integer inventario_id;
    private String tipo_alerta;
    private String mensaje;
    private Date fecha_creacion;
    private Boolean leido;

    public AlertasInventario(Integer id, Integer inventario_id, String tipo_alerta, String mensaje, Date fecha_creacion, Boolean leido) {
        this.id = id;
        this.inventario_id = inventario_id;
        this.tipo_alerta = tipo_alerta;
        this.mensaje = mensaje;
        this.fecha_creacion = fecha_creacion;
        this.leido = leido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventario_id() {
        return inventario_id;
    }

    public void setInventario_id(Integer inventario_id) {
        this.inventario_id = inventario_id;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }
}
