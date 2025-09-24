package com.mycompany.proyectojava.View.Mascota;

import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;

import java.util.Scanner;

public class MascotaView {
    private final MascotaControlller controller;
    private final Scanner input;

    public MascotaView(MascotaControlller controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE MASCOTAS (CONSOLA) ---");
            System.out.println("""
                        1. Listar mascotas
                        2. Agregar una mascota
                        3. Actualizar una mascota
                        4. Eliminar una mascota
                        0. salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                switch (opcion) {
                    case "1":
                        break;
                    case "2":
                        agregarMascota();
                    case "3":
                        break;
                    case "4":
                        break;
                    case "0":
                        System.out.println("Gracias por elegirnos ...");
                        break;
                    default:
                        System.out.println("\n Error. Opcion invalida. \nPresione cualquier letra para continuar");
                        input.nextLine();
                }
            } catch (Exception e){
                System.out.println("Error: " + e);
                System.out.println("Presioe cualquier letra");
                input.nextLine();
                opcion = "";
            }
        }
        input.close();
    }

    private void agregarMascota() {
        System.out.println("\n\n ----- 2. AGREGAR UNA MASCOTA\n");

        System.out.print("Nombre: ");
        String nombre = input.nextLine();

        System.out.print("ID Dueño: ");
        Integer idDueno = Integer.parseInt(input.nextLine());

        System.out.print("ID Raza: ");
        Integer razaId = Integer.parseInt(input.nextLine());

        System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
        String fechaStr = input.nextLine();
        java.sql.Date fechaNacimiento = null;
        if (!fechaStr.isEmpty()) {
            fechaNacimiento = java.sql.Date.valueOf(fechaStr);
        }

        System.out.print("Sexo (M/F): ");
        String sexo = input.nextLine();

        System.out.print("Microchip: ");
        String microchip = input.nextLine();

        System.out.print("Foto URL: ");
        String fotoUrl = input.nextLine();

        System.out.print("Alergias: ");
        String alergias = input.nextLine();

        System.out.print("Condiciones preexistentes: ");
        String condiciones = input.nextLine();

        System.out.print("Peso (kg): ");
        Double peso = Double.parseDouble(input.nextLine());

        System.out.print("Notas médicas: ");
        String notas = input.nextLine();

        Mascota mascota = new Mascota(idDueno, nombre, razaId, fechaNacimiento, sexo,
                microchip, fotoUrl, alergias, condiciones, peso, notas);

        controller.agregarMascota(mascota);
    }


}
