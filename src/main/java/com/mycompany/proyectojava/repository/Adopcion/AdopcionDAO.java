package com.mycompany.proyectojava.repository.Adopcion;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Adopcion.Adopcion;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEnum;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEstadoEnum;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdopcionDAO implements IAdopcion {
     private Connection conexion;

    public AdopcionDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

//    ------------------------------------ CREAR ADOPCION -------------------------------------------
    @Override
    public void crearAdopcion(Adopcion adopcion) throws SQLException {
        String sql = "{CALL registrar_adopcion(?,?,?,?,?)}";

        try (CallableStatement stmt = conexion.prepareCall(sql)) {
            stmt.setInt(1, adopcion.getMascotaId());
            stmt.setInt(2, adopcion.getAdoptanteId());
            stmt.setString(3, "Sistema");
            stmt.setString(4, adopcion.getTipo().name());
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.execute();

            adopcion.setId(stmt.getInt(5));
        } catch (SQLException e) {
            throw new RuntimeException("❌ Error al registrar adopción: " + e.getMessage(), e);
        }
    }

//    ----------------------------------------BUSCAR-----------------------------------------

    @Override
    public Adopcion obtenerAdopcionPorDocumento(String documento) {
        Adopcion adopcion = null;
        String sql = "SELECT \n" +
                "    a.id AS adopcion_id,\n" +
                "    m.id AS mascota_id,\n" +
                "    m.nombre AS nombre_mascota,\n" +
                "    ad.id AS adoptante_id,\n" +
                "    ad.nombre_completo AS nombre_adoptante,\n" +
                "    a.fecha_adopcion,\n" +
                "    a.tipo,\n" +
                "    a.estado,\n" +
                "    a.contrato_id,\n" +
                "    a.notas\n" +
                "FROM adopciones a\n" +
                "JOIN mascotas m ON a.mascota_id = m.id\n" +
                "JOIN duenos ad ON a.adoptante_id = ad.id\n" +
                "WHERE ad.documento_identidad = ?";
        try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, documento);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    Timestamp timestamp = rs.getTimestamp("fecha_adopcion");
                    LocalDateTime fechaAdopcion = timestamp != null ? timestamp.toLocalDateTime() : null;

                    AdopcionEnum tipoEnum = AdopcionEnum.valueOf(rs.getString("tipo").toLowerCase());

                    AdopcionEstadoEnum estadoEnum = AdopcionEstadoEnum.valueOf(rs.getString("estado").toLowerCase());
                    adopcion = new Adopcion(
                            rs.getInt("adoptante_id"),
                            rs.getString("nombre_mascota"),
                            rs.getString("nombre_adoptante"),
                            fechaAdopcion,
                            tipoEnum,
                            estadoEnum,
                            rs.getInt("contrato_id"),
                            rs.getString("notas")
                    );
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Error al consultar: " + e);
        }
        return adopcion;
    }

//    -------------------------------------------LISTAR ADOPCION ------------------------------------

    @Override
    public List<Adopcion> listarAdopciones() throws SQLException {
        List<Adopcion> lst = new ArrayList<>();
        String sql = "SELECT \n" +
                "    a.id AS adopcion_id,\n" +
                "    m.id AS mascota_id,\n" +
                "    m.nombre AS nombre_mascota,\n" +
                "    ad.id AS adoptante_id,\n" +
                "    ad.nombre_completo AS nombre_adoptante,\n" +
                "    a.fecha_adopcion,\n" +
                "    a.tipo,\n" +
                "    a.estado,\n" +
                "    a.contrato_id,\n" +
                "    a.notas\n" +
                "FROM adopciones a\n" +
                "JOIN mascotas m ON a.mascota_id = m.id\n" +
                "JOIN duenos ad ON a.adoptante_id = ad.id;";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("fecha_adopcion");
                    LocalDateTime fechaAdopcion = timestamp != null ? timestamp.toLocalDateTime() : null;

                    AdopcionEnum tipoEnum = rs.getString("tipo") != null
                            ? AdopcionEnum.valueOf(rs.getString("tipo").toLowerCase())
                            : null;

                    AdopcionEstadoEnum estadoEnum = rs.getString("estado") != null
                            ? AdopcionEstadoEnum.valueOf(rs.getString("estado").toLowerCase())
                            : null;

                    Adopcion adopcion = new Adopcion(
                            rs.getString("nombre_mascota"),
                            rs.getString("nombre_adoptante"),
                            fechaAdopcion,
                            tipoEnum,
                            estadoEnum,
                            rs.getInt("contrato_id"),
                            rs.getString("notas")
                    );

                    adopcion.setId(rs.getInt("adopcion_id"));
                    adopcion.setMascotaId(rs.getInt("mascota_id"));
                    adopcion.setAdoptanteId(rs.getInt("adoptante_id"));

                    lst.add(adopcion);
                }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar: " + e.getMessage(), e);
        }
        return lst;
    }

//    -------------------------------------------ACTUALIZAR ADOPCION --------------------------------------

    @Override
    public void actualizarAdopcion(Adopcion adopcion) {
        String sql = "UPDATE adopciones SET tipo=?, estado=? WHERE adoptante_id=?";
        try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, adopcion.getTipo().name());
            pstmt.setString(2, adopcion.getEstado().name());
            pstmt.setInt(3, adopcion.getAdoptanteId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar desde el dao" + e);
        }
    }

//    ------------------------------------------ELIMINAR ADOPCION -------------------------------------

    @Override
    public void eliminarAdopcion(String documento) throws SQLException {
        String sql = "UPDATE adopciones a " +
                "JOIN duenos d ON a.adoptante_id = d.id " +
                "SET a.estado = 'cancelada' " +
                "WHERE d.documento_identidad = ?";
        try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, documento);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error al eliminar: " + e);
        }
    }
}
