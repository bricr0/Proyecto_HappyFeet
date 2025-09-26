package com.mycompany.proyectojava.repository.Consultas;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Consultas.Consultas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasDAO implements IConsultas{
    private Connection connection;

    public ConsultasDAO() {
        this.connection = ConexionDBSingleton.getInstance().getConnection();
    }

    // Registrar nueva consulta
    @Override
    public boolean registrarConsulta(Consultas consulta) {
        String sql = "INSERT INTO consultas (cita_id, veterinario_id, diagnostico, tratamiento_recomendado, procedimientos, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, consulta.getCita_id());
            stmt.setInt(2, consulta.getVeterinario_id());
            stmt.setString(3, consulta.getDiagnostico());
            stmt.setString(4, consulta.getTratamiento_recomendado());
            stmt.setString(5, consulta.getProcedimientos());
            stmt.setTimestamp(6, new Timestamp(consulta.getFecha_registro().getTime()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        consulta.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Listar todas las consultas
    @Override
    public List<Consultas> listarConsultas() {
        List<Consultas> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas ORDER BY fecha_registro DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consultas consulta = new Consultas(
                        rs.getInt("id"),
                        rs.getInt("cita_id"),
                        rs.getInt("veterinario_id"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento_recomendado"),
                        rs.getString("procedimientos"),
                        new Date(rs.getTimestamp("fecha_registro").getTime())
                );
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    // Eliminar consulta por ID
    @Override
    public boolean eliminarConsulta(Integer id) {
        String sql = "DELETE FROM consultas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Buscar consulta por ID
    @Override
    public Consultas buscarConsultaPorId(Integer id) {
        String sql = "SELECT * FROM consultas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Consultas(
                        rs.getInt("id"),
                        rs.getInt("cita_id"),
                        rs.getInt("veterinario_id"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento_recomendado"),
                        rs.getString("procedimientos"),
                        new Date(rs.getTimestamp("fecha_registro").getTime())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Buscar consultas por cita
    @Override
    public List<Consultas> buscarConsultasPorCita(Integer citaId) {
        List<Consultas> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas WHERE cita_id = ? ORDER BY fecha_registro DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consultas consulta = new Consultas(
                        rs.getInt("id"),
                        rs.getInt("cita_id"),
                        rs.getInt("veterinario_id"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento_recomendado"),
                        rs.getString("procedimientos"),
                        new Date(rs.getTimestamp("fecha_registro").getTime())
                );
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    // Buscar consultas por veterinario
    @Override
    public List<Consultas> buscarConsultasPorVeterinario(Integer veterinarioId) {
        List<Consultas> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas WHERE veterinario_id = ? ORDER BY fecha_registro DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, veterinarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consultas consulta = new Consultas(
                        rs.getInt("id"),
                        rs.getInt("cita_id"),
                        rs.getInt("veterinario_id"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento_recomendado"),
                        rs.getString("procedimientos"),
                        new Date(rs.getTimestamp("fecha_registro").getTime())
                );
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    // Actualizar consulta
    @Override
    public boolean actualizarConsulta(Consultas consulta) {
        String sql = "UPDATE consultas SET diagnostico = ?, tratamiento_recomendado = ?, procedimientos = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consulta.getDiagnostico());
            stmt.setString(2, consulta.getTratamiento_recomendado());
            stmt.setString(3, consulta.getProcedimientos());
            stmt.setInt(4, consulta.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
