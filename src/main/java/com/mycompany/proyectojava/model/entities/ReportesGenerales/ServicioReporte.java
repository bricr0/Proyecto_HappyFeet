package com.mycompany.proyectojava.model.entities.ReportesGenerales;

public class ServicioReporte {
    private String servicio;
    private Integer veces;

    public ServicioReporte(String servicio, int veces) {
        this.servicio = servicio;
        this.veces = veces;
    }

    public String getServicio() { return servicio; }
    public Integer getVeces() { return veces; }
}
