package com.mycompany.proyectojava.View.AlertasInventario;

import com.mycompany.proyectojava.controller.AlertasInventario.AlertasInventarioController;
import com.mycompany.proyectojava.model.entities.AlertasInventario.AlertasInventario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlertasInventarioView {
    private final AlertasInventarioController controller;
    private final Scanner input;

    public AlertasInventarioView(AlertasInventarioController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            // Mostrar contador de alertas no leídas
            int alertasNoLeidas = controller.contarAlertasNoLeidas();
            String indicadorAlertas = alertasNoLeidas > 0 ? " ⚠️(" + alertasNoLeidas + ")" : "";

            System.out.println("\n --- GESTION DE ALERTAS DE INVENTARIO" + indicadorAlertas + " ---");
            System.out.println("""
                        1. Listar alertas
                        2. Alertas no leídas
                        3. Marcar alerta como leída
                        4. Buscar alertas por tipo
                        5. Buscar alertas por producto
                        6. Eliminar alerta
                        7. Generar alertas automáticas
                        0. Salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::listarAlertas);
                funciones.put("2", this::alertasNoLeidas);
                funciones.put("3", this::marcarAlertaComoLeida);
                funciones.put("4", this::buscarAlertasPorTipo);
                funciones.put("5", this::buscarAlertasPorProducto);
                funciones.put("6", this::eliminarAlerta);
                funciones.put("7", this::generarAlertasAutomaticas);
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

    private void listarAlertas() {
        System.out.println("\n\n ----- LISTA DE ALERTAS -----\n");
        List<AlertasInventario> alertas = controller.obtenerTodasLasAlertas();

        if (alertas.isEmpty()) {
            System.out.println("No hay alertas registradas.");
        } else {
            for (AlertasInventario alerta : alertas) {
                String estado = alerta.getLeido() ? "✅ LEÍDA" : "⚠️ NO LEÍDA";
                System.out.println("ID: " + alerta.getId() + " | " + estado);
                System.out.println("Producto: " + alerta.getInventario_id().getNombre_producto());
                System.out.println("Tipo: " + alerta.getTipo_alerta());
                System.out.println("Mensaje: " + alerta.getMensaje());
                System.out.println("Fecha: " + alerta.getFecha_creacion());
                System.out.println("---");
            }
            System.out.println("Total: " + alertas.size() + " alertas");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void alertasNoLeidas() {
        System.out.println("\n\n ----- ALERTAS NO LEÍDAS -----\n");
        List<AlertasInventario> alertas = controller.buscarAlertasNoLeidas();

        if (alertas.isEmpty()) {
            System.out.println("✅ No hay alertas no leídas.");
        } else {
            System.out.println("⚠️ Tienes " + alertas.size() + " alertas no leídas:");
            for (AlertasInventario alerta : alertas) {
                System.out.println("ID: " + alerta.getId() + " | Producto: " + alerta.getInventario_id().getNombre_producto());
                System.out.println("Tipo: " + alerta.getTipo_alerta() + " | " + alerta.getMensaje());
                System.out.println("Fecha: " + alerta.getFecha_creacion());
                System.out.println("---");
            }
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void marcarAlertaComoLeida() {
        System.out.println("\n\n ----- MARCAR ALERTA COMO LEÍDA -----\n");

        try {
            System.out.print("ID de la alerta a marcar como leída: ");
            Integer id = Integer.parseInt(input.nextLine());

            AlertasInventario alerta = controller.obtenerAlerta(id);
            if (alerta == null) {
                System.out.println("❌ No se encontró la alerta con ID: " + id);
                return;
            }

            if (alerta.getLeido()) {
                System.out.println("ℹ️  La alerta ya estaba marcada como leída");
                return;
            }

            System.out.println("Alerta a marcar como leída:");
            System.out.println("Producto: " + alerta.getInventario_id().getNombre_producto());
            System.out.println("Mensaje: " + alerta.getMensaje());

            System.out.print("¿Marcar como leída? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.marcarComoLeida(id);
                System.out.println(resultado);
            } else {
                System.out.println("Operación cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarAlertasPorTipo() {
        System.out.println("\n\n ----- BUSCAR ALERTAS POR TIPO -----\n");

        try {
            System.out.println("Tipos comunes: STOCK_BAJO, VENCIMIENTO, OTRO");
            System.out.print("Tipo de alerta: ");
            String tipo = input.nextLine();

            List<AlertasInventario> alertas = controller.buscarAlertasPorTipo(tipo);

            if (alertas.isEmpty()) {
                System.out.println("No se encontraron alertas del tipo: '" + tipo + "'");
            } else {
                System.out.println("Resultados de la búsqueda:");
                for (AlertasInventario alerta : alertas) {
                    String estado = alerta.getLeido() ? "LEÍDA" : "NO LEÍDA";
                    System.out.println("ID: " + alerta.getId() + " | " + estado + " | " +
                            alerta.getInventario_id().getNombre_producto() + " | " + alerta.getMensaje());
                }
                System.out.println("\nTotal encontradas: " + alertas.size());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarAlertasPorProducto() {
        System.out.println("\n\n ----- BUSCAR ALERTAS POR PRODUCTO -----\n");

        try {
            System.out.print("ID del producto: ");
            Integer productoId = Integer.parseInt(input.nextLine());

            List<AlertasInventario> alertas = controller.buscarAlertasPorProducto(productoId);

            if (alertas.isEmpty()) {
                System.out.println("No se encontraron alertas para el producto con ID: " + productoId);
            } else {
                System.out.println("Alertas del producto:");
                for (AlertasInventario alerta : alertas) {
                    String estado = alerta.getLeido() ? "LEÍDA" : "NO LEÍDA";
                    System.out.println("ID: " + alerta.getId() + " | " + estado + " | " +
                            alerta.getTipo_alerta() + " | " + alerta.getMensaje());
                }
                System.out.println("\nTotal encontradas: " + alertas.size());
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void eliminarAlerta() {
        System.out.println("\n\n ----- ELIMINAR ALERTA -----\n");

        try {
            System.out.print("ID de la alerta a eliminar: ");
            Integer id = Integer.parseInt(input.nextLine());

            AlertasInventario alerta = controller.obtenerAlerta(id);
            if (alerta == null) {
                System.out.println("❌ No se encontró la alerta con ID: " + id);
                return;
            }

            System.out.println("Alerta a eliminar:");
            System.out.println("Producto: " + alerta.getInventario_id().getNombre_producto());
            System.out.println("Tipo: " + alerta.getTipo_alerta());
            System.out.println("Mensaje: " + alerta.getMensaje());

            System.out.print("¿Está seguro de eliminar esta alerta? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.eliminarAlerta(id);
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

    private void generarAlertasAutomaticas() {
        System.out.println("\n\n ----- GENERAR ALERTAS AUTOMÁTICAS -----\n");

        try {
            System.out.println("Esta función revisaría el inventario y generaría alertas automáticamente");
            System.out.println("para stock bajo y productos próximos a vencer.");

            System.out.print("¿Generar alertas automáticas? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.generarAlertasAutomaticas();
                System.out.println(resultado);
            } else {
                System.out.println("Operación cancelada.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }
}
