package com.mycompany.proyectojava.repository.JornadaDeVacunacionAsistencia;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.JornadaDeVacunacionAsistencia.JornadaDeVacunacionAsistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JornadaDeVacunacionAsisteciaDAO implements IJornadaDeVacunacionAsistencia{
    private Connection conexion;

    public JornadaDeVacunacionAsisteciaDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

    @Override
    public void registrarAsistenncia(JornadaDeVacunacionAsistencia jornadaDeVacunacionAsistencia) {
        String sql = "{CALL registrar_asistencia_vacunacion(?,?,?,?,?, ?, ?)}";
        try (CallableStatement stmt = conexion.prepareCall(sql)) {
            stmt.setInt(1, jornadaDeVacunacionAsistencia.getJornadaId());
            stmt.setInt(2, jornadaDeVacunacionAsistencia.getMascotaId());
            if (jornadaDeVacunacionAsistencia.getProductoId() != null) {
                stmt.setInt(3, jornadaDeVacunacionAsistencia.getProductoId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setInt(4, jornadaDeVacunacionAsistencia.getCantidad());
            if (jornadaDeVacunacionAsistencia.getLote() != null && !jornadaDeVacunacionAsistencia.getLote().isEmpty()) {
                stmt.setString(5, jornadaDeVacunacionAsistencia.getLote());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }
            stmt.setInt(6, jornadaDeVacunacionAsistencia.getDuenoId());
            stmt.registerOutParameter(7, Types.INTEGER);
            stmt.execute();
            jornadaDeVacunacionAsistencia.setId(stmt.getInt(7));
        } catch (SQLException e){
            throw new RuntimeException("Error: " + e);
        }
    }



    @Override
    public List<JornadaDeVacunacionAsistencia> listarJornadaAsistencia(String nombreJornada) {
        List<JornadaDeVacunacionAsistencia> lista = new ArrayList<>();

        try {
            String sqlJornada = "SELECT id FROM jornadas_vacunacion WHERE nombre = ?";
            int jornadaId;
            try (PreparedStatement stmtJ = conexion.prepareStatement(sqlJornada)) {
                stmtJ.setString(1, nombreJornada);
                ResultSet rsJ = stmtJ.executeQuery();
                if (rsJ.next()) {
                    jornadaId = rsJ.getInt("id");
                } else {
                    throw new RuntimeException("⚠️ Jornada no encontrada: " + nombreJornada);
                }
            }

            String sql = """
                SELECT ja.*, 
                       jv.nombre AS nombre_jornada,
                       m.nombre AS nombre_mascota,
                       d.nombre_completo AS nombre_dueno
                FROM jornada_asistencias ja
                JOIN jornadas_vacunacion jv ON ja.jornada_id = jv.id
                JOIN mascotas m ON ja.mascota_id = m.id
                JOIN duenos d ON ja.dueno_id = d.id
                WHERE ja.jornada_id = ?
        """;

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setInt(1, jornadaId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    JornadaDeVacunacionAsistencia a = new JornadaDeVacunacionAsistencia();
                    a.setId(rs.getInt("id"));
                    a.setJornadaId(rs.getInt("jornada_id"));
                    a.setNombreJornada(rs.getString("nombre_jornada"));
                    a.setNombreMascota(rs.getString("nombre_mascota"));
                    a.setNombreDueno(rs.getString("nombre_dueno"));
                    a.setVacunado(rs.getBoolean("vacunado"));
                    a.setProductoId(rs.getInt("producto_id"));
                    a.setLote(rs.getString("lote"));
                    a.setCantidad(rs.getInt("cantidad"));
                    a.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                    a.setEstado(rs.getString("estado"));
                    a.setObservaciones(rs.getString("observaciones"));
                    lista.add(a);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar asistencias: " + e);
        }

        return lista;
    }


    @Override
    public void actualizarJornadaAsistecia(JornadaDeVacunacionAsistencia asistencia) {
        String sql = """
        UPDATE jornada_asistencias
        SET vacunado = ?, producto_id = ?, lote = ?, cantidad = ?, estado = ?, observaciones = ?
        WHERE id = ?
    """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setBoolean(1, asistencia.getVacunado());

            if (asistencia.getProductoId() != null) {
                String checkProducto = "SELECT id FROM inventario WHERE id = ?";
                try (PreparedStatement stmtCheck = conexion.prepareStatement(checkProducto)) {
                    stmtCheck.setInt(1, asistencia.getProductoId());
                    ResultSet rs = stmtCheck.executeQuery();
                    if (!rs.next()) {
                        throw new RuntimeException("⚠️ El producto con ID " + asistencia.getProductoId() + " no existe en inventario.");
                    }
                }
                stmt.setInt(2, asistencia.getProductoId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            if (asistencia.getLote() != null && !asistencia.getLote().isEmpty()) {
                stmt.setString(3, asistencia.getLote());
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            stmt.setInt(4, asistencia.getCantidad());

            stmt.setString(5, asistencia.getEstado());

            if (asistencia.getObservaciones() != null && !asistencia.getObservaciones().isEmpty()) {
                stmt.setString(6, asistencia.getObservaciones());
            } else {
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.setInt(7, asistencia.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("⚠️ No se encontró la asistencia con ID " + asistencia.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar asistencia: " + e);
        }
    }



    @Override
    public void eliminarJornadaAsistencia(JornadaDeVacunacionAsistencia jornadaDeVacunacionAsistencia) {
        String sql = "DELETE FROM jornada_asistencias WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, jornadaDeVacunacionAsistencia.getId());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("⚠️ No se encontró la asistencia con ID " + jornadaDeVacunacionAsistencia.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar asistencia: " + e);
        }
    }

    @Override
    public JornadaDeVacunacionAsistencia obtenerJornadaPorNombre(String nombre) {
        String sql = """
        SELECT ja.*, jv.nombre AS nombre_jornada
        FROM jornada_asistencias ja
        JOIN jornadas_vacunacion jv ON ja.jornada_id = jv.id
        WHERE jv.nombre = ?
        LIMIT 1
    """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                JornadaDeVacunacionAsistencia a = new JornadaDeVacunacionAsistencia();
                a.setId(rs.getInt("id"));
                a.setJornadaId(rs.getInt("jornada_id"));
                a.setNombreJornada(rs.getString("nombre_jornada"));
                a.setMascotaId(rs.getInt("mascota_id"));
                a.setDuenoId(rs.getInt("dueno_id"));
                a.setVacunado(rs.getBoolean("vacunado"));
                a.setProductoId(rs.getInt("producto_id"));
                a.setLote(rs.getString("lote"));
                a.setCantidad(rs.getInt("cantidad"));
                a.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                a.setEstado(rs.getString("estado"));
                a.setObservaciones(rs.getString("observaciones"));
                return a;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener asistencia por nombre: " + e);
        }

        return null;
    }
}
