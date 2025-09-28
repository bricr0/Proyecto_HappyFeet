package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.AlertasInventario.AlertasInventarioView;
import com.mycompany.proyectojava.View.Inventario.InventarioView;
import com.mycompany.proyectojava.View.Proveedores.ProveedoresView;
import com.mycompany.proyectojava.controller.AlertasInventario.AlertasInventarioController;
import com.mycompany.proyectojava.controller.Inventarios.InventarioController;
import com.mycompany.proyectojava.controller.Proveedores.ProveedoresController;
import com.mycompany.proyectojava.repository.AlertasInventario.AlertasInventarioDAO;
import com.mycompany.proyectojava.repository.AlertasInventario.IAlertasInventario;
import com.mycompany.proyectojava.repository.Inventario.IInventario;
import com.mycompany.proyectojava.repository.Inventario.InventarioDAO;
import com.mycompany.proyectojava.repository.Proveedores.IProveedores;
import com.mycompany.proyectojava.repository.Proveedores.ProveedoresDAO;
import com.mycompany.proyectojava.service.InventarioService.Alertas.AlertasInventarioService;
import com.mycompany.proyectojava.service.InventarioService.InventarioService;
import com.mycompany.proyectojava.service.Proveedores.ProveedoresService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuGestionInventarios {
    private final Scanner input = new Scanner(System.in);

    public void MenuInventario() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTION DE INVENTARIO =====");
            System.out.println("1. Proveedores");
            System.out.println("2. Inventario");
            System.out.println("3. Alertas de Inventario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

                Map<String, Runnable> funciones = new HashMap<>();

                funciones.put("1", () -> {
                    // Configuración para Proveedores
                    IProveedores proveedoresDAO = new ProveedoresDAO();
                    ProveedoresService proveedoresService = new ProveedoresService(proveedoresDAO);
                    ProveedoresController proveedoresController = new ProveedoresController(proveedoresService);

                    ProveedoresView proveedoresView = new ProveedoresView(proveedoresController);
                    proveedoresView.MostrarMenu();
                });

                funciones.put("2", () -> {
                    // Configuración para Inventario
                    IInventario inventarioDAO = new InventarioDAO();
                    InventarioService inventarioService = new InventarioService(inventarioDAO);
                    InventarioController inventarioController = new InventarioController(inventarioService);

                    InventarioView inventarioView = new InventarioView(inventarioController);
                    inventarioView.MostrarMenu();
                });

                funciones.put("3", () -> {
                    // Configuración para Alertas de Inventario
                    IAlertasInventario alertasDAO = new AlertasInventarioDAO();
                    InventarioDAO inventarioDAO = new InventarioDAO();
                    AlertasInventarioService alertasService = new AlertasInventarioService(alertasDAO, inventarioDAO);
                    AlertasInventarioController alertasController = new AlertasInventarioController(alertasService);

                    AlertasInventarioView alertasView = new AlertasInventarioView(alertasController);
                    alertasView.MostrarMenu();
                });

                funciones.put("0", () -> System.out.println("Volviendo al menú principal..."));

                Runnable funcion = funciones.get(String.valueOf(opcion));
                if (funcion != null) {
                    funcion.run();
                } else {
                    System.out.println("❌ Opción inválida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida, intente de nuevo.");
            } catch (Exception e) {
                System.out.println("❌ Error al inicializar el módulo: " + e.getMessage());
                e.printStackTrace();
            }

        } while (opcion != 0);
    }
}