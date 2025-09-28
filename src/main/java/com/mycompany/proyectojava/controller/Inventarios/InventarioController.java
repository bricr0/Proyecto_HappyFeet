package com.mycompany.proyectojava.controller.Inventarios;

import com.mycompany.proyectojava.model.entities.Inventario.Inventario;
import com.mycompany.proyectojava.service.InventarioService.InventarioService;

import java.util.List;

public class InventarioController {
    private InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    public String registrarProducto(String nombreProducto, String tipo, String fabricante,
                                    Integer cantidadStock, Integer stockMinimo, java.util.Date fechaVencimiento,
                                    Double precioVenta, String lote, String notas) {
        try {
            Inventario nuevoProducto = new Inventario(null, nombreProducto, tipo, fabricante,
                    cantidadStock, stockMinimo, fechaVencimiento, precioVenta,
                    lote, notas);
            boolean resultado = inventarioService.registrarProducto(nuevoProducto);

            if (resultado) {
                return "✅ Producto registrado exitosamente con ID: " + nuevoProducto.getId();
            } else {
                return "❌ Error al registrar el producto";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Inventario> obtenerTodosLosProductos() {
        return inventarioService.listarTodosLosProductos();
    }

    public Inventario obtenerProducto(Integer id) {
        try {
            return inventarioService.obtenerProductoPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
            return null;
        }
    }

    public String actualizarProducto(Integer id, String nombreProducto, String tipo, String fabricante,
                                     Integer cantidadStock, Integer stockMinimo, java.util.Date fechaVencimiento,
                                     Double precioVenta, String lote, String notas) {
        try {
            Inventario producto = new Inventario(id, nombreProducto, tipo, fabricante,
                    cantidadStock, stockMinimo, fechaVencimiento, precioVenta,
                    lote, notas);
            boolean resultado = inventarioService.actualizarProducto(producto);

            if (resultado) {
                return "✅ Producto actualizado exitosamente";
            } else {
                return "❌ Error al actualizar el producto";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public String eliminarProducto(Integer id) {
        try {
            boolean resultado = inventarioService.eliminarProducto(id);

            if (resultado) {
                return "✅ Producto eliminado exitosamente";
            } else {
                return "❌ Error al eliminar el producto";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Inventario> buscarProductosPorNombre(String nombre) {
        try {
            return inventarioService.buscarProductosPorNombre(nombre);
        } catch (Exception e) {
            System.out.println("Error al buscar productos: " + e.getMessage());
            return List.of();
        }
    }

    public List<Inventario> buscarProductosPorTipo(String tipo) {
        try {
            return inventarioService.buscarProductosPorTipo(tipo);
        } catch (Exception e) {
            System.out.println("Error al buscar productos por tipo: " + e.getMessage());
            return List.of();
        }
    }

    public List<Inventario> buscarProductosStockBajo() {
        try {
            return inventarioService.buscarProductosStockBajo();
        } catch (Exception e) {
            System.out.println("Error al buscar productos con stock bajo: " + e.getMessage());
            return List.of();
        }
    }

    public List<Inventario> buscarProductosProximosAVencer(int dias) {
        try {
            return inventarioService.buscarProductosProximosAVencer(dias);
        } catch (Exception e) {
            System.out.println("Error al buscar productos próximos a vencer: " + e.getMessage());
            return List.of();
        }
    }

    public String actualizarStock(Integer id, Integer cantidad) {
        try {
            boolean resultado = inventarioService.actualizarStock(id, cantidad);

            if (resultado) {
                return "✅ Stock actualizado exitosamente";
            } else {
                return "❌ Error al actualizar el stock";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }
}