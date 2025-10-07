package com.mycompany.proyectojava.model.entities.Consultas;

import java.util.Date;

public class Consultas {
    private Integer id;
    private Integer cita_id;
    private Integer veterinario_id;
    private String diagnostico;
    private String tratamiento_recomendado;
    private String procedimientos;
    private Date fecha_registro;

    public Consultas(Integer id, Integer cita_id, Integer veterinario_id, String diagnostico, String tratamiento_recomendado, String procedimientos, Date fecha_registro) {
        this.id = id;
        this.cita_id = cita_id;
        this.veterinario_id = veterinario_id;
        this.diagnostico = diagnostico;
        this.tratamiento_recomendado = tratamiento_recomendado;
        this.procedimientos = procedimientos;
        this.fecha_registro = fecha_registro;
    }

    public Consultas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
    }

    public String getTratamiento_recomendado() {
        return tratamiento_recomendado;
    }

    public void setTratamiento_recomendado(String tratamiento_recomendado) {
        this.tratamiento_recomendado = tratamiento_recomendado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Integer getVeterinario_id() {
        return veterinario_id;
    }

    public void setVeterinario_id(Integer veterinario_id) {
        this.veterinario_id = veterinario_id;
    }

    public Integer getCita_id() {
        return cita_id;
    }

    public void setCita_id(Integer cita_id) {
        this.cita_id = cita_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
