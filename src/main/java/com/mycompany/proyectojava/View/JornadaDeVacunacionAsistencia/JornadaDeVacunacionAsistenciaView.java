package com.mycompany.proyectojava.View.JornadaDeVacunacionAsistencia;

import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.controller.JornadaDeVacunacion.JornadaDeVacunacionController;
import com.mycompany.proyectojava.controller.JornadaDeVacunacionAsistencia.JornadaDeVacunacionAsistenciaController;
import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.JornadaDeVacunacionAsistencia.JornadaDeVacunacionAsistencia;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.model.enums.JornadaVacunacionAsistenciaEstado.JornadaDeVacunacionAsistenciaEstado;

import java.util.List;
import java.util.Scanner;

public class JornadaDeVacunacionAsistenciaView {
    private  JornadaDeVacunacionAsistenciaController controller;
    private JornadaDeVacunacionController jornadaDeVacunacionController;
    private DuenoController duenoController;
    private MascotaControlller mascotaControlller;
    private  Scanner input = new Scanner(System.in);

    public JornadaDeVacunacionAsistenciaView(JornadaDeVacunacionAsistenciaController controller, JornadaDeVacunacionController jornadaDeVacunacionController, DuenoController duenoController, MascotaControlller mascotaControlller) {
        this.controller = controller;
        this.jornadaDeVacunacionController = jornadaDeVacunacionController;
        this.duenoController = duenoController;
        this.mascotaControlller = mascotaControlller;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Asistencias de Jornadas de Vacunación ---");
            System.out.println("1. Registrar asistencia");
            System.out.println("2. Listar asistencias por jornada");
            System.out.println("3. Actualizar asistencia");
            System.out.println("4. Eliminar asistencia");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(input.nextLine());

            try {
                switch (opcion) {
                    case 1 -> registrarAsistencia();
                    case 2 -> listarAsistencias();
                    case 3 -> actualizarAsistencia();
                    case 4 -> eliminarAsistencia();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("⚠️ Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void registrarAsistencia() {
        System.out.println("\n--- Registrar Asistencia ---");

        System.out.print("Nombre de la jornada: ");
        String nombreJornada = input.nextLine().trim();

        System.out.print("Documento del dueño: ");
        String documento = input.nextLine().trim();

        System.out.print("Microchip de la mascota: ");
        String microchip = input.nextLine().trim();

        System.out.print("Producto (ID, opcional): ");
        String productoIdStr = input.nextLine().trim();

        System.out.print("Cantidad: ");
        Integer cantidad = Integer.parseInt(input.nextLine());

        System.out.print("Lote (opcional): ");
        String lote = input.nextLine().trim();

        Integer jornadaId = jornadaDeVacunacionController.obtenerJornadaPorNombre(nombreJornada).getId();
        if (jornadaId == null) {
            System.out.println("⚠️ No se encontró la jornada: " + nombreJornada);
            return;
        }

        Dueno dueno = duenoController.buscarDuenoPorDocumento(documento);
        if (dueno == null) {
            System.out.println("⚠️ No se encontró el dueño con documento: " + documento);
            return;
        }
        Integer duenoId = dueno.getId();

        Mascota mascota = mascotaControlller.buscarMascotaPorMicrochip(microchip);
        if (mascota == null) {
            System.out.println("⚠️ No se encontró la mascota con microchip: " + microchip);
            return;
        }
        Integer mascotaId = mascota.getId();

        Integer productoId = productoIdStr.isEmpty() ? null : Integer.parseInt(productoIdStr);

        JornadaDeVacunacionAsistencia asistencia = new JornadaDeVacunacionAsistencia();
        asistencia.setJornadaId(jornadaId);
        asistencia.setDuenoId(duenoId);
        asistencia.setMascotaId(mascotaId);
        asistencia.setProductoId(productoId);
        asistencia.setCantidad(cantidad);
        asistencia.setLote(lote);
        asistencia.setVacunado(true);
        asistencia.setEstado("asistio");

        controller.registrarAsistencia(asistencia);
        System.out.println("✅ Asistencia registrada con éxito. ID: " + asistencia.getId());
    }

    private void listarAsistencias() {
        System.out.print("\nNombre de la jornada: ");
        String nombreJornada = input.nextLine().trim();

        List<JornadaDeVacunacionAsistencia> lista = controller.listarAsistenciasPorJornada(nombreJornada);
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay asistencias registradas para esta jornada.");
            return;
        }

        System.out.println("\nID | Mascota | Dueño | Vacunado | Estado | Producto | Lote | Cantidad | Fecha | Observaciones");

        for (JornadaDeVacunacionAsistencia a : lista) {
            String lote = a.getLote() != null && !a.getLote().isEmpty() ? a.getLote() : "-";
            String observaciones = a.getObservaciones() != null && !a.getObservaciones().isEmpty() ? a.getObservaciones() : "-";
            String producto = a.getProductoId() != null ? a.getProductoId().toString() : "-";

            System.out.printf("%d | %s | %s | %s | %s | %s | %s | %d | %s | %s%n",
                    a.getId(),
                    a.getNombreMascota() != null ? a.getNombreMascota() : "-",
                    a.getNombreDueno() != null ? a.getNombreDueno() : "-",
                    a.getVacunado(),
                    a.getEstado(),
                    producto,
                    lote,
                    a.getCantidad(),
                    a.getFechaRegistro(),
                    observaciones
            );
        }
    }

    private void actualizarAsistencia() {
        System.out.print("Nombre de la jornada: ");
        String nombreJornada = input.nextLine().trim();

        List<JornadaDeVacunacionAsistencia> lista = controller.listarAsistenciasPorJornada(nombreJornada);
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay asistencias registradas para esta jornada.");
            return;
        }

        System.out.println("ID | Mascota | Dueño | Estado");
        for (JornadaDeVacunacionAsistencia a : lista) {
            System.out.printf("%d | %s | %s | %s%n",
                    a.getId(),
                    a.getNombreMascota(),
                    a.getNombreDueno(),
                    a.getEstado());
        }

        System.out.print("Ingrese el ID de la asistencia a actualizar: ");
        Integer id = Integer.parseInt(input.nextLine());

        JornadaDeVacunacionAsistencia asistencia = lista.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (asistencia == null) {
            System.out.println("⚠️ Asistencia no encontrada");
            return;
        }

        System.out.print("Vacunado (true/false) [" + asistencia.getVacunado() + "]: ");
        String vacunadoStr = input.nextLine();
        if (!vacunadoStr.isEmpty()) {
            asistencia.setVacunado(Boolean.parseBoolean(vacunadoStr));
        }

        System.out.print("Estado [" + asistencia.getEstado() + "]: ");
        String estadoStr = input.nextLine();
        if (!estadoStr.isEmpty()) {
            try {
                JornadaDeVacunacionAsistenciaEstado estadoEnum = JornadaDeVacunacionAsistenciaEstado.valueOf(estadoStr);
                asistencia.setEstado(estadoEnum.name().toLowerCase());
            } catch (IllegalArgumentException e){
                System.err.println("Error: " + e);
            }
        }

        System.out.print("Observaciones [" + (asistencia.getObservaciones() != null ? asistencia.getObservaciones() : "") + "]: ");
        String obsStr = input.nextLine();
        if (!obsStr.isEmpty()) {
            asistencia.setObservaciones(obsStr);
        }

        System.out.print("Producto (ID, opcional) [" + (asistencia.getProductoId() != null ? asistencia.getProductoId() : "") + "]: ");
        String productoIdStr = input.nextLine().trim();
        if (!productoIdStr.isEmpty()) {
            asistencia.setProductoId(Integer.parseInt(productoIdStr));
        } else {
            asistencia.setProductoId(null);
        }

        controller.actualizarAsistencia(asistencia);
        System.out.println("✅ Asistencia actualizada con éxito");
    }


    private void eliminarAsistencia() {
        System.out.print("\nNombre de la jornada: ");
        String nombreJornada = input.nextLine().trim();

        List<JornadaDeVacunacionAsistencia> lista = controller.listarAsistenciasPorJornada(nombreJornada);
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay asistencias registradas para esta jornada.");
            return;
        }

        System.out.println("ID | Mascota | Dueño | Estado");
        for (JornadaDeVacunacionAsistencia a : lista) {
            System.out.printf("%d | %s | %s | %s%n",
                    a.getId(),
                    a.getNombreMascota(),
                    a.getNombreDueno(),
                    a.getEstado());
        }

        System.out.print("Ingrese el ID de la asistencia a eliminar: ");
        Integer id = Integer.parseInt(input.nextLine());

        JornadaDeVacunacionAsistencia asistencia = lista.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (asistencia == null) {
            System.out.println("⚠️ Asistencia no encontrada");
            return;
        }

        controller.eliminarAsistencia(asistencia);
        System.out.println("✅ Asistencia eliminada con éxito");
    }

}
