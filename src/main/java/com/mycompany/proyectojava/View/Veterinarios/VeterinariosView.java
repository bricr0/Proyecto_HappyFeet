package com.mycompany.proyectojava.View.Veterinarios;

import com.mycompany.proyectojava.controller.Veterinarios.VeterinariosController;
import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VeterinariosView {
    private final VeterinariosController controller;
    private final Scanner input;

    public VeterinariosView(VeterinariosController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE VETERINARIOS ---");
            System.out.println("""
                        1. Agregar veterinario
                        2. Listar veterinarios
                        3. Actualizar veterinario
                        4. Eliminar veterinario
                        5. Buscar veterinario por ID
                        6. Buscar veterinarios por nombre
                        0. Salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarVeterinario);
                funciones.put("2", this::listarVeterinarios);
                funciones.put("3", this::actualizarVeterinario);
                funciones.put("4", this::eliminarVeterinario);
                funciones.put("5", this::buscarVeterinarioPorId);
                funciones.put("6", this::buscarVeterinariosPorNombre);
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
            }
        }
    }

    private void agregarVeterinario() {
        System.out.println("\n\n ----- AGREGAR VETERINARIO -----\n");

        try {
            System.out.print("Nombre completo: ");
            String nombreCompleto = input.nextLine();

            System.out.print("Teléfono: ");
            String telefono = input.nextLine();

            System.out.print("Email: ");
            String email = input.nextLine();

            String resultado = controller.registrarVeterinario(nombreCompleto, telefono, email);
            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println("Error al agregar veterinario: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarVeterinarios() {
        System.out.println("\n\n ----- LISTA DE VETERINARIOS -----\n");
        List<Veterinarios> veterinarios = controller.obtenerTodosLosVeterinarios();

        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
        } else {
            for (Veterinarios vet : veterinarios) {
                System.out.println("ID: " + vet.getId() +
                        " | Nombre: " + vet.getNombre_completo() +
                        " | Tel: " + vet.getTelefono() +
                        " | Email: " + vet.getEmail());
            }
            System.out.println("\nTotal: " + veterinarios.size() + " veterinarios");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void actualizarVeterinario() {
        System.out.println("\n\n ----- ACTUALIZAR VETERINARIO -----\n");

        try {
            System.out.print("ID del veterinario a actualizar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Veterinarios veterinarioExistente = controller.obtenerVeterinario(id);
            if (veterinarioExistente == null) {
                System.out.println("❌ No se encontró el veterinario con ID: " + id);
                return;
            }

            System.out.println("Datos actuales:");
            System.out.println("Nombre: " + veterinarioExistente.getNombre_completo());
            System.out.println("Teléfono: " + veterinarioExistente.getTelefono());
            System.out.println("Email: " + veterinarioExistente.getEmail());

            System.out.print("\nNuevo nombre (dejar en blanco para mantener actual): ");
            String nuevoNombre = input.nextLine();
            if (nuevoNombre.trim().isEmpty()) {
                nuevoNombre = veterinarioExistente.getNombre_completo();
            }

            System.out.print("Nuevo teléfono (dejar en blanco para mantener actual): ");
            String nuevoTelefono = input.nextLine();
            if (nuevoTelefono.trim().isEmpty()) {
                nuevoTelefono = veterinarioExistente.getTelefono();
            }

            System.out.print("Nuevo email (dejar en blanco para mantener actual): ");
            String nuevoEmail = input.nextLine();
            if (nuevoEmail.trim().isEmpty()) {
                nuevoEmail = veterinarioExistente.getEmail();
            }

            String resultado = controller.actualizarVeterinario(id, nuevoNombre, nuevoTelefono, nuevoEmail);
            System.out.println(resultado);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void eliminarVeterinario() {
        System.out.println("\n\n ----- ELIMINAR VETERINARIO -----\n");

        try {
            System.out.print("ID del veterinario a eliminar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Veterinarios veterinario = controller.obtenerVeterinario(id);
            if (veterinario == null) {
                System.out.println("❌ No se encontró el veterinario con ID: " + id);
                return;
            }

            System.out.println("Veterinario a eliminar:");
            System.out.println("Nombre: " + veterinario.getNombre_completo());
            System.out.println("Teléfono: " + veterinario.getTelefono());
            System.out.println("Email: " + veterinario.getEmail());

            System.out.print("¿Está seguro de eliminar este veterinario? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.eliminarVeterinario(id);
                System.out.println(resultado);
            } else {
                System.out.println("Eliminación cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarVeterinarioPorId() {
        System.out.println("\n\n ----- BUSCAR VETERINARIO POR ID -----\n");

        try {
            System.out.print("ID del veterinario: ");
            Integer id = Integer.parseInt(input.nextLine());

            Veterinarios veterinario = controller.obtenerVeterinario(id);
            if (veterinario != null) {
                System.out.println("\n--- DATOS DEL VETERINARIO ---");
                System.out.println("ID: " + veterinario.getId());
                System.out.println("Nombre: " + veterinario.getNombre_completo());
                System.out.println("Teléfono: " + veterinario.getTelefono());
                System.out.println("Email: " + veterinario.getEmail());
            } else {
                System.out.println("❌ No se encontró el veterinario con ID: " + id);
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarVeterinariosPorNombre() {
        System.out.println("\n\n ----- BUSCAR VETERINARIOS POR NOMBRE -----\n");

        try {
            System.out.print("Nombre o parte del nombre: ");
            String nombre = input.nextLine();

            List<Veterinarios> veterinarios = controller.buscarVeterinariosPorNombre(nombre);

            if (veterinarios.isEmpty()) {
                System.out.println("No se encontraron veterinarios con: '" + nombre + "'");
            } else {
                System.out.println("Resultados de la búsqueda:");
                for (Veterinarios vet : veterinarios) {
                    System.out.println("ID: " + vet.getId() +
                            " | Nombre: " + vet.getNombre_completo() +
                            " | Tel: " + vet.getTelefono());
                }
                System.out.println("\nTotal encontrados: " + veterinarios.size());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }
}
