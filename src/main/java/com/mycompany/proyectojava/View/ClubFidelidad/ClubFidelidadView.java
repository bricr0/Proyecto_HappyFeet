package com.mycompany.proyectojava.View.ClubFidelidad;

import com.mycompany.proyectojava.controller.ClubFidelidad.ClubFidelidadController;
import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.ClubFidelidad;
import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.PuntosMovimiento;
import com.mycompany.proyectojava.model.enums.ClubMascotasFrecuentes.PuntosTipo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ClubFidelidadView {

        private ClubFidelidadController controller;
        private Scanner input = new Scanner(System.in);

        public ClubFidelidadView(ClubFidelidadController controller) {
            this.controller = controller;
        }

        public void mostrarMenu() {
            int opcion = -1;
            do {
                System.out.println("\n--- Club de Mascotas Frecuentes ---");
                System.out.println("1. Listar clientes y puntos");
                System.out.println("2. Registrar movimiento de puntos");
                System.out.println("3. Canjear puntos");
                System.out.println("4. Ver movimientos de un cliente");
                System.out.println("5. Actualizar puntos manualmente");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                try {
                    opcion = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::listarClientes);
                funciones.put("2", this::registrarMovimiento);
                funciones.put("3", this::canjearPuntos);
                funciones.put("4", this::listarMovimientosCliente);
                funciones.put("5", this::actualizarPuntos);
                funciones.put("0", () -> System.out.println("👋 Saliendo..."));

                Runnable funcion = funciones.get(String.valueOf(opcion));
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("⚠️ Opción inválida");
                }

            } while (opcion != 0);
        }

        private void listarClientes() {
            List<ClubFidelidad> lista = controller.listarClubFidelidad();
            System.out.println("\nNombre | Documento | Puntos | Nivel | Actualizado");
            for (ClubFidelidad c : lista) {
                System.out.printf("%s | %s | %d | %s | %s%n",
                        c.getNombreDueno(),
                        c.getDocumentoDueno(),
                        c.getPuntosBalance(),
                        c.getNivel(),
                        c.getActualizado());
            }
        }

        private void registrarMovimiento() {
            PuntosMovimiento movimiento = new PuntosMovimiento();
            System.out.print("Documento del dueño: ");
            movimiento.setDocumentoDueno(input.nextLine());
            System.out.print("Cantidad de puntos: ");
            movimiento.setPuntos(Integer.parseInt(input.nextLine()));
            movimiento.setTipo(PuntosTipo.acumulacion);
            System.out.print("Descripción: ");
            movimiento.setDescripcion(input.nextLine());
            controller.registrarMovimiento(movimiento);
            System.out.println("✅ Movimiento registrado");
        }

        private void canjearPuntos() {
            System.out.print("Documento del dueño: ");
            String documento = input.nextLine();
            System.out.print("Cantidad de puntos a canjear: ");
            int puntos = Integer.parseInt(input.nextLine());
            System.out.print("Descripción: ");
            String descripcion = input.nextLine();
            boolean ok = controller.canjearPuntos(documento, puntos, descripcion);
            System.out.println(ok ? "✅ Canje realizado" : "⚠️ No hay suficientes puntos");
        }

        private void listarMovimientosCliente() {
            System.out.print("Documento del dueño: ");
            String documento = input.nextLine();
            List<PuntosMovimiento> movimientos = controller.listarMovimientosPorDocumento(documento);

            if (movimientos.isEmpty()) {
                System.out.println("⚠️ No hay movimientos registrados para este cliente.");
            } else {
                System.out.println("\nID | Puntos | Tipo | Descripción | Fecha | Nombre Dueño | Documento");
                for (PuntosMovimiento pm : movimientos) {
                    System.out.printf("%d | %d | %s | %s | %s | %s | %s%n",
                            pm.getId(),
                            pm.getPuntos(),
                            pm.getTipo(),
                            pm.getDescripcion(),
                            pm.getFecha(),
                            pm.getNombreDueno(),
                            pm.getDocumentoDueno());
                }
            }
        }

        private void actualizarPuntos() {
            ClubFidelidad club = new ClubFidelidad();
            System.out.print("Documento del dueño: ");
            club.setDocumentoDueno(input.nextLine());
            System.out.print("Nuevo saldo de puntos: ");
            club.setPuntosBalance(Integer.parseInt(input.nextLine()));
            controller.actualizarPuntos(club);
            System.out.println("✅ Puntos actualizados correctamente.");
        }
    }
