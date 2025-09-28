package com.mycompany.proyectojava.service.InventarioService;

import com.mycompany.proyectojava.model.entities.Inventario.Inventario;
import com.mycompany.proyectojava.repository.Inventario.IInventario;

import java.util.List;

public class InventarioService {
    private IInventario inventarioDAO;

    public InventarioService(IInventario inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    public boolean registrarProducto(Inventario producto) {
        // Validaciones
        if (producto.getNombre_producto() == null || producto.getNombre_producto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if (producto.getTipo() == null || producto.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de producto es obligatorio");
        }

        if (producto.getCantidad_stock() == null || producto.getCantidad_stock() < 0) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser negativa");
        }

        if (producto.getStock_minimo() == null || producto.getStock_minimo() < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo");
        }

        if (producto.getPrecio_venta() == null || producto.getPrecio_venta() < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser negativo");
        }

        return inventarioDAO.registrarProducto(producto);
    }

    public List<Inventario> listarTodosLosProductos() {
        return inventarioDAO.listarProductos();
    }

    public Inventario obtenerProductoPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return inventarioDAO.buscarProductoPorId(id);
    }

    public boolean actualizarProducto(Inventario producto) {
        if (producto.getId() == null) {
            throw new IllegalArgumentException("El ID del producto es obligatorio para actualizar");
        }

        // Verificar que el producto exista
        Inventario existente = inventarioDAO.buscarProductoPorId(producto.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + producto.getId());
        }

        return inventarioDAO.actualizarProducto(producto);
    }

    public boolean eliminarProducto(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        // Verificar que el producto exista
        Inventario producto = inventarioDAO.buscarProductoPorId(id);
        if (producto == null) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + id);
        }

        return inventarioDAO.eliminarProducto(id);
    }

    public List<Inventario> buscarProductosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return listarTodosLosProductos();
        }
        return inventarioDAO.buscarProductosPorNombre(nombre.trim());
    }

    public List<Inventario> buscarProductosPorTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            return listarTodosLosProductos();
        }
        return inventarioDAO.buscarProductosPorTipo(tipo.trim());
    }

    public List<Inventario> buscarProductosStockBajo() {
        return inventarioDAO.buscarProductosStockBajo();
    }

    public List<Inventario> buscarProductosProximosAVencer(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Los días no pueden ser negativos");
        }
        return inventarioDAO.buscarProductosProximosAVencer(dias);
    }

    public boolean actualizarStock(Integer id, Integer cantidad) {
        Inventario producto = inventarioDAO.buscarProductoPorId(id);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }

        int nuevoStock = producto.getCantidad_stock() + cantidad;
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("No hay suficiente stock para esta operación");
        }

        producto.setCantidad_stock(nuevoStock);
        return inventarioDAO.actualizarProducto(producto);
    }
}