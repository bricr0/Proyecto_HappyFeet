package com.mycompany.proyectojava.repository.Inventario;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Inventario.Inventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO implements  IInventario {
    private Connection connection;

    public InventarioDAO() {
        this.connection = ConexionDBSingleton.getInstance().getConnection();
    }

    // Registrar nuevo producto en inventario
    @Override
    public boolean registrarProducto(Inventario producto) {
        String sql = "INSERT INTO inventario (nombre_producto, tipo, fabricante, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, lote, notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre_producto());
            stmt.setString(2, producto.getTipo());
            stmt.setString(3, producto.getFabricante());
            stmt.setInt(4, producto.getCantidad_stock());
            stmt.setInt(5, producto.getStock_minimo());

            if (producto.getFecha_vencimiento() != null) {
                stmt.setDate(6, new java.sql.Date(producto.getFecha_vencimiento().getTime()));
            } else {
                stmt.setNull(6, Types.DATE);
            }

            stmt.setDouble(7, producto.getPrecio_venta());
            stmt.setString(8, producto.getLote());
            stmt.setString(9, producto.getNotas());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al registrar producto: " + e.getMessage());
        }
        return false;
    }

    // Listar todos los productos
    @Override
    public List<Inventario> listarProductos() {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario ORDER BY nombre_producto";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventario producto = mapearResultSetAInventario(rs);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al listar productos: " + e.getMessage());
        }
        return productos;
    }

    // Buscar producto por ID
    @Override
    public Inventario buscarProductoPorId(Integer id) {
        String sql = "SELECT * FROM inventario WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultSetAInventario(rs);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar producto: " + e.getMessage());
        }
        return null;
    }

    // Actualizar producto
    @Override
    public boolean actualizarProducto(Inventario producto) {
        String sql = "UPDATE inventario SET nombre_producto = ?, tipo = ?, fabricante = ?, cantidad_stock = ?, stock_minimo = ?, fecha_vencimiento = ?, precio_venta = ?, lote = ?, notas = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre_producto());
            stmt.setString(2, producto.getTipo());
            stmt.setString(3, producto.getFabricante());
            stmt.setInt(4, producto.getCantidad_stock());
            stmt.setInt(5, producto.getStock_minimo());

            if (producto.getFecha_vencimiento() != null) {
                stmt.setDate(6, new java.sql.Date(producto.getFecha_vencimiento().getTime()));
            } else {
                stmt.setNull(6, Types.DATE);
            }

            stmt.setDouble(7, producto.getPrecio_venta());
            stmt.setString(8, producto.getLote());
            stmt.setString(9, producto.getNotas());
            stmt.setInt(10, producto.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al actualizar producto: " + e.getMessage());
        }
        return false;
    }

    // Eliminar producto
    @Override
    public boolean eliminarProducto(Integer id) {
        String sql = "DELETE FROM inventario WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al eliminar producto: " + e.getMessage());
        }
        return false;
    }

    // Buscar productos por nombre
    @Override
    public List<Inventario> buscarProductosPorNombre(String nombre) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE nombre_producto LIKE ? ORDER BY nombre_producto";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(mapearResultSetAInventario(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar productos por nombre: " + e.getMessage());
        }
        return productos;
    }

    // Buscar productos por tipo
    @Override
    public List<Inventario> buscarProductosPorTipo(String tipo) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE tipo LIKE ? ORDER BY nombre_producto";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + tipo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(mapearResultSetAInventario(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar productos por tipo: " + e.getMessage());
        }
        return productos;
    }

    // Productos con stock bajo
    @Override
    public List<Inventario> buscarProductosStockBajo() {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE cantidad_stock <= stock_minimo ORDER BY cantidad_stock ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearResultSetAInventario(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar productos con stock bajo: " + e.getMessage());
        }
        return productos;
    }

    // Productos próximos a vencer
    @Override
    public List<Inventario> buscarProductosProximosAVencer(int dias) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento IS NOT NULL AND fecha_vencimiento <= DATE_ADD(CURDATE(), INTERVAL ? DAY) ORDER BY fecha_vencimiento ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, dias);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(mapearResultSetAInventario(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar productos próximos a vencer: " + e.getMessage());
        }
        return productos;
    }
    private Inventario mapearResultSetAInventario(ResultSet rs) throws SQLException {
        java.util.Date fechaVencimiento = null;
        if (rs.getDate("fecha_vencimiento") != null) {
            fechaVencimiento = new java.util.Date(rs.getDate("fecha_vencimiento").getTime());
        }

        return new Inventario(
                rs.getInt("id"),
                rs.getString("nombre_producto"),
                rs.getString("tipo"),
                rs.getString("fabricante"),
                rs.getInt("cantidad_stock"),
                rs.getInt("stock_minimo"),
                fechaVencimiento,
                rs.getDouble("precio_venta"),
                rs.getString("lote"),
                rs.getString("notas")
        );
    }
}