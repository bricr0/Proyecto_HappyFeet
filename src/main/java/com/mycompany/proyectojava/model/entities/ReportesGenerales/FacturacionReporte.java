package com.mycompany.proyectojava.model.entities.ReportesGenerales;

public class FacturacionReporte {
    private String periodo;
    private java.math.BigDecimal totalFacturado;

    public FacturacionReporte(String periodo, java.math.BigDecimal totalFacturado) {
        this.periodo = periodo;
        this.totalFacturado = totalFacturado;
    }

    public String getPeriodo() { return periodo; }
    public java.math.BigDecimal getTotalFacturado() { return totalFacturado; }
}
