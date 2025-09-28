package com.mycompany.proyectojava.View.Adopcion;

import com.mycompany.proyectojava.Util.AdopcionContrato.ContratoTxtGenerator;
import com.mycompany.proyectojava.controller.Adopcion.AdopcionController;
import com.mycompany.proyectojava.model.entities.Adopcion.Adopcion;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEnum;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEstadoEnum;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdopcionView {
    private AdopcionController controller;
    private final MascotaDAO mascotaDAO;
    private final DuenoDAO duenoDAO;
    private Scanner input = new Scanner(System.in);

    public AdopcionView(AdopcionController controller, MascotaDAO mascotaDAO, DuenoDAO duenoDAO) {
        this.controller = controller;
        this.mascotaDAO = mascotaDAO;
        this.duenoDAO = duenoDAO;
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu() {
        Integer opcion = 0;
        do {
            System.out.println("\n--- Días de Adopción ---");
            System.out.println("1. Registrar adopción");
            System.out.println("2. Listar adopciones");
            System.out.println("3. Actualizar adopción");
            System.out.println("4. Eliminar adopción");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(input.nextLine());
            try {
                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", () -> {
                    try {
                        registrarAdopcion();
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al registrar adopción: " + e.getMessage());
                    }
                });

                funciones.put("2", () -> {
                    try {
                        listarAdopciones();
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al listar adopciones: " + e.getMessage());
                    }
                });

                funciones.put("3", () -> {
                    try {
                        actualizarAdopcion();
                    } catch (Exception e) {
                        throw new RuntimeException("Error al actualizar" + e);
                    }
                });

                funciones.put("4", () -> {
                    try {
                        eliminarAdopcion();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                funciones.put("0", () -> System.out.println("Saliendo..."));

                Runnable funcion = funciones.get(String.valueOf(opcion));
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("⚠️ Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error en el menu: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void registrarAdopcion() {
        System.out.println("---------- REGISTRAR ADOPCIÓN ----------");

        System.out.print("Microchip de la mascota: ");
        String microchip = input.nextLine().trim();

        System.out.print("Documento del adoptante: ");
        String documento = input.nextLine().trim();

        System.out.print("Tipo (ADOPCION/TEMPORAL): ");
        String tipoStr = input.nextLine().trim();

        Mascota mascota = mascotaDAO.listarPorMicrochip(microchip);
        Dueno adoptante = duenoDAO.listarPorDocumento(documento);

        if (mascota == null) {
            System.out.println("⚠️ No existe una mascota con ese microchip.");
            return;
        }

        if (adoptante == null) {
            System.out.println("⚠️ No existe un dueño con ese documento.");
            return;
        }

        AdopcionEnum tipo = AdopcionEnum.valueOf(tipoStr.toLowerCase());

        Adopcion adopcion = new Adopcion(
                mascota.getId(),
                adoptante.getId(),
                tipo,
                null
        );

        adopcion.setNombreAdoptante(adoptante.getNombre());
        adopcion.setNombreMascota(mascota.getNombre());
        adopcion.setEstado(AdopcionEstadoEnum.pendiente);
        adopcion.setFechaAdopcion(LocalDateTime.now());

        controller.registrarAdopcion(adopcion);

        System.out.println("✅ Adopción registrada con ID: " + adopcion.getId());
        ContratoTxtGenerator.generarContrato(adopcion);
    }

    private void listarAdopciones() throws Exception {
        List<Adopcion> lista = controller.listarAdopciones();
        for (Adopcion a : lista) {
            System.out.println(
                    a.getId() +
                            " | Mascota: " + a.getNombreMascota() +
                            " | Adoptante: " + a.getNombreAdoptante() +
                            " | Tipo: " + a.getTipo() +
                            " | Estado: " + a.getEstado()
            );        }
    }

    private void actualizarAdopcion() {
        System.out.print("Documento del dueno a actualizar: ");
        String documento = input.nextLine();
        Adopcion a = controller.buscarAdopcion(documento);
        if (a == null) {
            System.out.println("No encontrado");
            return;
        }
        System.out.print("Nuevo tipo (adopcion/temporal): ");
        a.setTipo(AdopcionEnum.valueOf(input.nextLine()));
        System.out.print("Nuevo estado (pendiente/completada/rechazada/cancelada): ");
        a.setEstado(AdopcionEstadoEnum.valueOf(input.nextLine()));

        controller.actualizarAdopcion(a);
        System.out.println("Adopción actualizada.");
    }

//    -------------------------------------------ELIMINAR ----------------------------------------

    private void eliminarAdopcion() throws Exception {
        System.out.print("Documento del dueno a eliminar su adopcion: ");
        String documento = input.nextLine();
        Adopcion a = controller.buscarAdopcion(documento);
        if (a == null) {
            System.out.println("No encontrado");
            return;
        }
        controller.eliminarAdopcion(documento);
        System.out.println("Adopción eliminada.");
    }
}
