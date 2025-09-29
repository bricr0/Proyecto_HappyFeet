package com.mycompany.proyectojava.repository.ClubFidelidad;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.ClubFidelidad;
import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.PuntosMovimiento;
import com.mycompany.proyectojava.model.enums.ClubMascotasFrecuentes.ClubDeFidelidadNivel;
import com.mycompany.proyectojava.model.enums.ClubMascotasFrecuentes.PuntosTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubFidelidadDAO implements  IClubFidelidad{
    private Connection conexion;

    public ClubFidelidadDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

    @Override
    public List<ClubFidelidad> listarClubFidelidad() {
        List<ClubFidelidad> lista = new ArrayList<>();
        String sql = """
            SELECT d.nombre_completo AS nombre_dueno, d.documento_identidad AS documento_dueno, 
                   c.puntos_balance, c.nivel, c.actualizado
            FROM club_fidelidad c
            JOIN duenos d ON c.dueno_id = d.id
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ClubFidelidad c = new ClubFidelidad();
                c.setNombreDueno(rs.getString("nombre_dueno"));
                c.setDocumentoDueno(rs.getString("documento_dueno"));
                c.setPuntosBalance(rs.getInt("puntos_balance"));
                c.setNivel(ClubDeFidelidadNivel.valueOf(rs.getString("nivel").toLowerCase()));
                c.setActualizado(rs.getTimestamp("actualizado").toLocalDateTime());
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listar club fidelidad: " + e);
        }
        return lista;
    }

    @Override
    public ClubFidelidad obtenerMiembroPorDocumento(String documento) {
        String sql = """
            SELECT d.nombre_completo AS nombre_dueno, d.documento_identidad AS documento_dueno, 
                   c.puntos_balance, c.nivel, c.actualizado
            FROM club_fidelidad c
            JOIN duenos d ON c.dueno_id = d.id
            WHERE d.documento_identidad = ?
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ClubFidelidad c = new ClubFidelidad();
                c.setNombreDueno(rs.getString("nombre_dueno"));
                c.setDocumentoDueno(rs.getString("documento_dueno"));
                c.setPuntosBalance(rs.getInt("puntos_balance"));
                c.setNivel(ClubDeFidelidadNivel.valueOf(rs.getString("nivel").toLowerCase()));
                c.setActualizado(rs.getTimestamp("actualizado").toLocalDateTime());
                return c;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obtener miembro por documento: " + e);
        }
        return null;
    }

    @Override
    public void actualizarPuntos(ClubFidelidad clubFidelidad) {
        String sql = """
    UPDATE club_fidelidad c
    JOIN duenos d ON c.dueno_id = d.id
    SET c.puntos_balance = ?, c.actualizado = NOW()
    WHERE d.documento_identidad = ?
""";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, clubFidelidad.getPuntosBalance());
            stmt.setString(2, clubFidelidad.getDocumentoDueno());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("⚠️ No se encontró un cliente con documento " + clubFidelidad.getDocumentoDueno());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizar puntos del miembro: " + e);
        }
    }

    @Override
    public void registrarMovimiento(PuntosMovimiento movimiento) {
        String sql = """
            INSERT INTO puntos_movimientos (dueno_id, puntos, tipo, descripcion)
            SELECT id, ?, ?, ? FROM duenos WHERE documento_identidad = ?
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, movimiento.getPuntos());
            stmt.setString(2, movimiento.getTipo().name().toLowerCase());
            stmt.setString(3, movimiento.getDescripcion());
            stmt.setString(4, movimiento.getDocumentoDueno());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error registrar movimiento: " + e);
        }
    }

    @Override
    public List<PuntosMovimiento> listarMovimientosPorDocumento(String documento) {
        List<PuntosMovimiento> lista = new ArrayList<>();
        String sql = """
            SELECT pm.id, pm.puntos, pm.tipo, pm.descripcion, pm.fecha,
                   d.nombre_completo AS nombre_dueno, d.documento_identidad AS documento_dueno
            FROM puntos_movimientos pm
            JOIN duenos d ON pm.dueno_id = d.id
            WHERE d.documento_identidad = ?
            ORDER BY pm.fecha DESC
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PuntosMovimiento pm = new PuntosMovimiento();
                pm.setId(rs.getInt("id"));
                pm.setPuntos(rs.getInt("puntos"));
                pm.setTipo(PuntosTipo.valueOf(rs.getString("tipo").toLowerCase()));
                pm.setDescripcion(rs.getString("descripcion"));
                pm.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                pm.setNombreDueno(rs.getString("nombre_dueno"));
                pm.setDocumentoDueno(rs.getString("documento_dueno"));
                lista.add(pm);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listar movimientos: " + e);
        }

        return lista;
    }

    @Override
    public boolean canjearPuntos(String documentoDueno, int puntos, String descripcion) {
        String sqlDueno = "SELECT id FROM duenos WHERE documento_identidad = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sqlDueno)) {
            ps.setString(1, documentoDueno);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new RuntimeException("⚠️ No existe dueño con documento: " + documentoDueno);
                }
                int duenoId = rs.getInt("id");

                try (CallableStatement stmt = conexion.prepareCall("{CALL canjear_puntos(?, ?, ?, ?)}")) {
                    stmt.setInt(1, duenoId);
                    stmt.setInt(2, puntos);
                    stmt.setString(3, descripcion);
                    stmt.registerOutParameter(4, Types.BOOLEAN);

                    stmt.execute();
                    return stmt.getBoolean(4);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Error al canjear puntos: " + e.getMessage(), e);
        }
    }

}
