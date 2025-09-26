package com.mycompany.proyectojava.repository.Citas;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Citas.Citas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitasDAO implements ICitas {
    private Connection connection;

    public CitasDAO() {
        this.connection = ConexionDBSingleton.getInstance().getConnection();
    }

    // Registrar nueva cita
    @Override
    public boolean registrarCita(Citas cita) {
        String sql = "INSERT INTO citas (mascota_id, fecha_hora, estado_id, veterinario_id, motivo, observaciones) VALUES (?, ?, ?, ?, ?, ?)";


        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cita.getMascota_id());
            stmt.setTimestamp(2, new java.sql.Timestamp(cita.getFecha_hora().getTime()));
            stmt.setInt(3, cita.getEstado_id());
            stmt.setInt(4, cita.getVeterinario_id());
            stmt.setString(5, cita.getMotivo());
            stmt.setString(6, cita.getObservaciones());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        cita.setId(idGenerado);
                        return true;
                    }
                }
            }
            return false;

        } catch (Exception e) {
            System.out.println("❌ DAO ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Listar todas las citas
    @Override
    public List<Citas> listarCitas() {
        List<Citas> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas ORDER BY fecha_hora DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Citas cita = new Citas(
                        rs.getInt("mascota_id"),
                        new Date(rs.getTimestamp("fecha_hora").getTime()),
                        rs.getInt("estado_id"),
                        rs.getInt("veterinario_id"),
                        rs.getString("motivo"),
                        rs.getString("observaciones")
                );
                cita.setId(rs.getInt("id"));
                citas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public boolean actualizarCitas(Citas cita) {
        String sql = "UPDATE citas SET mascota_id = ?, fecha_hora = ?, estado_id = ?, veterinario_id = ?, motivo = ?, observaciones = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, cita.getMascota_id());
            stmt.setTimestamp(2, new java.sql.Timestamp(cita.getFecha_hora().getTime()));
            stmt.setInt(3, cita.getEstado_id());
            stmt.setInt(4, cita.getVeterinario_id());
            stmt.setString(5, cita.getMotivo());
            stmt.setString(6, cita.getObservaciones());
            stmt.setInt(7, cita.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("✅ Cita actualizada exitosamente");
                return true;
            } else {
                System.out.println("❌ No se encontró la cita con ID: " + cita.getId());
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERROR al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    // Eliminar cita por ID
    @Override
    public boolean eliminarCita(Integer id) {
        String sql = "DELETE FROM citas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Buscar cita por ID
    @Override
    public Citas buscarCitaPorId(Integer id) {
        String sql = "SELECT * FROM citas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Citas cita = new Citas(
                        rs.getInt("mascota_id"),
                        new Date(rs.getTimestamp("fecha_hora").getTime()),
                        rs.getInt("estado_id"),
                        rs.getInt("veterinario_id"),
                        rs.getString("motivo"),
                        rs.getString("observaciones")
                );
                cita.setId(rs.getInt("id"));
                return cita;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
