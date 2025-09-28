package com.mycompany.proyectojava.model.entities.Factura.ElementosFactura;

import com.mycompany.proyectojava.model.enums.Factura.ElementosFactura.ElementosFacturaTipo;

import java.math.BigDecimal;

public class ElementosFactura {
    private int id;
    private int facturaId;
    private Integer productoId;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private ElementosFacturaTipo tipo;
    private Integer consultaId;

    public ElementosFactura(String descripcion, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public ElementosFacturaTipo getTipo() {
        return tipo;
    }

    public void setTipo(ElementosFacturaTipo tipo) {
        this.tipo = tipo;
    }

    public Integer getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Integer consultaId) {
        this.consultaId = consultaId;
    }

}
