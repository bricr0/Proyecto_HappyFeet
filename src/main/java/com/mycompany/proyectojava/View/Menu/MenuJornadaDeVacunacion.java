package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.JornadaDeVacunacion.JornadaDeVacunacionView;

import java.util.Scanner;

public class MenuJornadaDeVacunacion {

    private final JornadaDeVacunacionView view;
    private final Scanner input;

    public MenuJornadaDeVacunacion(JornadaDeVacunacionView view) {
        this.view = view;
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ JORNADAS DE VACUNACIÓN ===");
            System.out.println("1. Crear jornada");
            System.out.println("2. Listar jornadas");
            System.out.println("3. Actualizar jornada");
            System.out.println("4. Eliminar jornada");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> view.crearJornada();
                case 2 -> view.listarJornadas();
                case 3 -> view.actualizarJornada();
                case 4 -> view.eliminarJornada();
                case 0 -> System.out.println("Saliendo del menú de jornadas...");
                default -> System.out.println("⚠️ Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }
}
