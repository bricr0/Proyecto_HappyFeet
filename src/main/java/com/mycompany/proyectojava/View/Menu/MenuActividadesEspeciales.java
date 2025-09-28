package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.Adopcion.AdopcionView;
import com.mycompany.proyectojava.View.JornadaDeVacunacion.JornadaDeVacunacionView;
import com.mycompany.proyectojava.controller.Adopcion.AdopcionController;
import com.mycompany.proyectojava.controller.JornadaDeVacunacion.JornadaDeVacunacionController;
import com.mycompany.proyectojava.repository.Adopcion.AdopcionDAO;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.JornadaDeVacunacion.JornadaDeVacunacionDAO;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuActividadesEspeciales {
        private final Scanner input = new Scanner(System.in);

    public void MenuEspeciales() {
        Integer opcion = -1;
        AdopcionDAO adopcionDAO = new AdopcionDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        DuenoDAO duenoDAO = new DuenoDAO();
        AdopcionController adopcionController = new AdopcionController(adopcionDAO);
        AdopcionView adopcionView = new AdopcionView(adopcionController, mascotaDAO, duenoDAO);
        JornadaDeVacunacionDAO jornadaDeVacunacionDAO = new JornadaDeVacunacionDAO();
        JornadaDeVacunacionController jornadaDeVacunacionController = new JornadaDeVacunacionController(jornadaDeVacunacionDAO);
        JornadaDeVacunacionView jornadaDeVacunacionView = new JornadaDeVacunacionView(jornadaDeVacunacionController);

        do {
            System.out.println("\n===== ACTIVIDADES ESPECIALES =====");
            System.out.println("1. Adoptar mascotas");
            System.out.println("2. Vacunar mascotas");
            System.out.println("3. Club de mascotas frecuentes");
            System.out.println("0. Volver al menú principal");
            System.out.print(">>> Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", () -> {
                    try {
                        adopcionView.mostrarMenu();
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al registrar adopción: " + e.getMessage());
                    }
                });

                funciones.put("2", () -> {
                    try {
                        jornadaDeVacunacionView.mostrarMenu();
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al listar adopciones: " + e.getMessage());
                    }
                });




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
}
