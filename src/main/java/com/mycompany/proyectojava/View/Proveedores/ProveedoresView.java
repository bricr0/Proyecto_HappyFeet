package com.mycompany.proyectojava.View.Proveedores;

import com.mycompany.proyectojava.controller.Proveedores.ProveedoresController;
import com.mycompany.proyectojava.model.entities.Proveedores.Proveedores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProveedoresView {
    private final ProveedoresController controller;
    private final Scanner input;

    public ProveedoresView(ProveedoresController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE PROVEEDORES ---");
            System.out.println("""
                        1. Agregar proveedor
                        2. Listar proveedores
                        3. Actualizar proveedor
                        4. Eliminar proveedor
                        5. Buscar proveedor por ID
                        6. Buscar proveedores por nombre
                        7. Buscar proveedores por contacto
                        0. Salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarProveedor);
                funciones.put("2", this::listarProveedores);
                funciones.put("3", this::actualizarProveedor);
                funciones.put("4", this::eliminarProveedor);
                funciones.put("5", this::buscarProveedorPorId);
                funciones.put("6", this::buscarProveedoresPorNombre);
                funciones.put("7", this::buscarProveedoresPorContacto);
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

    private void agregarProveedor() {
        System.out.println("\n\n ----- AGREGAR PROVEEDOR -----\n");

        try {
            System.out.print("Nombre del proveedor: ");
            String nombre = input.nextLine();

            System.out.print("Persona de contacto: ");
            String contacto = input.nextLine();

            System.out.print("Teléfono: ");
            String telefono = input.nextLine();

            System.out.print("Email (opcional): ");
            String email = input.nextLine();
            if (email.trim().isEmpty()) {
                email = null; // Permitir email nulo
            }

            String resultado = controller.registrarProveedor(nombre, contacto, telefono, email);
            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarProveedores() {
        System.out.println("\n\n ----- LISTA DE PROVEEDORES -----\n");
        List<Proveedores> proveedores = controller.obtenerTodosLosProveedores();

        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("ID | Nombre | Contacto | Teléfono | Email");
            System.out.println("-----------------------------------------");
            for (Proveedores prov : proveedores) {
                System.out.println(prov.getId() + " | " +
                        prov.getNombre() + " | " +
                        prov.getContacto() + " | " +
                        prov.getTelofono() + " | " +
                        (prov.getEmail() != null ? prov.getEmail() : "N/A"));
            }
            System.out.println("\nTotal: " + proveedores.size() + " proveedores");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void actualizarProveedor() {
        System.out.println("\n\n ----- ACTUALIZAR PROVEEDOR -----\n");

        try {
            System.out.print("ID del proveedor a actualizar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Proveedores proveedorExistente = controller.obtenerProveedor(id);
            if (proveedorExistente == null) {
                System.out.println("❌ No se encontró el proveedor con ID: " + id);
                return;
            }

            System.out.println("Datos actuales:");
            System.out.println("Nombre: " + proveedorExistente.getNombre());
            System.out.println("Contacto: " + proveedorExistente.getContacto());
            System.out.println("Teléfono: " + proveedorExistente.getTelofono());
            System.out.println("Email: " + (proveedorExistente.getEmail() != null ? proveedorExistente.getEmail() : "N/A"));

            System.out.print("\nNuevo nombre (dejar en blanco para mantener actual): ");
            String nuevoNombre = input.nextLine();
            if (nuevoNombre.trim().isEmpty()) {
                nuevoNombre = proveedorExistente.getNombre();
            }

            System.out.print("Nuevo contacto (dejar en blanco para mantener actual): ");
            String nuevoContacto = input.nextLine();
            if (nuevoContacto.trim().isEmpty()) {
                nuevoContacto = proveedorExistente.getContacto();
            }

            System.out.print("Nuevo teléfono (dejar en blanco para mantener actual): ");
            String nuevoTelefono = input.nextLine();
            if (nuevoTelefono.trim().isEmpty()) {
                nuevoTelefono = proveedorExistente.getTelofono();
            }

            System.out.print("Nuevo email (dejar en blanco para mantener actual): ");
            String nuevoEmail = input.nextLine();
            if (nuevoEmail.trim().isEmpty()) {
                nuevoEmail = proveedorExistente.getEmail();
            }

            String resultado = controller.actualizarProveedor(id, nuevoNombre, nuevoContacto, nuevoTelefono, nuevoEmail);
            System.out.println(resultado);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void eliminarProveedor() {
        System.out.println("\n\n ----- ELIMINAR PROVEEDOR -----\n");

        try {
            System.out.print("ID del proveedor a eliminar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Proveedores proveedor = controller.obtenerProveedor(id);
            if (proveedor == null) {
                System.out.println("❌ No se encontró el proveedor con ID: " + id);
                return;
            }

            System.out.println("Proveedor a eliminar:");
            System.out.println("Nombre: " + proveedor.getNombre());
            System.out.println("Contacto: " + proveedor.getContacto());
            System.out.println("Teléfono: " + proveedor.getTelofono());

            System.out.print("¿Está seguro de eliminar este proveedor? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.eliminarProveedor(id);
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

    private void buscarProveedorPorId() {
        System.out.println("\n\n ----- BUSCAR PROVEEDOR POR ID -----\n");

        try {
            System.out.print("ID del proveedor: ");
            Integer id = Integer.parseInt(input.nextLine());

            Proveedores proveedor = controller.obtenerProveedor(id);
            if (proveedor != null) {
                System.out.println("\n--- DATOS DEL PROVEEDOR ---");
                System.out.println("ID: " + proveedor.getId());
                System.out.println("Nombre: " + proveedor.getNombre());
                System.out.println("Contacto: " + proveedor.getContacto());
                System.out.println("Teléfono: " + proveedor.getTelofono());
                System.out.println("Email: " + (proveedor.getEmail() != null ? proveedor.getEmail() : "N/A"));
            } else {
                System.out.println("❌ No se encontró el proveedor con ID: " + id);
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarProveedoresPorNombre() {
        System.out.println("\n\n ----- BUSCAR PROVEEDORES POR NOMBRE -----\n");

        try {
            System.out.print("Nombre o parte del nombre: ");
            String nombre = input.nextLine();

            List<Proveedores> proveedores = controller.buscarProveedoresPorNombre(nombre);

            if (proveedores.isEmpty()) {
                System.out.println("No se encontraron proveedores con: '" + nombre + "'");
            } else {
                System.out.println("Resultados de la búsqueda:");
                for (Proveedores prov : proveedores) {
                    System.out.println("ID: " + prov.getId() +
                            " | Nombre: " + prov.getNombre() +
                            " | Contacto: " + prov.getContacto() +
                            " | Tel: " + prov.getTelofono());
                }
                System.out.println("\nTotal encontrados: " + proveedores.size());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarProveedoresPorContacto() {
        System.out.println("\n\n ----- BUSCAR PROVEEDORES POR CONTACTO -----\n");

        try {
            System.out.print("Nombre del contacto: ");
            String contacto = input.nextLine();

            List<Proveedores> proveedores = controller.buscarProveedoresPorContacto(contacto);

            if (proveedores.isEmpty()) {
                System.out.println("No se encontraron proveedores con contacto: '" + contacto + "'");
            } else {
                System.out.println("Resultados de la búsqueda:");
                for (Proveedores prov : proveedores) {
                    System.out.println("ID: " + prov.getId() +
                            " | Nombre: " + prov.getNombre() +
                            " | Contacto: " + prov.getContacto() +
                            " | Tel: " + prov.getTelofono());
                }
                System.out.println("\nTotal encontrados: " + proveedores.size());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }
}
