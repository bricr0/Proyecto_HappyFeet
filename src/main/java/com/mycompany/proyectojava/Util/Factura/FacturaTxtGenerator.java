package com.mycompany.proyectojava.Util.Factura;

import com.mycompany.proyectojava.model.entities.Factura.Factura;
import com.mycompany.proyectojava.model.entities.Factura.ElementosFactura.ElementosFactura;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FacturaTxtGenerator {

    public static void generar(Factura factura, Dueno dueno) {
        String nombreArchivo = "Facturas/Factura_" + factura.getId() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("===== FACTURA CLÍNICA VETERINARIA =====\n");
            writer.write("ID Factura: " + factura.getId() + "\n");
            writer.write("Fecha Emisión: " + factura.getFechaEmision() + "\n");
            writer.write("Estado: " + factura.getEstado() + "\n\n");

            writer.write("---- CLIENTE ----\n");
            writer.write("Nombre: " + dueno.getNombre() + "\n");
            writer.write("Documento: " + dueno.getDocumento() + "\n");
            writer.write("Teléfono: " + dueno.getTelefono() + "\n");
            writer.write("Correo: " + dueno.getEmail() + "\n\n");

            writer.write("---- DETALLE ----\n");
            for (ElementosFactura elem : factura.getElementos()) {
                writer.write(
                        elem.getDescripcion() + " x" + elem.getCantidad() +
                                " @ " + elem.getPrecioUnitario() +
                                " = " + elem.getSubtotal() + "\n"
                );
            }

            writer.write("\n----------------------------\n");
            writer.write("Subtotal: " + factura.getSubtotal() + "\n");
            writer.write("Impuestos: " + factura.getImpuestos() + "\n");
            writer.write("TOTAL A PAGAR: " + factura.getTotal() + "\n");
            writer.write("============================\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
