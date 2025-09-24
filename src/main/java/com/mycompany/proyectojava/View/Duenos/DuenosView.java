package com.mycompany.proyectojava.View.Duenos;

import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuenosView {
    private final DuenoController controller;
    private final Scanner input;

    public DuenosView(DuenoController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

//    ------------------------------------------------ MENU PRINCIPAL ------------------------------------------------

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE DUEÑOS ---");
            System.out.println("""
                        1. Agregar un dueño
                        2. Listar dueños
                        3. Actualizar un dueño
                        4. Eliminar un dueño
                        5. Ver mascotas de un dueño
                        0. salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarDueno);
                funciones.put("2", this::MenuListas);
                funciones.put("3", this::actualizarDueno);
                funciones.put("4", this::eliminarDueno);
                funciones.put("5", this::verMascotasDeUnDueno);
                funciones.put("0", () -> System.out.println("Saliendo..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e){
                System.out.println("Error: " + e);
                System.out.println("Presione cualquier letra");
                input.nextLine();
                opcion = "";
            }
        }
        input.close();
    }

//    --------------------------------------------------------SUBMENU LISTAR DUEÑOS ------------------------------------------------

    private void MenuListas(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- LISTAR DUEÑOS ---");
            System.out.println("""
                        1. Listar todos
                        2. Listar activos
                        3. Listar inactivos
                        4. Listar por documento
                        0. salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::listarTodosLosDuenos);
                funciones.put("2", this::listarDuenosActivos);
                funciones.put("3", this::listarDuenosInactivos);
                funciones.put("4", this::listarDuenoPorDocumento);
                funciones.put("0", () -> System.out.println("Saliendo..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e){
                System.out.println("Error: " + e);
                System.out.println("Presione cualquier letra");
                input.nextLine();
                opcion = "";
            }
        }
    }

//    ---------------------------------------------------------------- FUNCIONES ------------------------------------------------


//    -------------------------------------------------------------------- 1. AGREGAR DUEÑO ------------------------------------------------

    private void agregarDueno() {
        System.out.println("\n\n ----- 2. AGREGAR UN DUEÑO\n");

        System.out.print("Nombre: ");
        String nombre = input.nextLine();

        System.out.print("Documento: ");
        String documento = input.nextLine();

        System.out.print("Dirección: ");
        String direccion = input.nextLine();

        System.out.print("Teléfono: ");
        String telefono = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Contacto de emergencia (Opcional): ");
        String contacto_emergencia = input.nextLine();

        String estado = "activo";

        Dueno dueno = new Dueno(
                nombre,
                documento,
                direccion,
                telefono,
                email,
                estado,
                contacto_emergencia.isEmpty() ? null : contacto_emergencia
        );

        controller.agregarDueno(dueno);

        System.out.println("Dueño agregado con estado: " + estado);
    }

//    -------------------------------------------------------- 2. LISTAR DUEÑOS -------------------------------------------------

    private void listarTodosLosDuenos() {
        System.out.println("\n\n ----- DUEÑOS\n");
        controller.listarDuenos();
    }

//    --------------------------------------------------------- 3. LISTAR ACTIVOS -------------------------------------------------

    private void listarDuenosActivos() {
        System.out.println("\n\n ----- DUEÑOS ACTIVOS\n");
        controller.listarDuenosActivos();
    }

//    --------------------------------------------------------- 4. LISTAR INACTIVOS -------------------------------------------------

    private void listarDuenosInactivos() {
        System.out.println("\n\n ----- DUEÑOS INACTIVOS\n");
        controller.listarDuenosInactivos();
    }

//    --------------------------------------------------------- 5. LISTAR POR DOCUMENTO -------------------------------------------------
    private void listarDuenoPorDocumento() {
        System.out.println("\n\n ----- LISTAR DUEÑO POR DOCUMENTO\n");
        System.out.print("Ingrese el documento del dueño: ");
        String documento = input.nextLine();
        Dueno dueno = controller.buscarDuenoPorDocumento(documento);
        if (dueno != null) {
            System.out.println(dueno);
        } else {
            System.out.println("No se encontró ningún dueño con el documento: " + documento);
        }
    }

//    ------------------------------------------------------------- 6. ACTUALIZAR DUEÑO -------------------------------------------------

    private void actualizarDueno(){
        System.out.println("\n\n ----- ACTUALIZAR DUEÑO\n");
        System.out.print("Ingrese el documento del dueño a actualizar: ");
        String documento = input.nextLine();
        Dueno duenoExistente = controller.buscarDuenoPorDocumento(documento);
        if (duenoExistente == null) {
            System.out.println("No se encontró ningún dueño con el documento: " + documento);
            return;
        }

        System.out.println("Datos actuales del dueño: " + duenoExistente);

        System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
        String nombre = input.nextLine();
        if (!nombre.trim().isEmpty()) {
            duenoExistente.setNombre(nombre);
        }

        System.out.print("Nueva dirección (dejar en blanco para no cambiar): ");
        String direccion = input.nextLine();
        if (!direccion.trim().isEmpty()) {
            duenoExistente.setDireccion(direccion);
        }

        System.out.print("Nuevo teléfono (dejar en blanco para no cambiar): ");
        String telefono = input.nextLine();
        if (!telefono.trim().isEmpty()) {
            duenoExistente.setTelefono(telefono);
        }

        System.out.print("Nuevo email (dejar en blanco para no cambiar): ");
        String email = input.nextLine();
        if (!email.trim().isEmpty()) {
            duenoExistente.setEmail(email);
        }

        System.out.print("Nuevo contacto de emergencia (dejar en blanco para no cambiar): ");
        String contactoEmergencia = input.nextLine();
        if (!contactoEmergencia.trim().isEmpty()) {
            duenoExistente.setContacto_emergencia(contactoEmergencia);
        }

        System.out.print("Nuevo estado (activo/inactivo, dejar en blanco para no cambiar): ");
        String estado = input.nextLine();
        if (!estado.trim().isEmpty()) {
            duenoExistente.setEstado(estado);
        }

        controller.actualizarDueno(duenoExistente);
        System.out.println("Dueño actualizado con éxito.");
    }

//    ------------------------------------------------------------- 7. ELIMINAR DUEÑO -------------------------------------------------

    private void eliminarDueno(){
        System.out.println("\n\n ----- ELIMINAR DUEÑO\n");
        System.out.print("Ingrese el documento del dueño a eliminar: ");
        String documento = input.nextLine();
        Dueno duenoExistente = controller.buscarDuenoPorDocumento(documento);
        if (duenoExistente == null) {
            System.out.println("No se encontró ningún dueño con el documento: " + documento);
            return;
        }

        System.out.println("Datos del dueño a eliminar: " + duenoExistente);
        System.out.print("¿Está seguro que desea eliminar este dueño? (s/n): ");
        String confirmacion = input.nextLine();
        if (confirmacion.equalsIgnoreCase("s")) {
            controller.eliminarDueno(documento);
            System.out.println("Dueño eliminado con éxito.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

//    -------------------------------------------------------------- 8. VER MASCOTAS DE UN DUEÑO -------------------------------------------------

    private void verMascotasDeUnDueno(){
        System.out.println("\n\n ----- VER MASCOTAS DE UN DUEÑO\n");
        System.out.print("Ingrese el documento del dueño: ");
        String documento = input.nextLine();
        controller.verMascotasDeUnDueno(documento);
    }
}
