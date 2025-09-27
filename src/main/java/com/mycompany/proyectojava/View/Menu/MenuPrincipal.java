package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.Duenos.DuenosView;
import com.mycompany.proyectojava.View.Mascota.MascotaView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner input = new Scanner(System.in);

    public void mostrar() {
        int opcion = -1;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestion de pacientes (Mascotas y Duenos)");
            System.out.println("2. Servicios medicos y citas");
            System.out.println("3. Inventario y farmacia");
            System.out.println("4. Facturacion e informes");
            System.out.println("5. Actividades especiales");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();

                funciones.put("1", () -> {
                    MenuGestionPacientes menuGestionPacientes = new MenuGestionPacientes();
                    menuGestionPacientes.MenuPacientes();
                });

                funciones.put("4", () -> {
                    MenuGestionFacturas menuGestionFacturas = new MenuGestionFacturas();
                    menuGestionFacturas.MenuFacturas();
                });

                funciones.put("0", () -> System.out.println("Saliendo del programa..."));

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
