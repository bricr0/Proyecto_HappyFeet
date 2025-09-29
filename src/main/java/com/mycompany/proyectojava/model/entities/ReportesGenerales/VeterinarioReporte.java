package com.mycompany.proyectojava.model.entities.ReportesGenerales;

public class VeterinarioReporte {
    private String veterinario;
    private Integer consultas;

    public VeterinarioReporte(String veterinario, int consultas) {
        this.veterinario = veterinario;
        this.consultas = consultas;
    }

    public String getVeterinario() { return veterinario; }
    public Integer getConsultas() { return consultas; }
}
