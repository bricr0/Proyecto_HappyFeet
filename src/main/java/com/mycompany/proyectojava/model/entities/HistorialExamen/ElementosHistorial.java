
package com.mycompany.proyectojava.model.entities.HistorialExamen;

import java.util.Date;

public class ElementosHistorial {
    private Date fecha;
    private String tipo;
    private String descripcion;
    private String diagnostico;

    public ElementosHistorial(Date fecha, String tipo, String descripcion, String diagnostico) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    
}
