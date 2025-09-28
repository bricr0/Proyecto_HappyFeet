package com.mycompany.proyectojava.repository.AlertasInventario;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.AlertasInventario.AlertasInventario;
import com.mycompany.proyectojava.model.entities.Inventario.Inventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertasInventarioDAO implements IAlertasInventario {
    private Connection connection;

    public AlertasInventarioDAO() {
        this.connection = ConexionDBSingleton.getInstance().getConnection();
    }

    // Registrar nueva alerta
    @Override
    public boolean registrarAlerta(AlertasInventario alerta) {
        String sql = "INSERT INTO alertas_inventario (inventario_id, tipo_alerta, mensaje, fecha_creacion, leido) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, alerta.getInventario_id().getId());
            stmt.setString(2, alerta.getTipo_alerta());
            stmt.setString(3, alerta.getMensaje());
            stmt.setTimestamp(4, new Timestamp(alerta.getFecha_creacion().getTime()));
            stmt.setBoolean(5, alerta.getLeido());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        alerta.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al registrar alerta: " + e.getMessage());
        }
        return false;
    }

    // Listar todas las alertas
    public List<AlertasInventario> listarAlertas() {
        List<AlertasInventario> alertas = new ArrayList<>();
        String sql = "SELECT ai.*, i.nombre_producto, i.tipo, i.cantidad_stock, i.stock_minimo " +
                "FROM alertas_inventario ai " +
                "INNER JOIN inventario i ON ai.inventario_id = i.id " +
                "ORDER BY ai.fecha_creacion DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AlertasInventario alerta = mapearResultSetAAlerta(rs);
                alertas.add(alerta);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al listar alertas: " + e.getMessage());
        }
        return alertas;
    }

    // Buscar alerta por ID
    @Override
    public AlertasInventario buscarAlertaPorId(Integer id) {
        String sql = "SELECT ai.*, i.nombre_producto, i.tipo, i.cantidad_stock, i.stock_minimo " +
                "FROM alertas_inventario ai " +
                "INNER JOIN inventario i ON ai.inventario_id = i.id " +
                "WHERE ai.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultSetAAlerta(rs);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar alerta: " + e.getMessage());
        }
        return null;
    }

    // Actualizar alerta (principalmente para marcar como leída)
    @Override
    public boolean actualizarAlerta(AlertasInventario alerta) {
        String sql = "UPDATE alertas_inventario SET leido = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, alerta.getLeido());
            stmt.setInt(2, alerta.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al actualizar alerta: " + e.getMessage());
        }
        return false;
    }

    // Eliminar alerta
    @Override
    public boolean eliminarAlerta(Integer id) {
        String sql = "DELETE FROM alertas_inventario WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al eliminar alerta: " + e.getMessage());
        }
        return false;
    }

    // Buscar alertas por tipo
    @Override
    public List<AlertasInventario> buscarAlertasPorTipo(String tipo) {
        List<AlertasInventario> alertas = new ArrayList<>();
        String sql = "SELECT ai.*, i.nombre_producto, i.tipo, i.cantidad_stock, i.stock_minimo " +
                "FROM alertas_inventario ai " +
                "INNER JOIN inventario i ON ai.inventario_id = i.id " +
                "WHERE ai.tipo_alerta = ? " +
                "ORDER BY ai.fecha_creacion DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alertas.add(mapearResultSetAAlerta(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar alertas por tipo: " + e.getMessage());
        }
        return alertas;
    }

    // Buscar alertas no leídas
    @Override
    public List<AlertasInventario> buscarAlertasNoLeidas() {
        List<AlertasInventario> alertas = new ArrayList<>();
        String sql = "SELECT ai.*, i.nombre_producto, i.tipo, i.cantidad_stock, i.stock_minimo " +
                "FROM alertas_inventario ai " +
                "INNER JOIN inventario i ON ai.inventario_id = i.id " +
                "WHERE ai.leido = false " +
                "ORDER BY ai.fecha_creacion DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alertas.add(mapearResultSetAAlerta(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar alertas no leídas: " + e.getMessage());
        }
        return alertas;
    }

    // Buscar alertas por producto
    @Override
    public List<AlertasInventario> buscarAlertasPorProducto(Integer inventarioId) {
        List<AlertasInventario> alertas = new ArrayList<>();
        String sql = "SELECT ai.*, i.nombre_producto, i.tipo, i.cantidad_stock, i.stock_minimo " +
                "FROM alertas_inventario ai " +
                "INNER JOIN inventario i ON ai.inventario_id = i.id " +
                "WHERE ai.inventario_id = ? " +
                "ORDER BY ai.fecha_creacion DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alertas.add(mapearResultSetAAlerta(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar alertas por producto: " + e.getMessage());
        }
        return alertas;
    }

    private AlertasInventario mapearResultSetAAlerta(ResultSet rs) throws SQLException {
        // Crear objeto Inventario básico para la relación
        Inventario inventario = new Inventario(
                rs.getInt("inventario_id"),
                rs.getString("nombre_producto"),
                rs.getString("tipo"),
                null, // fabricante
                rs.getInt("cantidad_stock"),
                rs.getInt("stock_minimo"),
                null, // fecha_vencimiento
                null, // precio_venta
                null, // lote
                null  // notas
        );

        return new AlertasInventario(
                rs.getInt("id"),
                inventario,
                rs.getString("tipo_alerta"),
                rs.getString("mensaje"),
                new Date(rs.getTimestamp("fecha_creacion").getTime()),
                rs.getBoolean("leido")
        );
    }
}
