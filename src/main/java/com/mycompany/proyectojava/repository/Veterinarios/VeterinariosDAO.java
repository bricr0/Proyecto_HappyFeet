package com.mycompany.proyectojava.repository.Veterinarios;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinariosDAO implements IVeterinarios {
    private Connection connection;

    public VeterinariosDAO() {
        this.connection = ConexionDBSingleton.getInstance().getConnection();
    }

    // Registrar nuevo veterinario
    @Override
    public boolean registrarVeterinario(Veterinarios veterinario) {
        String sql = "INSERT INTO veterinarios (nombre_completo, telefono, email) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veterinario.getNombre_completo());
            stmt.setString(2, veterinario.getTelefono());
            stmt.setString(3, veterinario.getEmail());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        veterinario.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al registrar veterinario: " + e.getMessage());
        }
        return false;
    }

    // Listar todos los veterinarios
    @Override
    public List<Veterinarios> listarVeterinarios() {
        List<Veterinarios> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinarios ORDER BY nombre_completo";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veterinarios veterinario = new Veterinarios(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                veterinarios.add(veterinario);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al listar veterinarios: " + e.getMessage());
        }
        return veterinarios;
    }

    // Buscar veterinario por ID
    @Override
    public Veterinarios buscarVeterinarioPorId(Integer id) {
        String sql = "SELECT * FROM veterinarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veterinarios(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar veterinario: " + e.getMessage());
        }
        return null;
    }

    // Actualizar veterinario
    @Override
    public boolean actualizarVeterinario(Veterinarios veterinario) {
        String sql = "UPDATE veterinarios SET nombre_completo = ?, telefono = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre_completo());
            stmt.setString(2, veterinario.getTelefono());
            stmt.setString(3, veterinario.getEmail());
            stmt.setInt(4, veterinario.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al actualizar veterinario: " + e.getMessage());
        }
        return false;
    }

    // Eliminar veterinario
    @Override
    public boolean eliminarVeterinario(Integer id) {
        String sql = "DELETE FROM veterinarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al eliminar veterinario: " + e.getMessage());
        }
        return false;
    }

    // Buscar veterinario por nombre
    @Override
    public List<Veterinarios> buscarVeterinariosPorNombre(String nombre) {
        List<Veterinarios> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinarios WHERE nombre_completo LIKE ? ORDER BY nombre_completo";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veterinarios veterinario = new Veterinarios(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                veterinarios.add(veterinario);
            }
        } catch (SQLException e) {
            System.out.println("❌ DAO ERROR al buscar veterinarios por nombre: " + e.getMessage());
        }
        return veterinarios;
    }
}
