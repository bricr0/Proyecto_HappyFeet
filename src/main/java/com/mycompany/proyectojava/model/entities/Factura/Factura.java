package com.mycompany.proyectojava.model.entities.Factura;

import com.mycompany.proyectojava.model.entities.Factura.ElementosFactura.ElementosFactura;
import com.mycompany.proyectojava.model.enums.Factura.FacturaEstado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Factura {
    private int id;
    private int duenoId;
    private LocalDateTime fechaEmision;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    FacturaEstado estado;
    private List<ElementosFactura> elementos;

    public Factura(int id, int duenoId, LocalDateTime fechaEmision, BigDecimal subtotal, BigDecimal impuestos, BigDecimal total, FacturaEstado estado, List<ElementosFactura> elementos) {
        this.id = id;
        this.duenoId = duenoId;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
        this.estado = estado;
        this.elementos = elementos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public FacturaEstado getEstado() {
        return estado;
    }

    public void setEstado(FacturaEstado estado) {
        this.estado = estado;
    }

    public List<ElementosFactura> getElementos() {
        return elementos;
    }

    public void setElementos(List<ElementosFactura> elementos) {
        this.elementos = elementos;
    }

}
