package com.mycompany.proyectojava.repository.JornadaDeVacunacion;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.JornadasVacunacion.JornadasDeVacunacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JornadaDeVacunacionDAO implements IJornadaDeVacunacion {
    private Connection conexion;


    public JornadaDeVacunacionDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

    public void crearJornada(JornadasDeVacunacion jornada) {
        String sql = "INSERT INTO jornadas_vacunacion (nombre, fecha, ubicacion, notas, creado_por, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, jornada.getNombre());
            stmt.setDate(2, Date.valueOf(jornada.getFecha()));
            stmt.setString(3, jornada.getUbicacion());
            stmt.setString(4, jornada.getNotas());
            stmt.setString(5, jornada.getCreadoPor());
            stmt.setString(6, jornada.getEstado());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                jornada.setId(rs.getInt(1));
            }
        } catch (SQLException e){
            throw new RuntimeException("Error: " + e);
        }
    }

    // Listar
    public List<JornadasDeVacunacion> listarJornadas(String estadoFiltro) {
        List<JornadasDeVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM jornadas_vacunacion";
        if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
            sql += " WHERE estado = ?";
        }
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
                stmt.setString(1, estadoFiltro);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JornadasDeVacunacion j = new JornadasDeVacunacion();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setFecha(rs.getDate("fecha").toLocalDate());
                j.setUbicacion(rs.getString("ubicacion"));
                j.setNotas(rs.getString("notas"));
                j.setCreadoPor(rs.getString("creado_por"));
                j.setEstado(rs.getString("estado").toLowerCase());
                j.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                lista.add(j);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error: " + e);
        }
        return lista;
    }

    // Actualizar
    public void actualizarJornada(JornadasDeVacunacion jornada) {
        String sql = "UPDATE jornadas_vacunacion SET nombre=?, fecha=?, ubicacion=?, notas=?, estado=? WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, jornada.getNombre());
            stmt.setDate(2, Date.valueOf(jornada.getFecha()));
            stmt.setString(3, jornada.getUbicacion());
            stmt.setString(4, jornada.getNotas());
            stmt.setString(5, jornada.getEstado());
            stmt.setInt(6, jornada.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Eliminar
    public void eliminarJornada(Integer id) {
        String sql = "DELETE FROM jornadas_vacunacion WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error: " + e);
        }
    }

    @Override
    public JornadasDeVacunacion obtenerJornadaPorNombre(String nombre) {
        String sql = "SELECT * FROM jornadas_vacunacion WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                JornadasDeVacunacion j = new JornadasDeVacunacion();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setFecha(rs.getDate("fecha").toLocalDate());
                j.setUbicacion(rs.getString("ubicacion"));
                j.setNotas(rs.getString("notas"));
                j.setCreadoPor(rs.getString("creado_por"));
                j.setEstado(rs.getString("estado"));
                j.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return j;
            }
        }catch (SQLException e){
            throw new RuntimeException("Error: " + e);
        }
        return null;
    }
}
