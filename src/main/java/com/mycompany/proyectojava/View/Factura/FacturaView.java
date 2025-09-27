package com.mycompany.proyectojava.View.Factura;

import com.mycompany.proyectojava.controller.Factura.FacturaController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FacturaView {
    private final FacturaController controller;
    private final Scanner input;

    public FacturaView(FacturaController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

//    ------------------------------------------------ MENU PRINCIPAL ------------------------------------------------

    public void mostrarMenu() {
        String opcion = "";
        while (!opcion.equals("0")) {
            System.out.println("\n --- GESTIÓN DE FACTURAS ---");
            System.out.println("""
                    1. Generar factura por documento
                    2. Listar facturas
                    0. Salir
                    >>> Elige una opción:""");

            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::generarFacturaPorDocumento);
//                funciones.put("2", this::listarFacturas);
                funciones.put("0", () -> System.out.println("Saliendo..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("⚠️ Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                System.out.println("Presiona Enter para continuar...");
                input.nextLine();
                opcion = "";
            }
        }
    }

//    ------------------------------------------------ OPCIONES ------------------------------------------------

    private void generarFacturaPorDocumento() {
        System.out.print("Ingrese el documento del dueño: ");
        String documento = input.nextLine();
        controller.generarFacturaPorDocumento(documento);
    }

//    private void listarFacturas() {
//        controller.();
//    }
}
