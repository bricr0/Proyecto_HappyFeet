package com.mycompany.proyectojava.View.Razas;

import com.mycompany.proyectojava.controller.Especie.EspecieController;
import com.mycompany.proyectojava.controller.Razas.RazaController;
import com.mycompany.proyectojava.model.entities.Especie.Especie;
import com.mycompany.proyectojava.model.entities.Razas.Razas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RazasView {
    private final RazaController controller;
    private final EspecieController especieController;
    private final Scanner input;


    public RazasView(RazaController controller, EspecieController especieController) {
        this.controller = controller;
        this.especieController = especieController;
        this.input = new Scanner(System.in);;
    }

//    -------------------------------------------------------------- MENU PRINCIPAL --------------------------------------------------------------

    public void MostrarMenu() {
        String opcion = "";
        while (!opcion.equals("0")) {
            System.out.println("\n*********************** GESTIÓN DE RAZAS ***********************\n");
            System.out.println("""
                    1. Agregar una raza
                    2. Listar razas
                    3. Listar razas por especie
                    4. Buscar por nombre
                    5. Actualizar una raza
                    6. Eliminar una raza
                    0. salir
                    >>> Elige una opcion:""");

            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarRaza);
                funciones.put("2", this::listarRazas);
                funciones.put("3", this::listarPorEspecie);
                funciones.put("4", this::buscarPorNombre);
                funciones.put("5", this::actualizarRaza);
                funciones.put("6", this::eliminarRaza);
                funciones.put("0", () -> System.out.println("Saliendo del sistema..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Presione cualquier letra");
                input.nextLine();
                opcion = "";
            }
        }
    }

//    --------------------------------------------------------------- FUNCIONES --------------------------------------------------------------

//    ---------------------------------------------------------------1. AGREGAR RAZA--------------------------------------------------------------

    private void agregarRaza() {
        System.out.println("\n*********************** AGREGAR RAZA ***********************\n");
        System.out.print("Ingrese el nombre de la raza: ");
        String nombre = input.nextLine();

        System.out.print("Ingrese el nombre de la especie: ");
        String nombre_especie = input.nextLine();
        if (nombre_especie.isEmpty()) {
            System.out.println("El nombre de la especie no puede estar vacío.");
            return;
        }
        Especie especie_id = especieController.buscarEspecie(nombre_especie);

        if (especie_id == null){
            System.out.println("No se puede agregar una raza a una especie que no existe.");
            return;
        }

        Razas raza = new Razas(nombre, especie_id.getId());

        controller.agregarRaza(raza);

        System.out.println("✅ Raza agregada con éxito.");
    }


//    ---------------------------------------------------------------2. LISTAR RAZAS--------------------------------------------------------------

    private void listarRazas() {
        System.out.println("\n*********************** LISTA DE RAZAS ***********************\n");
        controller.listarRazas();
    }

//    ---------------------------------------------------------------3. LISTAR POR ESPECIE--------------------------------------------------------------

    private void listarPorEspecie() {
        System.out.println("\n*********************** LISTAR RAZAS POR ESPECIE ***********************\n");
        System.out.print("Ingrese el nombre de la especie: ");
        String nombre_especie = input.nextLine();

        Especie especie_id = especieController.buscarEspecie(nombre_especie);

        if (especie_id == null){
            System.out.println("No se puede listar una especie que no existe.");
            return;
        }

        List<Razas> raza = controller.listarPorEspecie(especie_id.getId());

        if (raza != null) {
            System.out.println("Raza encontrada: " + raza);
        } else {
            System.out.println("No se encontró ninguna raza con ese nombre.");
        }
    }


//    ---------------------------------------------------------------4. BUSCAR POR NOMBRE--------------------------------------------------------------

    private void buscarPorNombre() {
        System.out.println("\n*********************** BUSCAR RAZA POR NOMBRE ***********************\n");
        System.out.print("Ingrese el nombre de la raza: ");
        String nombre = input.nextLine().trim();

        Razas raza = controller.buscarPorNombre(nombre);
        if (raza != null) {
            System.out.println("Raza encontrada: " + raza);
        } else {
            System.out.println("No se encontró ninguna raza con ese nombre.");
        }
    }
//    ---------------------------------------------------------------5. ACTUALIZAR RAZA--------------------------------------------------------------

    private void actualizarRaza() {
        System.out.println("\n*********************** ACTUALIZAR RAZA ***********************\n");
        System.out.print("Ingrese el nombre de la raza a actualizar: ");
        String nombre = input.nextLine().trim();
        Razas razaExistente = controller.buscarPorNombre(nombre);
        if (razaExistente == null) {
            System.out.println("No se puede actualizar una raza que no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre de la raza: ");
        String nuevoNombre = input.nextLine().trim();
        razaExistente.setNombre(nuevoNombre);

        controller.actualizarRaza(razaExistente);

    }


//    ---------------------------------------------------------------6. ELIMINAR RAZA--------------------------------------------------------------

    private void eliminarRaza(){
        System.out.println("\n*********************** ELIMINAR RAZA ***********************\n");
        System.out.print("Ingrese el nombre de la raza a eliminar: ");
        String nombre = input.nextLine().trim();

        controller.eliminarRaza(nombre);

        System.out.println("Raza eliminada con éxito.");
    }

}