package com.mycompany.proyectojava.View.Citas;

import com.mycompany.proyectojava.controller.Citas.CitasController;
import com.mycompany.proyectojava.model.entities.Citas.Citas;

import java.util.*;

public class CitasView {
    private final CitasController controller;
    private final Scanner input;

    public CitasView(CitasController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

//    ------------------------------------------------ MENU PRINCIPAL ------------------------------------------------

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE CITAS ---");
            System.out.println("""
                        1. Agregar una cita
                        2. Listar citas
                        3. Actualizar una cita
                        4. Eliminar una cita
                        5. Buscar cita por ID
                        0. Salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarCita);
                funciones.put("2", this::MenuListas);
                funciones.put("3", this::actualizarCitas);
                funciones.put("4", this::eliminarCita);
                funciones.put("5", this::buscarCitaPorId);
                funciones.put("0", () -> System.out.println("Saliendo..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                System.out.println("Presione cualquier tecla para continuar...");
                input.nextLine();
                opcion = "";
            }
        }
    }

//    -------------------------------------------------------- SUBMENU LISTAR CITAS ------------------------------------------------

    private void MenuListas(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- LISTAR CITAS ---");
            System.out.println("""
                        1. Listar todas las citas
                        2. Listar citas por mascota
                        3. Listar citas por veterinario
                        4. Listar citas pendientes
                        0. Volver al menú principal
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::listarTodasLasCitas);
                funciones.put("2", this::listarCitasPorMascota);
                funciones.put("3", this::listarCitasPorVeterinario);
                funciones.put("4", this::listarCitasPendientes);
                funciones.put("0", () -> System.out.println("Volviendo al menú principal..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                System.out.println("Presione cualquier tecla para continuar...");
                input.nextLine();
                opcion = "";
            }
        }
    }

//    ---------------------------------------------------------------- FUNCIONES ------------------------------------------------

//    -------------------------------------------------------------------- 1. AGREGAR CITA ------------------------------------------------

    private void agregarCita() {
        System.out.println("\n\n ----- AGREGAR UNA CITA -----\n");

        try {
            System.out.print("ID de la mascota: ");
            Integer mascota_id = Integer.parseInt(input.nextLine());

            System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
            String fechaHoraStr = input.nextLine();
            java.util.Date fecha_hora = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaHoraStr);

            System.out.print("ID del estado (1-Pendiente, 2-Confirmada, 3-Completada, 4-Cancelada): ");
            Integer estado_id = Integer.parseInt(input.nextLine());

            System.out.print("ID del veterinario: ");
            Integer veterinario_id = Integer.parseInt(input.nextLine());

            System.out.print("Motivo de la cita: ");
            String motivo = input.nextLine();

            System.out.print("Observaciones (opcional): ");
            String observaciones = input.nextLine();

            String resultado = controller.registrarCita(mascota_id, fecha_hora, estado_id, veterinario_id, motivo, observaciones);

            System.out.println("Resultado : " + resultado);

        } catch (Exception e) {
            System.out.println("❌ ERROR en agregarCita(): " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

//    -------------------------------------------------------- 2. LISTAR CITAS -------------------------------------------------

    private void listarTodasLasCitas() {
        System.out.println("\n\n ----- TODAS LAS CITAS -----\n");
        List<Citas> citas = controller.obtenerTodasLasCitas();

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (Citas cita : citas) {
                System.out.println("ID: " + cita.getId() +
                        " | Mascota ID: " + cita.getMascota_id() +
                        " | Fecha: " + cita.getFecha_hora() +
                        " | Estado: " + cita.getEstado_id() +
                        " | Veterinario: " + cita.getVeterinario_id() +
                        " | Motivo: " + cita.getMotivo());
            }
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarCitasPorMascota() {
        System.out.println("\n\n ----- CITAS POR MASCOTA -----\n");
        try {
            System.out.print("Ingrese el ID de la mascota: ");
            Integer mascotaId = Integer.parseInt(input.nextLine());

            List<Citas> citas = controller.obtenerCitasPorMascota(mascotaId);

            if (citas.isEmpty()) {
                System.out.println("No hay citas para la mascota con ID: " + mascotaId);
            } else {
                for (Citas cita : citas) {
                    System.out.println("ID: " + cita.getId() +
                            " | Fecha: " + cita.getFecha_hora() +
                            " | Estado: " + cita.getEstado_id() +
                            " | Veterinario: " + cita.getVeterinario_id() +
                            " | Motivo: " + cita.getMotivo());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarCitasPorVeterinario() {
        System.out.println("\n\n ----- CITAS POR VETERINARIO -----\n");
        try {
            System.out.print("Ingrese el ID del veterinario: ");
            Integer veterinarioId = Integer.parseInt(input.nextLine());

            List<Citas> citas = controller.obtenerTodasLasCitas().stream()
                    .filter(c -> c.getVeterinario_id().equals(veterinarioId))
                    .toList();

            if (citas.isEmpty()) {
                System.out.println("No hay citas para el veterinario con ID: " + veterinarioId);
            } else {
                for (Citas cita : citas) {
                    System.out.println("ID: " + cita.getId() +
                            " | Mascota ID: " + cita.getMascota_id() +
                            " | Fecha: " + cita.getFecha_hora() +
                            " | Estado: " + cita.getEstado_id() +
                            " | Motivo: " + cita.getMotivo());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarCitasPendientes() {
        System.out.println("\n\n ----- CITAS PENDIENTES -----\n");
        try {
            List<Citas> citas = controller.obtenerTodasLasCitas().stream()
                    .filter(c -> c.getEstado_id().equals(1)) // Suponiendo que 1 es Pendiente
                    .toList();

            if (citas.isEmpty()) {
                System.out.println("No hay citas pendientes.");
            } else {
                for (Citas cita : citas) {
                    System.out.println("ID: " + cita.getId() +
                            " | Mascota ID: " + cita.getMascota_id() +
                            " | Fecha: " + cita.getFecha_hora() +
                            " | Veterinario: " + cita.getVeterinario_id() +
                            " | Motivo: " + cita.getMotivo());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

//    ------------------------------------------------------------- 3. ACTUALIZAR CITA -------------------------------------------------

    private void actualizarCitas(){
        System.out.println("\n\n ----- ACTUALIZAR CITA -----\n");

        try {
            System.out.print("Ingrese el ID de la cita a actualizar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Citas citaExistente = controller.obtenerCita(id);

            if (citaExistente == null) {
                System.out.println("❌ No se encontró ninguna cita con el ID: " + id);
                System.out.println("Presione cualquier tecla para continuar...");
                input.nextLine();
                return;
            }

            System.out.println("\n--- DATOS ACTUALES DE LA CITA ---");
            System.out.println("ID: " + citaExistente.getId());
            System.out.println("Mascota ID: " + citaExistente.getMascota_id());
            System.out.println("Fecha: " + citaExistente.getFecha_hora());
            System.out.println("Estado ID: " + citaExistente.getEstado_id());
            System.out.println("Veterinario ID: " + citaExistente.getVeterinario_id());
            System.out.println("Motivo: " + citaExistente.getMotivo());
            System.out.println("Observaciones: " + citaExistente.getObservaciones());

            System.out.println("\n--- INGRESE NUEVOS DATOS ---");
            System.out.println("(Deje en blanco para mantener el valor actual)");

            System.out.print("Nuevo ID de mascota (actual: " + citaExistente.getMascota_id() + "): ");
            String nuevoMascotaIdStr = input.nextLine();
            Integer nuevoMascotaId = nuevoMascotaIdStr.trim().isEmpty() ?
                    citaExistente.getMascota_id() : Integer.parseInt(nuevoMascotaIdStr);

            System.out.print("Nueva fecha y hora (yyyy-MM-dd HH:mm) (actual: " + citaExistente.getFecha_hora() + "): ");
            String nuevaFechaHoraStr = input.nextLine();
            java.util.Date nuevaFechaHora = nuevaFechaHoraStr.trim().isEmpty() ?
                    citaExistente.getFecha_hora() : new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(nuevaFechaHoraStr);

            System.out.print("Nuevo estado ID (actual: " + citaExistente.getEstado_id() + "): ");
            String nuevoEstadoIdStr = input.nextLine();
            Integer nuevoEstadoId = nuevoEstadoIdStr.trim().isEmpty() ?
                    citaExistente.getEstado_id() : Integer.parseInt(nuevoEstadoIdStr);

            System.out.print("Nuevo veterinario ID (actual: " + citaExistente.getVeterinario_id() + "): ");
            String nuevoVeterinarioIdStr = input.nextLine();
            Integer nuevoVeterinarioId = nuevoVeterinarioIdStr.trim().isEmpty() ?
                    citaExistente.getVeterinario_id() : Integer.parseInt(nuevoVeterinarioIdStr);

            System.out.print("Nuevo motivo (actual: " + citaExistente.getMotivo() + "): ");
            String nuevoMotivo = input.nextLine();
            if (nuevoMotivo.trim().isEmpty()) {
                nuevoMotivo = citaExistente.getMotivo();
            }

            System.out.print("Nuevas observaciones (actual: " + citaExistente.getObservaciones() + "): ");
            String nuevasObservaciones = input.nextLine();
            if (nuevasObservaciones.trim().isEmpty()) {
                nuevasObservaciones = citaExistente.getObservaciones();
            }

            System.out.println("\n--- RESUMEN DE CAMBIOS ---");
            System.out.println("Mascota ID: " + citaExistente.getMascota_id() + " → " + nuevoMascotaId);
            System.out.println("Fecha: " + citaExistente.getFecha_hora() + " → " + nuevaFechaHora);
            System.out.println("Estado: " + citaExistente.getEstado_id() + " → " + nuevoEstadoId);
            System.out.println("Veterinario: " + citaExistente.getVeterinario_id() + " → " + nuevoVeterinarioId);
            System.out.println("Motivo: " + citaExistente.getMotivo() + " → " + nuevoMotivo);

            System.out.print("\n¿Confirmar actualización? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.actualizarCitas(id, nuevoMascotaId, nuevaFechaHora,
                        nuevoEstadoId, nuevoVeterinarioId,
                        nuevoMotivo, nuevasObservaciones);
                System.out.println(resultado);
            } else {
                System.out.println("❌ Actualización cancelada por el usuario");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Formato de número inválido");
        } catch (java.text.ParseException e) {
            System.out.println("❌ Error: Formato de fecha inválido. Use: yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

//    ------------------------------------------------------------- 4. ELIMINAR CITA -------------------------------------------------

    private void eliminarCita(){
        System.out.println("\n\n ----- ELIMINAR CITA -----\n");
        try {
            System.out.print("Ingrese el ID de la cita a eliminar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Citas citaExistente = controller.obtenerCita(id);
            if (citaExistente == null) {
                System.out.println("No se encontró ninguna cita con el ID: " + id);
                return;
            }

            System.out.println("Datos de la cita a eliminar:");
            System.out.println("ID: " + citaExistente.getId());
            System.out.println("Mascota ID: " + citaExistente.getMascota_id());
            System.out.println("Fecha: " + citaExistente.getFecha_hora());
            System.out.println("Motivo: " + citaExistente.getMotivo());

            System.out.print("¿Está seguro que desea eliminar esta cita? (s/n): ");
            String confirmacion = input.nextLine();
            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.eliminarCita(id);
                System.out.println(resultado);
            } else {
                System.out.println("Eliminación cancelada.");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar la cita: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

//    -------------------------------------------------------------- 5. BUSCAR CITA POR ID -------------------------------------------------

    private void buscarCitaPorId(){
        System.out.println("\n\n ----- BUSCAR CITA POR ID -----\n");
        try {
            System.out.print("Ingrese el ID de la cita: ");
            Integer id = Integer.parseInt(input.nextLine());

            Citas cita = controller.obtenerCita(id);
            if (cita != null) {
                System.out.println("\n--- DATOS DE LA CITA ---");
                System.out.println("ID: " + cita.getId());
                System.out.println("Mascota ID: " + cita.getMascota_id());
                System.out.println("Fecha y hora: " + cita.getFecha_hora());
                System.out.println("Estado ID: " + cita.getEstado_id());
                System.out.println("Veterinario ID: " + cita.getVeterinario_id());
                System.out.println("Motivo: " + cita.getMotivo());
                System.out.println("Observaciones: " + cita.getObservaciones());
            } else {
                System.out.println("No se encontró ninguna cita con el ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }
}
