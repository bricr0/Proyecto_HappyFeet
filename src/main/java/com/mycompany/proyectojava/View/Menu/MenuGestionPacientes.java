package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.Duenos.DuenosView;
import com.mycompany.proyectojava.View.Especie.EspecieView;
import com.mycompany.proyectojava.View.Mascota.MascotaView;
import com.mycompany.proyectojava.View.Razas.RazasView;
import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.controller.Especie.EspecieController;
import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.controller.Razas.RazaController;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Dueno.IDueno;
import com.mycompany.proyectojava.repository.Especie.EspecieDAO;
import com.mycompany.proyectojava.repository.Especie.IEspecie;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;
import com.mycompany.proyectojava.repository.Razas.RazasDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuGestionPacientes {
    private final Scanner input = new Scanner(System.in);

    public void MenuPacientes() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTION DE PACIENTES =====");
            System.out.println("1. Duenos");
            System.out.println("2. Mascotas");
            System.out.println("3. Especies");
            System.out.println("4. Razas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", () -> {
                    IDueno duenoDAO = new DuenoDAO();
                    DuenoController duenoController = new DuenoController(duenoDAO);
                    DuenosView duenosView = new DuenosView(duenoController);
                    duenosView.MostrarMenu();
                });

                funciones.put("2", () -> {
                    MascotaControlller mascotaControlller = new MascotaControlller(new MascotaDAO());
                    DuenoController duenoController = new DuenoController(new DuenoDAO());
                    RazaController razaController = new RazaController(new RazasDAO());
                    EspecieController especieController = new EspecieController(new EspecieDAO());

                    MascotaView mascotaView = new MascotaView(mascotaControlller, duenoController, razaController, especieController);
                    mascotaView.mostrarMenu();
                });

                funciones.put("3", () -> {
                    EspecieController especieController = new EspecieController(new EspecieDAO());
                    EspecieView especieView = new EspecieView(especieController);
                    especieView.MostrarMenu();
                });

                funciones.put("4", () -> {
                    RazaController razaController = new RazaController(new RazasDAO());
                    EspecieController especieController = new EspecieController(new EspecieDAO());
                    RazasView razasView = new RazasView(razaController, especieController);
                    razasView.MostrarMenu();
                });

                funciones.put("0", () -> System.out.println("Volviendo al menú principal..."));

                Runnable funcion = funciones.get(String.valueOf(opcion));
                if (funcion != null){
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
