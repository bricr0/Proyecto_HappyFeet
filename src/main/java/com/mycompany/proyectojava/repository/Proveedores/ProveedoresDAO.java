package com.mycompany.proyectojava.repository.Proveedores;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Proveedores.Proveedores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresDAO implements IProveedores {
    private Connection connection;

    public ProveedoresDAO() {
        this.connection = ConexionDBSingleton.getInstance().getConnection();
    }

    // Registrar nuevo proveedor
    @Override
    public boolean registrarProveedor(Proveedores proveedor) {
        String sql = "INSERT INTO proveedores (nombre, contacto, telofono, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            stmt.setString(3, proveedor.getTelofono());
            stmt.setString(4, proveedor.getEmail());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        proveedor.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al registrar proveedor: " + e.getMessage());
        }
        return false;
    }

    // Listar todos los proveedores
    @Override
    public List<Proveedores> listarProveedores() {
        List<Proveedores> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores ORDER BY nombre";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedores proveedor = new Proveedores(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telofono"),
                        rs.getString("email")
                );
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al listar proveedores: " + e.getMessage());
        }
        return proveedores;
    }

    // Buscar proveedor por ID
    @Override
    public Proveedores buscarProveedorPorId(Integer id) {
        String sql = "SELECT * FROM proveedores WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Proveedores(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telofono"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar proveedor: " + e.getMessage());
        }
        return null;
    }

    // Actualizar proveedor
    @Override
    public boolean actualizarProveedor(Proveedores proveedor) {
        String sql = "UPDATE proveedores SET nombre = ?, contacto = ?, telofono = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            stmt.setString(3, proveedor.getTelofono());
            stmt.setString(4, proveedor.getEmail());
            stmt.setInt(5, proveedor.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al actualizar proveedor: " + e.getMessage());
        }
        return false;
    }

    // Eliminar proveedor
    @Override
    public boolean eliminarProveedor(Integer id) {
        String sql = "DELETE FROM proveedores WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al eliminar proveedor: " + e.getMessage());
        }
        return false;
    }

    // Buscar proveedores por nombre
    @Override
    public List<Proveedores> buscarProveedoresPorNombre(String nombre) {
        List<Proveedores> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores WHERE nombre LIKE ? ORDER BY nombre";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Proveedores proveedor = new Proveedores(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telofono"),
                        rs.getString("email")
                );
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar proveedores por nombre: " + e.getMessage());
        }
        return proveedores;
    }

    // Buscar proveedores por contacto
    @Override
    public List<Proveedores> buscarProveedoresPorContacto(String contacto) {
        List<Proveedores> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores WHERE contacto LIKE ? ORDER BY nombre";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + contacto + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Proveedores proveedor = new Proveedores(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telofono"),
                        rs.getString("email")
                );
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar proveedores por contacto: " + e.getMessage());
        }
        return proveedores;
    }
}