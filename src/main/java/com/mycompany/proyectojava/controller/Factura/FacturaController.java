package com.mycompany.proyectojava.controller.Factura;

import com.mycompany.proyectojava.service.Facturas.FacturaService;


public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    // ------------------------------------------------ GENERAR FACTURA ------------------------------------------------
    public void generarFacturaPorDocumento(String documentoDueno) {
        try {
            facturaService.generarFacturaPorDocumento(documentoDueno);
        } catch (Exception e) {
            System.out.println("❌ Error al generar la factura: " + e.getMessage());
        }
    }

    // ------------------------------------------------ LISTAR FACTURAS ------------------------------------------------
//    public void listarFacturas() {
//        try {
//            List<Factura> facturas = facturaDAO.();
//            if (facturas.isEmpty()) {
//                System.out.println("⚠️ No hay facturas registradas.");
//            } else {
//                System.out.println("\n--- LISTADO DE FACTURAS ---");
//                for (Factura f : facturas) {
//                    System.out.println("ID: " + f.getId() +
//                            " | Dueño: " + f.getDuenoId() +
//                            " | Fecha: " + f.getFecha() +
//                            " | Total: $" + f.getTotal());
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("❌ Error al listar facturas: " + e.getMessage());
//        }
//    }
}
