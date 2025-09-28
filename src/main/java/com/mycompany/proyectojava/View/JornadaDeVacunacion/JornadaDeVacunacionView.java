package com.mycompany.proyectojava.View.JornadaDeVacunacion;

import com.mycompany.proyectojava.controller.JornadaDeVacunacion.JornadaDeVacunacionController;
import com.mycompany.proyectojava.model.entities.JornadasVacunacion.JornadasDeVacunacion;
import com.mycompany.proyectojava.model.enums.JornadasVacunacion.JornadaDeVacunacionEstado;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class JornadaDeVacunacionView {
    private JornadaDeVacunacionController jornadaController;
    private Scanner input = new Scanner(System.in);

    public JornadaDeVacunacionView(JornadaDeVacunacionController jornadaController) {
        this.jornadaController = jornadaController;
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n--- Jornadas de Vacunación ---");
            System.out.println("1. Crear jornada");
            System.out.println("2. Listar jornadas");
            System.out.println("3. Actualizar jornada");
            System.out.println("4. Eliminar jornada");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(input.nextLine());

            switch (opcion) {
                case 1 -> crearJornada();
                case 2 -> listarJornadas();
                case 3 -> actualizarJornada();
                case 4 -> eliminarJornada();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("⚠️ Opción no válida");
            }

        } while (opcion != 0);
    }

    // --- MÉTODOS CRUD ---

    public void crearJornada() {
        System.out.println("--- Crear Jornada ---");
        System.out.print("Nombre: ");
        String nombre = input.nextLine();

        System.out.print("Fecha (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(input.nextLine());

        System.out.print("Ubicación: ");
        String ubicacion = input.nextLine();

        System.out.print("Notas: ");
        String notas = input.nextLine();

        JornadasDeVacunacion jornada = new JornadasDeVacunacion();
        jornada.setNombre(nombre);
        jornada.setFecha(fecha);
        jornada.setUbicacion(ubicacion);
        jornada.setNotas(notas);

        jornada.setEstado(JornadaDeVacunacionEstado.programada.name());

        jornadaController.crearJornada(jornada);
    }

    public void listarJornadas() {
        System.out.print("Filtrar por estado (programada/en_curso/finalizada/cancelada) o ENTER para todas: ");
        String filtro = input.nextLine();
        JornadaDeVacunacionEstado estadoFiltro = filtro.isEmpty() ? null :
                JornadaDeVacunacionEstado.valueOf(filtro.toUpperCase());

        List<JornadasDeVacunacion> jornadas = jornadaController.listarJornadas(estadoFiltro);
        System.out.println("--- Jornadas ---");
        for (JornadasDeVacunacion j : jornadas) {
            System.out.printf("Nombre: %s | Fecha: %s | Ubicación: %s | Estado: %s%n",
                    j.getNombre(), j.getFecha(), j.getUbicacion(), j.getEstado());
        }
    }

    public void actualizarJornada() {
        System.out.print("Nombre de la jornada a actualizar: ");
        String nombre = input.nextLine();

        JornadasDeVacunacion jornada = jornadaController.obtenerJornadaPorNombre(nombre);
        if (jornada == null) {
            System.out.println("⚠️ Jornada no encontrada");
            return;
        }

        System.out.print("Nuevo nombre (ENTER para no cambiar): ");
        String nuevoNombre = input.nextLine();
        if (!nuevoNombre.isEmpty()) jornada.setNombre(nuevoNombre);

        System.out.print("Nueva fecha (YYYY-MM-DD, ENTER para no cambiar): ");
        String fechaStr = input.nextLine();
        if (!fechaStr.isEmpty()) jornada.setFecha(LocalDate.parse(fechaStr));

        System.out.print("Nueva ubicación (ENTER para no cambiar): ");
        String ubicacion = input.nextLine();
        if (!ubicacion.isEmpty()) jornada.setUbicacion(ubicacion);

        System.out.print("Nuevo estado (programada/en_curso/finalizada/cancelada, ENTER para no cambiar): ");
        String estadoStr = input.nextLine();
        if (!estadoStr.isEmpty()) jornada.setEstado(JornadaDeVacunacionEstado.valueOf(estadoStr.toUpperCase()).name());

        jornadaController.actualizarJornada(jornada);
    }

    public void eliminarJornada() {
        System.out.print("Nombre de la jornada a eliminar: ");
        String nombre = input.nextLine();

        JornadasDeVacunacion jornada = jornadaController.obtenerJornadaPorNombre(nombre);
        if (jornada == null) {
            System.out.println("⚠️ Jornada no encontrada");
            return;
        }

        jornadaController.eliminarJornada(jornada.getId());
    }
}
