package com.mycompany.proyectojava.View.Consultas;

import com.mycompany.proyectojava.controller.Consultas.ConsultasController;
import com.mycompany.proyectojava.model.entities.Consultas.Consultas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsultasView {
    private final ConsultasController controller;
    private final Scanner input;

    public ConsultasView(ConsultasController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

//    ------------------------------------------------ MENU PRINCIPAL ------------------------------------------------

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE CONSULTAS ---");
            System.out.println("""
                        1. Agregar una consulta
                        2. Listar consultas
                        3. Actualizar una consulta
                        4. Eliminar una consulta
                        5. Buscar consulta por ID
                        0. Salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarConsulta);
                funciones.put("2", this::MenuListas);
                funciones.put("3", this::actualizarConsulta);
                funciones.put("4", this::eliminarConsulta);
                funciones.put("5", this::buscarConsultaPorId);
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

//    -------------------------------------------------------- SUBMENU LISTAR CONSULTAS ------------------------------------------------

    private void MenuListas(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- LISTAR CONSULTAS ---");
            System.out.println("""
                        1. Listar todas las consultas
                        2. Listar consultas por cita
                        3. Listar consultas por veterinario
                        0. Volver al menú principal
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::listarTodasLasConsultas);
                funciones.put("2", this::listarConsultasPorCita);
                funciones.put("3", this::listarConsultasPorVeterinario);
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

//    -------------------------------------------------------------------- 1. AGREGAR CONSULTA ------------------------------------------------

    private void agregarConsulta() {
        System.out.println("\n\n ----- AGREGAR UNA CONSULTA -----\n");

        try {
            System.out.print("ID de la cita: ");
            Integer cita_id = Integer.parseInt(input.nextLine());

            System.out.print("ID del veterinario: ");
            Integer veterinario_id = Integer.parseInt(input.nextLine());

            System.out.print("Diagnóstico: ");
            String diagnostico = input.nextLine();

            System.out.print("Tratamiento recomendado: ");
            String tratamiento_recomendado = input.nextLine();

            System.out.print("Procedimientos realizados: ");
            String procedimientos = input.nextLine();

            String resultado = controller.registrarConsulta(cita_id, veterinario_id, diagnostico,
                    tratamiento_recomendado, procedimientos, new java.util.Date());
            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println("Error al agregar la consulta: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

//    -------------------------------------------------------- 2. LISTAR CONSULTAS -------------------------------------------------

    private void listarTodasLasConsultas() {
        System.out.println("\n\n ----- TODAS LAS CONSULTAS -----\n");
        List<Consultas> consultas = controller.obtenerTodasLasConsultas();

        if (consultas.isEmpty()) {
            System.out.println("No hay consultas registradas.");
        } else {
            for (Consultas consulta : consultas) {
                System.out.println("ID: " + consulta.getId() +
                        " | Cita ID: " + consulta.getCita_id() +
                        " | Veterinario ID: " + consulta.getVeterinario_id() +
                        " | Fecha: " + consulta.getFecha_registro());
                System.out.println("Diagnóstico: " + truncarTexto(consulta.getDiagnostico(), 50));
                System.out.println("---");
            }
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarConsultasPorCita() {
        System.out.println("\n\n ----- CONSULTAS POR CITA -----\n");
        try {
            System.out.print("Ingrese el ID de la cita: ");
            Integer citaId = Integer.parseInt(input.nextLine());

            List<Consultas> consultas = controller.obtenerConsultasPorCita(citaId);

            if (consultas.isEmpty()) {
                System.out.println("No hay consultas para la cita con ID: " + citaId);
            } else {
                for (Consultas consulta : consultas) {
                    System.out.println("ID: " + consulta.getId() +
                            " | Veterinario ID: " + consulta.getVeterinario_id() +
                            " | Fecha: " + consulta.getFecha_registro());
                    System.out.println("Diagnóstico: " + truncarTexto(consulta.getDiagnostico(), 50));
                    System.out.println("Tratamiento: " + truncarTexto(consulta.getTratamiento_recomendado(), 50));
                    System.out.println("---");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarConsultasPorVeterinario() {
        System.out.println("\n\n ----- CONSULTAS POR VETERINARIO -----\n");
        try {
            System.out.print("Ingrese el ID del veterinario: ");
            Integer veterinarioId = Integer.parseInt(input.nextLine());

            List<Consultas> consultas = controller.obtenerConsultasPorVeterinario(veterinarioId);

            if (consultas.isEmpty()) {
                System.out.println("No hay consultas para el veterinario con ID: " + veterinarioId);
            } else {
                for (Consultas consulta : consultas) {
                    System.out.println("ID: " + consulta.getId() +
                            " | Cita ID: " + consulta.getCita_id() +
                            " | Fecha: " + consulta.getFecha_registro());
                    System.out.println("Diagnóstico: " + truncarTexto(consulta.getDiagnostico(), 50));
                    System.out.println("---");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

//    ------------------------------------------------------------- 3. ACTUALIZAR CONSULTA -------------------------------------------------

    private void actualizarConsulta(){
        System.out.println("\n\n ----- ACTUALIZAR CONSULTA -----\n");
        try {
            System.out.print("Ingrese el ID de la consulta a actualizar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Consultas consultaExistente = controller.obtenerConsulta(id);
            if (consultaExistente == null) {
                System.out.println("No se encontró ninguna consulta con el ID: " + id);
                return;
            }

            System.out.println("Datos actuales de la consulta:");
            System.out.println("Cita ID: " + consultaExistente.getCita_id());
            System.out.println("Veterinario ID: " + consultaExistente.getVeterinario_id());
            System.out.println("Fecha: " + consultaExistente.getFecha_registro());
            System.out.println("Diagnóstico: " + consultaExistente.getDiagnostico());
            System.out.println("Tratamiento: " + consultaExistente.getTratamiento_recomendado());
            System.out.println("Procedimientos: " + consultaExistente.getProcedimientos());

            System.out.print("\nNuevo diagnóstico (dejar en blanco para no cambiar): ");
            String diagnostico = input.nextLine();
            if (!diagnostico.trim().isEmpty()) {
                consultaExistente.setDiagnostico(diagnostico);
            }

            System.out.print("Nuevo tratamiento recomendado (dejar en blanco para no cambiar): ");
            String tratamiento = input.nextLine();
            if (!tratamiento.trim().isEmpty()) {
                consultaExistente.setTratamiento_recomendado(tratamiento);
            }

            System.out.print("Nuevos procedimientos (dejar en blanco para no cambiar): ");
            String procedimientos = input.nextLine();
            if (!procedimientos.trim().isEmpty()) {
                consultaExistente.setProcedimientos(procedimientos);
            }

            String resultado = controller.actualizarConsulta(id,
                    consultaExistente.getDiagnostico(),
                    consultaExistente.getTratamiento_recomendado(),
                    consultaExistente.getProcedimientos());

            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println("Error al actualizar la consulta: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

//    ------------------------------------------------------------- 4. ELIMINAR CONSULTA -------------------------------------------------

    private void eliminarConsulta(){
        System.out.println("\n\n ----- ELIMINAR CONSULTA -----\n");
        try {
            System.out.print("Ingrese el ID de la consulta a eliminar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Consultas consultaExistente = controller.obtenerConsulta(id);
            if (consultaExistente == null) {
                System.out.println("No se encontró ninguna consulta con el ID: " + id);
                return;
            }

            System.out.println("Datos de la consulta a eliminar:");
            System.out.println("ID: " + consultaExistente.getId());
            System.out.println("Cita ID: " + consultaExistente.getCita_id());
            System.out.println("Veterinario ID: " + consultaExistente.getVeterinario_id());
            System.out.println("Fecha: " + consultaExistente.getFecha_registro());
            System.out.println("Diagnóstico: " + truncarTexto(consultaExistente.getDiagnostico(), 50));

            System.out.print("¿Está seguro que desea eliminar esta consulta? (s/n): ");
            String confirmacion = input.nextLine();
            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.eliminarConsulta(id);
                System.out.println(resultado);
            } else {
                System.out.println("Eliminación cancelada.");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar la consulta: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

//    -------------------------------------------------------------- 5. BUSCAR CONSULTA POR ID -------------------------------------------------

    private void buscarConsultaPorId(){
        System.out.println("\n\n ----- BUSCAR CONSULTA POR ID -----\n");
        try {
            System.out.print("Ingrese el ID de la consulta: ");
            Integer id = Integer.parseInt(input.nextLine());

            Consultas consulta = controller.obtenerConsulta(id);
            if (consulta != null) {
                System.out.println("\n--- DATOS DE LA CONSULTA ---");
                System.out.println("ID: " + consulta.getId());
                System.out.println("Cita ID: " + consulta.getCita_id());
                System.out.println("Veterinario ID: " + consulta.getVeterinario_id());
                System.out.println("Fecha de registro: " + consulta.getFecha_registro());
                System.out.println("Diagnóstico: " + consulta.getDiagnostico());
                System.out.println("Tratamiento recomendado: " + consulta.getTratamiento_recomendado());
                System.out.println("Procedimientos: " + consulta.getProcedimientos());
            } else {
                System.out.println("No se encontró ninguna consulta con el ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nPresione cualquier tecla para continuar...");
        input.nextLine();
    }

    private String truncarTexto(String texto, int longitud) {
        if (texto == null) return "";
        return texto.length() > longitud ? texto.substring(0, longitud) + "..." : texto;
    }
}