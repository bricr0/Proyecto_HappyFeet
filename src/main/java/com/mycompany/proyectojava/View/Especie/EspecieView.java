package com.mycompany.proyectojava.View.Especie;

import com.mycompany.proyectojava.controller.Especie.EspecieController;
import com.mycompany.proyectojava.model.entities.Especie.Especie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EspecieView {
    private final EspecieController controller;
    private final Scanner input;

    public EspecieView(EspecieController controller) {
        this.controller = controller;
        this.input =  new Scanner(System.in);
    }

//    ---------------------------------------------------- MENU PRINCIPAL ------------------------------------------------

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE ESPECIES ---");
            System.out.println("""
                        1. Agregar una especie
                        2. Listar todas las especies
                        3, Buscar una especie
                        4. Actualizar una especie
                        5. Eliminar una especie
                        0. salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarEspecie);
                funciones.put("2", this::listarEspecies);
                funciones.put("3", this::buscarEspecie);
                funciones.put("4", this::actualizarEspecie);
                funciones.put("5", this::eliminarEspecie);
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


//    ----------------------------------------------------- FUNCIONES ----------------------------------------------

//    -----------------------------------------------------1. AGREGAR ESPECIE ------------------------------------------------

    public void agregarEspecie(){
        System.out.println("\n --- AGREGAR ESPECIE ---");
        System.out.print("Ingrese el nombre de la especie: ");
        String nombre = input.nextLine().trim();

        controller.agregarEspecie(nombre);

        System.out.println("Especie agregada con éxito.");
    }


//    -----------------------------------------------------2. LISTAR ESPECIES ------------------------------------------------

    public void listarEspecies(){
        System.out.println("\n --- LISTA DE ESPECIES ---");
        controller.listarEspecies();
    }

//    -----------------------------------------------------3. BUSCAR ESPECIE ------------------------------------------------

    public void buscarEspecie(){
        System.out.println("\n --- BUSCAR ESPECIE ---");
        System.out.print("Ingrese el nombre de la especie a buscar: ");
        String nombre = input.nextLine().trim();

        controller.buscarEspecie(nombre);
    }

//    ----------------------------------------------------4. ACTUALIZAR ESPECIE ------------------------------------------------

    public void actualizarEspecie(){
        System.out.println("\n --- ACTUALIZAR ESPECIE ---");
        System.out.print("Ingrese el nombre de la especie a actualizar: ");
        String nombre = input.nextLine().trim();
        Especie especieExistente = controller.buscarEspecie(nombre);
        if (especieExistente == null) {
            System.out.println("No se puede actualizar una especie que no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre de la especie: ");
        String nuevoNombre = input.nextLine().trim();
        especieExistente.setNombre(nuevoNombre);

        controller.actualizarEspecie(especieExistente);

    }

//    ----------------------------------------------------5. ELIMINAR ESPECIE ------------------------------------------------

    public void eliminarEspecie(){
        System.out.println("\n --- ELIMINAR ESPECIE ---");
        System.out.print("Ingrese el nombre de la especie a eliminar: ");
        String nombre = input.nextLine().trim();

        controller.eliminarEspecie(nombre);

        System.out.println("Especie eliminada con éxito.");
    }
}
