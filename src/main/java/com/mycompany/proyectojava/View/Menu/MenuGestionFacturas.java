package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.Service.FacturaService.FacturaService;
import com.mycompany.proyectojava.View.Factura.FacturaView;
import com.mycompany.proyectojava.controller.Factura.FacturaController;
import com.mycompany.proyectojava.model.entities.Factura.Factura;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Factura.IFacturaDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuGestionFacturas {
    private final Scanner input = new Scanner(System.in);

    public void MenuFacturas() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTIÓN DE FACTURAS =====");
            System.out.println("1. Generación de Factura en Texto Plano");
            System.out.println("2. Reportes Gerenciales");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", () -> {
                    FacturaService facturaService = new FacturaService();
                    FacturaController facturaController = new FacturaController(facturaService);

                    FacturaView facturaView = new FacturaView(facturaController);
                    facturaView.mostrarMenu();
                });

                funciones.put("2", this::menuReportesGerenciales);

                funciones.put("0", () -> System.out.println("Volviendo al menú principal..."));

                Runnable funcion = funciones.get(String.valueOf(opcion));
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("❌ Opción inválida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida, intente de nuevo.");
            }

        } while (opcion != 0);
    }

    // ------------------------------------------------ SUBMENÚ REPORTES ------------------------------------------------

    private void menuReportesGerenciales() {
        int opcion = -1;
        do {
            System.out.println("\n===== REPORTES GERENCIALES =====");
            System.out.println("1. Servicios más solicitados");
            System.out.println("2. Desempeño del equipo veterinario");
            System.out.println("3. Estado del inventario (productos a vencer, necesidad de reabastecimiento)");
            System.out.println("4. Análisis de facturación por período");
            System.out.println("0. Volver al menú de facturas");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", () -> {
                    System.out.println("📊 Generando reporte: Servicios más solicitados...");
                });
                funciones.put("2", () -> {
                    System.out.println("📊 Generando reporte: Desempeño del equipo veterinario...");
                });
                funciones.put("3", () -> {
                    System.out.println("📊 Generando reporte: Estado del inventario...");
                });
                funciones.put("4", () -> {
                    System.out.println("📊 Generando reporte: Análisis de facturación por período...");
                });
                funciones.put("0", () -> System.out.println("Volviendo al menú de facturas..."));

                Runnable funcion = funciones.get(String.valueOf(opcion));
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("❌ Opción inválida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida, intente de nuevo.");
            }

        } while (opcion != 0);
    }
}
