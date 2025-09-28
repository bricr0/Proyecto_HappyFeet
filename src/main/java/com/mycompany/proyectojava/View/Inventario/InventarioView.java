package com.mycompany.proyectojava.View.Inventario;

import com.mycompany.proyectojava.controller.Inventarios.InventarioController;
import com.mycompany.proyectojava.model.entities.Inventario.Inventario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventarioView {
    private final InventarioController controller;
    private final Scanner input;

    public InventarioView(InventarioController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void MostrarMenu(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println("\n --- GESTION DE INVENTARIO ---");
            System.out.println("""
                        1. Agregar producto
                        2. Listar productos
                        3. Actualizar producto
                        4. Eliminar producto
                        5. Buscar producto por ID
                        6. Buscar productos por nombre
                        7. Buscar productos por tipo
                        8. Productos con stock bajo
                        9. Productos próximos a vencer
                        10. Actualizar stock
                        0. Salir
                        >>> Elige una opcion:""");
            try {
                opcion = input.nextLine();

                Map<String, Runnable> funciones = new HashMap<>();
                funciones.put("1", this::agregarProducto);
                funciones.put("2", this::listarProductos);
                funciones.put("3", this::actualizarProducto);
                funciones.put("4", this::eliminarProducto);
                funciones.put("5", this::buscarProductoPorId);
                funciones.put("6", this::buscarProductosPorNombre);
                funciones.put("7", this::buscarProductosPorTipo);
                funciones.put("8", this::productosStockBajo);
                funciones.put("9", this::productosProximosAVencer);
                funciones.put("10", this::actualizarStock);
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

    private void agregarProducto() {
        System.out.println("\n\n ----- AGREGAR PRODUCTO AL INVENTARIO -----\n");

        try {
            System.out.print("Nombre del producto: ");
            String nombreProducto = input.nextLine();

            System.out.print("Tipo (ej: medicamento, vacuna, material, etc.): ");
            String tipo = input.nextLine();

            System.out.print("Fabricante: ");
            String fabricante = input.nextLine();

            System.out.print("Cantidad en stock: ");
            Integer cantidadStock = Integer.parseInt(input.nextLine());

            System.out.print("Stock mínimo: ");
            Integer stockMinimo = Integer.parseInt(input.nextLine());

            System.out.print("Fecha de vencimiento (yyyy-MM-dd o dejar vacío): ");
            String fechaVencimientoStr = input.nextLine();
            java.util.Date fechaVencimiento = null;
            if (!fechaVencimientoStr.trim().isEmpty()) {
                fechaVencimiento = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(fechaVencimientoStr);
            }

            System.out.print("Precio de venta: ");
            Double precioVenta = Double.parseDouble(input.nextLine());

            System.out.print("Número de lote: ");
            String lote = input.nextLine();

            System.out.print("Notas (opcional): ");
            String notas = input.nextLine();

            String resultado = controller.registrarProducto(nombreProducto, tipo, fabricante, cantidadStock,
                    stockMinimo, fechaVencimiento, precioVenta, lote, notas);
            System.out.println(resultado);

        } catch (java.text.ParseException e) {
            System.out.println("❌ Error: Formato de fecha inválido. Use yyyy-MM-dd");
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Formato de número inválido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void listarProductos() {
        System.out.println("\n\n ----- LISTA DE PRODUCTOS EN INVENTARIO -----\n");
        List<Inventario> productos = controller.obtenerTodosLosProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.println("ID | Nombre | Tipo | Stock | Precio | Vencimiento");
            System.out.println("-------------------------------------------------");
            for (Inventario prod : productos) {
                String fechaVencimiento = prod.getFecha_vencimiento() != null ?
                        new java.text.SimpleDateFormat("yyyy-MM-dd").format(prod.getFecha_vencimiento()) : "N/A";

                System.out.println(prod.getId() + " | " +
                        prod.getNombre_producto() + " | " +
                        prod.getTipo() + " | " +
                        prod.getCantidad_stock() + " | $" +
                        prod.getPrecio_venta() + " | " +
                        fechaVencimiento);
            }
            System.out.println("\nTotal: " + productos.size() + " productos");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void actualizarProducto() {
        System.out.println("\n\n ----- ACTUALIZAR PRODUCTO -----\n");

        try {
            System.out.print("ID del producto a actualizar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Inventario productoExistente = controller.obtenerProducto(id);
            if (productoExistente == null) {
                System.out.println("❌ No se encontró el producto con ID: " + id);
                return;
            }

            System.out.println("Datos actuales:");
            System.out.println("Nombre: " + productoExistente.getNombre_producto());
            System.out.println("Tipo: " + productoExistente.getTipo());
            System.out.println("Fabricante: " + productoExistente.getFabricante());
            System.out.println("Stock: " + productoExistente.getCantidad_stock());
            System.out.println("Stock mínimo: " + productoExistente.getStock_minimo());
            System.out.println("Precio: $" + productoExistente.getPrecio_venta());
            System.out.println("Lote: " + productoExistente.getLote());

            System.out.print("\nNuevo nombre (dejar en blanco para mantener actual): ");
            String nuevoNombre = input.nextLine();
            if (nuevoNombre.trim().isEmpty()) {
                nuevoNombre = productoExistente.getNombre_producto();
            }

            System.out.print("Nuevo tipo (dejar en blanco para mantener actual): ");
            String nuevoTipo = input.nextLine();
            if (nuevoTipo.trim().isEmpty()) {
                nuevoTipo = productoExistente.getTipo();
            }

            System.out.print("Nuevo fabricante (dejar en blanco para mantener actual): ");
            String nuevoFabricante = input.nextLine();
            if (nuevoFabricante.trim().isEmpty()) {
                nuevoFabricante = productoExistente.getFabricante();
            }

            System.out.print("Nueva cantidad stock (dejar en blanco para mantener actual): ");
            String nuevoStockStr = input.nextLine();
            Integer nuevoStock = nuevoStockStr.trim().isEmpty() ?
                    productoExistente.getCantidad_stock() : Integer.parseInt(nuevoStockStr);

            System.out.print("Nuevo stock mínimo (dejar en blanco para mantener actual): ");
            String nuevoStockMinStr = input.nextLine();
            Integer nuevoStockMin = nuevoStockMinStr.trim().isEmpty() ?
                    productoExistente.getStock_minimo() : Integer.parseInt(nuevoStockMinStr);

            System.out.print("Nuevo precio (dejar en blanco para mantener actual): ");
            String nuevoPrecioStr = input.nextLine();
            Double nuevoPrecio = nuevoPrecioStr.trim().isEmpty() ?
                    productoExistente.getPrecio_venta() : Double.parseDouble(nuevoPrecioStr);

            System.out.print("Nuevo lote (dejar en blanco para mantener actual): ");
            String nuevoLote = input.nextLine();
            if (nuevoLote.trim().isEmpty()) {
                nuevoLote = productoExistente.getLote();
            }

            System.out.print("Nuevas notas (dejar en blanco para mantener actual): ");
            String nuevasNotas = input.nextLine();
            if (nuevasNotas.trim().isEmpty()) {
                nuevasNotas = productoExistente.getNotas();
            }

            String resultado = controller.actualizarProducto(id, nuevoNombre, nuevoTipo, nuevoFabricante,
                    nuevoStock, nuevoStockMin, productoExistente.getFecha_vencimiento(),
                    nuevoPrecio, nuevoLote, nuevasNotas);
            System.out.println(resultado);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Formato de número inválido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void eliminarProducto() {
        System.out.println("\n\n ----- ELIMINAR PRODUCTO -----\n");

        try {
            System.out.print("ID del producto a eliminar: ");
            Integer id = Integer.parseInt(input.nextLine());

            Inventario producto = controller.obtenerProducto(id);
            if (producto == null) {
                System.out.println("❌ No se encontró el producto con ID: " + id);
                return;
            }

            System.out.println("Producto a eliminar:");
            System.out.println("Nombre: " + producto.getNombre_producto());
            System.out.println("Tipo: " + producto.getTipo());
            System.out.println("Stock actual: " + producto.getCantidad_stock());

            System.out.print("¿Está seguro de eliminar este producto? (s/n): ");
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                String resultado = controller.eliminarProducto(id);
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

    private void buscarProductoPorId() {
        System.out.println("\n\n ----- BUSCAR PRODUCTO POR ID -----\n");

        try {
            System.out.print("ID del producto: ");
            Integer id = Integer.parseInt(input.nextLine());

            Inventario producto = controller.obtenerProducto(id);
            if (producto != null) {
                System.out.println("\n--- DATOS DEL PRODUCTO ---");
                System.out.println("ID: " + producto.getId());
                System.out.println("Nombre: " + producto.getNombre_producto());
                System.out.println("Tipo: " + producto.getTipo());
                System.out.println("Fabricante: " + producto.getFabricante());
                System.out.println("Stock: " + producto.getCantidad_stock() + " (mínimo: " + producto.getStock_minimo() + ")");
                System.out.println("Precio: $" + producto.getPrecio_venta());
                System.out.println("Lote: " + producto.getLote());
                System.out.println("Notas: " + producto.getNotas());
                if (producto.getFecha_vencimiento() != null) {
                    System.out.println("Vencimiento: " + new java.text.SimpleDateFormat("yyyy-MM-dd").format(producto.getFecha_vencimiento()));
                }
            } else {
                System.out.println("❌ No se encontró el producto con ID: " + id);
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID debe ser un número válido");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarProductosPorNombre() {
        System.out.println("\n\n ----- BUSCAR PRODUCTOS POR NOMBRE -----\n");

        try {
            System.out.print("Nombre o parte del nombre: ");
            String nombre = input.nextLine();

            List<Inventario> productos = controller.buscarProductosPorNombre(nombre);

            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos con: '" + nombre + "'");
            } else {
                System.out.println("Resultados de la búsqueda:");
                for (Inventario prod : productos) {
                    System.out.println("ID: " + prod.getId() + " | " + prod.getNombre_producto() + " | " +
                            prod.getTipo() + " | Stock: " + prod.getCantidad_stock());
                }
                System.out.println("\nTotal encontrados: " + productos.size());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void buscarProductosPorTipo() {
        System.out.println("\n\n ----- BUSCAR PRODUCTOS POR TIPO -----\n");

        try {
            System.out.print("Tipo de producto: ");
            String tipo = input.nextLine();

            List<Inventario> productos = controller.buscarProductosPorTipo(tipo);

            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos del tipo: '" + tipo + "'");
            } else {
                System.out.println("Resultados de la búsqueda:");
                for (Inventario prod : productos) {
                    System.out.println("ID: " + prod.getId() + " | " + prod.getNombre_producto() + " | Stock: " + prod.getCantidad_stock());
                }
                System.out.println("\nTotal encontrados: " + productos.size());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void productosStockBajo() {
        System.out.println("\n\n ----- PRODUCTOS CON STOCK BAJO -----\n");

        try {
            List<Inventario> productos = controller.buscarProductosStockBajo();

            if (productos.isEmpty()) {
                System.out.println("✅ No hay productos con stock bajo");
            } else {
                System.out.println("⚠️  Productos que necesitan reposición:");
                for (Inventario prod : productos) {
                    System.out.println("• " + prod.getNombre_producto() + " - Stock: " + prod.getCantidad_stock() +
                            " (mínimo: " + prod.getStock_minimo() + ")");
                }
                System.out.println("\nTotal: " + productos.size() + " productos con stock bajo");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void productosProximosAVencer() {
        System.out.println("\n\n ----- PRODUCTOS PRÓXIMOS A VENCER -----\n");

        try {
            System.out.print("Días para considerar como 'próximo a vencer': ");
            int dias = Integer.parseInt(input.nextLine());

            List<Inventario> productos = controller.buscarProductosProximosAVencer(dias);

            if (productos.isEmpty()) {
                System.out.println("✅ No hay productos próximos a vencer en los próximos " + dias + " días");
            } else {
                System.out.println("⚠️  Productos próximos a vencer:");
                for (Inventario prod : productos) {
                    String fechaVencimiento = new java.text.SimpleDateFormat("yyyy-MM-dd").format(prod.getFecha_vencimiento());
                    System.out.println("• " + prod.getNombre_producto() + " - Vence: " + fechaVencimiento);
                }
                System.out.println("\nTotal: " + productos.size() + " productos próximos a vencer");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese un número válido de días");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }

    private void actualizarStock() {
        System.out.println("\n\n ----- ACTUALIZAR STOCK -----\n");

        try {
            System.out.print("ID del producto: ");
            Integer id = Integer.parseInt(input.nextLine());

            Inventario producto = controller.obtenerProducto(id);
            if (producto == null) {
                System.out.println("❌ No se encontró el producto con ID: " + id);
                return;
            }

            System.out.println("Producto: " + producto.getNombre_producto());
            System.out.println("Stock actual: " + producto.getCantidad_stock());

            System.out.print("Cantidad a agregar (use negativo para restar): ");
            Integer cantidad = Integer.parseInt(input.nextLine());

            String resultado = controller.actualizarStock(id, cantidad);
            System.out.println(resultado);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Formato de número inválido");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("Presione cualquier tecla para continuar...");
        input.nextLine();
    }
}