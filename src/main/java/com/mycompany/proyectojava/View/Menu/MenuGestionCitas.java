package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.Citas.CitasView;
import com.mycompany.proyectojava.View.Consultas.ConsultasView;
import com.mycompany.proyectojava.View.Veterinarios.VeterinariosView;
import com.mycompany.proyectojava.controller.Citas.CitasController;
import com.mycompany.proyectojava.controller.Consultas.ConsultasController;
import com.mycompany.proyectojava.controller.Veterinarios.VeterinariosController;
import com.mycompany.proyectojava.repository.Citas.CitasDAO;
import com.mycompany.proyectojava.repository.Citas.ICitas;
import com.mycompany.proyectojava.repository.Consultas.ConsultasDAO;
import com.mycompany.proyectojava.repository.Consultas.IConsultas;
import com.mycompany.proyectojava.repository.Veterinarios.IVeterinarios;
import com.mycompany.proyectojava.repository.Veterinarios.VeterinariosDAO;
import com.mycompany.proyectojava.service.Citas.CitasService;
import com.mycompany.proyectojava.service.Consultas.ConsultasService;
import com.mycompany.proyectojava.service.Veterinarios.VeterinariosService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuGestionCitas {
    private final Scanner input = new Scanner(System.in);

    public void MenuCitas() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTION DE CITAS Y CONSULTAS =====");
            System.out.println("1. Citas");
            System.out.println("2. Consultas");
            System.out.println("3. Veterinarios");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();

                funciones.put("1", () -> {
                    // Configuración para Citas
                    ICitas citasDAO = new CitasDAO();
                    CitasService citasService = new CitasService(citasDAO);
                    CitasController citasController = new CitasController(citasService);

                    CitasView citasView = new CitasView(citasController);
                    citasView.MostrarMenu();
                });

                funciones.put("2", () -> {
                    // Configuración para Consultas
                    IConsultas consultasDAO = new ConsultasDAO();
                    ICitas citasDAO = new CitasDAO();
                    ConsultasService consultasService = new ConsultasService(consultasDAO, citasDAO);
                    ConsultasController consultasController = new ConsultasController(consultasService);

                    ConsultasView consultasView = new ConsultasView(consultasController);
                    consultasView.MostrarMenu();
                });

                funciones.put("3", () -> {
                    IVeterinarios veterinariosDAO = new VeterinariosDAO();
                    VeterinariosService veterinariosService = new VeterinariosService(veterinariosDAO);
                    VeterinariosController veterinariosController = new VeterinariosController(veterinariosService);

                    VeterinariosView veterinariosView = new VeterinariosView(veterinariosController);
                    veterinariosView.MostrarMenu();
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
            } catch (Exception e) {
                System.out.println("❌ Error al inicializar el módulo: " + e.getMessage());
                e.printStackTrace();
            }

        } while (opcion != 0);
    }
}