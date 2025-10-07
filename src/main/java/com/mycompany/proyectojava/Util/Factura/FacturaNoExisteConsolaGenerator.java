/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectojava.Util.Factura;

import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Factura.Factura;

/**
 *
 * @author camper
 */
public class FacturaNoExisteConsolaGenerator {
        public static void generar(Factura factura, Dueno dueno) {
        System.out.println("INFORME DE FACTURACION POR CLIENTE\n");
        System.out.println("----DATOS DEL CLIENTE ----\n");
        System.out.println("Nombre: " + dueno.getNombre() + "\n");
        System.out.println("Documento: " + dueno.getDocumento() + "\n");
        System.out.println("--- HISTORIAL DE FACTURAS\n");
        System.out.println("ID Factura: " + factura.getId() + "\n");
        System.out.println("Fecha Emisión: " + factura.getFechaEmision() + "\n");
        System.out.println("Total Factura: $0 \n");
        System.out.println("============================\n");
    }
}
