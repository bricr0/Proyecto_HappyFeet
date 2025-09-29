package com.mycompany.proyectojava.model.entities.ReportesGenerales;

public class InventarioReporte {
    private String producto;
    private Integer stock;
    private Integer stockMinimo;
    private java.sql.Date fechaVencimiento;

    public InventarioReporte(String producto, int stock, int stockMinimo, java.sql.Date fechaVencimiento) {
        this.producto = producto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getProducto() { return producto; }
    public int getStock() { return stock; }
    public int getStockMinimo() { return stockMinimo; }
    public java.sql.Date getFechaVencimiento() { return fechaVencimiento; }
}
