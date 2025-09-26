package com.mycompany.proyectojava.View.Mascota;

import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.controller.Especie.EspecieController;
import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.controller.Razas.RazaController;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Especie.Especie;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.model.entities.Razas.Razas;
import com.mycompany.proyectojava.repository.Especie.EspecieDAO;
import com.mycompany.proyectojava.repository.Especie.IEspecie;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MascotaView {
    private final MascotaControlller controller;
    private final DuenoController duenoController;
    private final RazaController razaController;
    private final EspecieController especieController;
    private final Scanner input;

    public MascotaView(MascotaControlller controller, DuenoController duenoController, RazaController razaController, EspecieController especieController) {
        this.controller = controller;
        this.duenoController = duenoController;
        this.razaController = razaController;
        this.especieController = especieController;
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu() {
        String opcion = "";
        while (!opcion.equals("0")) {
            System.out.println("\n --- GESTION DE MASCOTAS (CONSOLA) ---");
            System.out.println("""
                    1. Agregar una mascota.
                    2. Listar mascotas.
                    3. Actualizar una mascota.
                    4. Eliminar una mascota.
                    0. salir
                    >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarMascota);
                funciones.put("2", this::menuListasMascotas);
                funciones.put("3", this::actualizarMascota);
                funciones.put("4", this::eliminarMascota);
                funciones.put("0", () -> System.out.println("Gracias por elegirnos ..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("\n ❌ Error. Opción inválida. \nPresione cualquier tecla para continuar");
                    input.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Presione cualquier tecla para continuar");
                input.nextLine();
                opcion = "";
            }
        }
    }


//    --------------------------------------------------------SUBMENU LISTAR MASCOTAS ------------------------------------------------

    private void menuListasMascotas() {
        String opcion = "";
        while (!opcion.equals("0")) {
            System.out.println("\n --- LISTAR MASCOTAS ---");
            System.out.println("""
                    1. Listar todas
                    2. Listar activas
                    3. Listar inactivas
                    4. Listar por microchip
                    0. salir
                    >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::listarMascotas);
                funciones.put("2", this::listarMascotasActivas);
                funciones.put("3", this::listarMascotasInactivas);
                funciones.put("4", this::listarPorMicrochip);
                funciones.put("0", () -> System.out.println("Saliendo del submenú de mascotas..."));

                Runnable funcion = funciones.get(opcion);
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("❌ Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
                System.out.println("Presione cualquier tecla para continuar");
                input.nextLine();
                opcion = "";
            }
        }
    }

//    -----------------------------------------------------------1. AGREGAR MASCOTA ------------------------------------------------

    private void agregarMascota() {
        System.out.println("\n\n ----- 2. AGREGAR UNA MASCOTA\n");

        System.out.print("Nombre: ");
        String nombre = input.nextLine();

        System.out.print("Ingrese el documento del dueño: ");
        String documento = input.nextLine();
        Dueno dueno = duenoController.buscarDuenoPorDocumento(documento);
        if (dueno == null) {
            System.out.println("No se encontró ningún dueño con el documento: " + documento);
            return;
        }
        Integer idDueno = dueno.getId();
        System.out.println(idDueno);

        System.out.println("*********************Especies disponibles*********************");
        especieController.listarEspecies();
        System.out.print("Ingrese el nombre de la especie: ");
        String especieNombre = input.nextLine();

        IEspecie especieDao = new EspecieDAO();
        Especie especie = especieDao.listarPorNombre(especieNombre);
        if (especie == null) {
            System.out.println("No se encontró la especie: " + especieNombre);
            return;
        }

        List<Razas> razasDisponibles = razaController.listarPorEspecie(especie.getId());
        razasDisponibles.forEach(r -> System.out.println(r));

        System.out.print("Ingrese el nombre de la raza: ");
        String razaNombre = input.nextLine();

        Razas raza = razaController.buscarPorNombre(razaNombre);
        if (raza == null) {
            System.out.println("No se encontró la raza: " + razaNombre);
            return;
        }
        Integer razaId = raza.getId();

        System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
        String fechaStr = input.nextLine();
        Date fechaNacimiento = fechaStr.isEmpty() ? null : Date.valueOf(fechaStr);

        System.out.print("Sexo (Macho/Hembra): ");
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

        String estado = "activo";

        Mascota mascota = new Mascota(idDueno, nombre, razaId, fechaNacimiento, sexo,
                microchip, fotoUrl, alergias, condiciones, peso, notas, estado);

        controller.agregarMascota(mascota);

        System.out.println("✅ Mascota agregada con éxito.");
    }

//    ------------------------------------------------------------------------ 2. LISTAR MASCOTAS ------------------------------------------------

    private void listarMascotas() {
        System.out.println("\n\n ----- 2. LISTAR MASCOTAS\n");
        controller.listarMascotas();
        System.out.println("Presione cualquier tecla para continuar");
        input.nextLine();
    }

//    ------------------------------------------------------------------------ 2.1. LISTAR ACTIVAS ------------------------------------------------

    private void listarMascotasActivas() {
        System.out.println("\n\n ----- 2. LISTAR MASCOTAS ACTIVAS\n");
        controller.listarMascotasActivas();
        System.out.println("Presione cualquier tecla para continuar");
        input.nextLine();
    }
//    ------------------------------------------------------------------------ 2.2. LISTAR INACTIVAS ------------------------------------------------

    private void listarMascotasInactivas() {
        System.out.println("\n\n ----- 2. LISTAR MASCOTAS INACTIVAS\n");
        controller.listarMascotasInactivas();
        System.out.println("Presione cualquier tecla para continuar");
        input.nextLine();
    }

//    ------------------------------------------------------------------------ 2.3. LISTAR POR MICROCHIP ------------------------------------------------

    private void listarPorMicrochip() {
        System.out.println("\n\n ----- 2. LISTAR MASCOTA POR MICROCHIP\n");
        System.out.print("Ingrese el microchip de la mascota: ");
        String microchip = input.nextLine();
        Mascota mascota = controller.buscarMascotaPorMicrochip(microchip);
        if (mascota != null) {
            System.out.println(mascota);
        } else {
            System.out.println("No se encontró ninguna mascota con el microchip: " + microchip);
        }
        System.out.println("Presione cualquier tecla para continuar");
        input.nextLine();
    }

//    -------------------------------------------------------- 3. ACTUALIZAR MASCOTA ------------------------------------------------

    private void actualizarMascota() {
        System.out.println("\n\n ----- 3. ACTUALIZAR MASCOTA\n");

        System.out.print("Ingrese el microchip de la mascota a actualizar: ");
        String microchip = input.nextLine();
        Mascota mascotaExistente = controller.buscarMascotaPorMicrochip(microchip);
        if (mascotaExistente == null) {
            System.out.println("❌ No se encontró ninguna mascota con el microchip: " + microchip);
            return;
        }

        while (true) {
            System.out.print("Nuevo nombre (actual: " + mascotaExistente.getNombre() + "): ");
            String nombre = input.nextLine();
            if (nombre.trim().isEmpty()) break;
            if (nombre.trim().length() > 0) {
                mascotaExistente.setNombre(nombre);
                break;
            } else {
                System.out.println("❌ Nombre inválido, intente de nuevo.");
            }
        }

        while (true) {
            System.out.print("Nueva fecha de nacimiento (yyyy-mm-dd) (actual: " + mascotaExistente.getFecha_nacimiento() + "): ");
            String fechaStr = input.nextLine();
            if (fechaStr.trim().isEmpty()) break;
            try {
                Date fechaNacimiento = Date.valueOf(fechaStr);
                if (fechaNacimiento.after(new java.util.Date())) {
                    System.out.println("❌ La fecha no puede estar en el futuro.");
                } else {
                    mascotaExistente.setFecha_nacimiento(fechaNacimiento);
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Formato inválido. Use yyyy-mm-dd.");
            }
        }

        while (true) {
            System.out.print("Nuevo sexo (Macho/Hembra) (actual: " + mascotaExistente.getSexo() + "): ");
            String sexo = input.nextLine();
            if (sexo.trim().isEmpty()) break;
            if (sexo.equalsIgnoreCase("Macho") || sexo.equalsIgnoreCase("Hembra")) {
                mascotaExistente.setSexo(sexo);
                break;
            } else {
                System.out.println("❌ Sexo inválido, debe ser 'Macho' o 'Hembra'.");
            }
        }

        System.out.print("Nuevas alergias (actual: " + mascotaExistente.getAlergias() + "): ");
        String alergias = input.nextLine();
        if (!alergias.trim().isEmpty()) {
            mascotaExistente.setAlergias(alergias);
        }

        System.out.print("Nuevas condiciones preexistentes (actual: " + mascotaExistente.getCondiciones_preexistentes() + "): ");
        String condiciones = input.nextLine();
        if (!condiciones.trim().isEmpty()) {
            mascotaExistente.setCondiciones_preexistentes(condiciones);
        }

        while (true) {
            System.out.print("Nuevo peso (kg) (actual: " + mascotaExistente.getPeso_kg() + "): ");
            String pesoStr = input.nextLine();
            if (pesoStr.trim().isEmpty()) break;
            try {
                double peso = Double.parseDouble(pesoStr);
                if (peso > 0) {
                    mascotaExistente.setPeso_kg(peso);
                    break;
                } else {
                    System.out.println("❌ El peso debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Número inválido, intente de nuevo.");
            }
        }

        System.out.print("Nuevas notas médicas (actual: " + mascotaExistente.getNotas_medicas() + "): ");
        String notas = input.nextLine();
        if (!notas.trim().isEmpty()) {
            mascotaExistente.setNotas_medicas(notas);
        }

        controller.actualizarMascota(mascotaExistente);
        System.out.println("✅ Mascota actualizada con éxito.");

    }

//    -------------------------------------------------------- 4. ELIMINAR MASCOTA ------------------------------------------------

    private void eliminarMascota() {
        System.out.println("\n\n ----- 4. ELIMINAR MASCOTA\n");
        System.out.print("Ingrese el microchip de la mascota a eliminar: ");
        String microchip = input.nextLine();
        Mascota mascotaExistente = controller.buscarMascotaPorMicrochip(microchip);
        if (mascotaExistente == null) {
            System.out.println("No se encontró ninguna mascota con el microchip: " + microchip);
            return;
        }

        controller.eliminarMascota(microchip);
        System.out.println("✅ Mascota eliminada con éxito.");
    }

}



