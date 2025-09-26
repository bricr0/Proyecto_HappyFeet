package com.mycompany.proyectojava.repository.Mascota;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.model.entities.Razas.Razas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MascotaDAO implements IMascota {
    private Connection conexion;

    public MascotaDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }


//    -------------------------------------------------------------------------------1. AGREGAR MASCOTA ---------------------------------------------------------------------------------
    @Override
    public void agregarMascota(Mascota mascota) {
        String sql = "INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, microchip, alergias, condiciones_preexistentes, peso_kg, notas_medicas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
             pstmt.setInt(1, mascota.getDueno_id());
             pstmt.setString(2, mascota.getNombre());
             pstmt.setInt(3, mascota.getRaza_id());
             pstmt.setDate(4, new java.sql.Date(mascota.getFecha_nacimiento().getTime()));
             pstmt.setString(5, mascota.getSexo());
             pstmt.setString(6, mascota.getMicrochip());
             pstmt.setString(7, mascota.getAlergias());
             pstmt.setString(8, mascota.getCondiciones_preexistentes());
             pstmt.setDouble(9, mascota.getPeso_kg());
             pstmt.setString(10, mascota.getNotas_medicas());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar una mascota: " + e);
        }
    }

//    -------------------------------------------------------------------------------2. LISTAR MASCOTAS ---------------------------------------------------------------------------------

    @Override
    public List<Mascota> listarTodas() {
        List<Mascota> lst = new ArrayList<>();
        String sql = "SELECT m.nombre AS mascota_nombre, m.fecha_nacimiento, m.sexo, m.microchip, " +
                "m.alergias, m.condiciones_preexistentes, m.peso_kg, m.notas_medicas, m.estado, " +
                "d.nombre_completo AS dueno_nombre, r.nombre AS raza_nombre " +
                "FROM mascotas m " +
                "JOIN duenos d ON m.dueno_id = d.id " +
                "JOIN razas r ON m.raza_id = r.id";
        try (Statement stmt = conexion.createStatement(); ResultSet re = stmt.executeQuery(sql)) {
            while (re.next()) {
                Dueno dueno = new Dueno();
                dueno.setNombre(re.getString("dueno_nombre"));

                Razas raza = new Razas();
                raza.setNombre(re.getString("raza_nombre"));

                Mascota mascota = new Mascota(
                        null,
                        re.getString("mascota_nombre"),
                        null,
                        re.getDate("fecha_nacimiento"),
                        re.getString("sexo"),
                        re.getString("microchip"),
                        null,
                        re.getString("alergias"),
                        re.getString("condiciones_preexistentes"),
                        re.getDouble("peso_kg"),
                        re.getString("notas_medicas"),
                        re.getString("estado")
                );

                mascota.setDueno(dueno);
                mascota.setRaza(raza);

                lst.add(mascota);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar todas las mascotas: " + e);
        }
        return lst;
    }


//    -------------------------------------------------------------------------------3. LISTAR MASCOTAS ACTIVAS ---------------------------------------------------------------------------------

    @Override
    public List<Mascota> listarActivas() {
        List<Mascota> lst = new ArrayList<>();
        String sql = """
        SELECT m.id, m.nombre, m.fecha_nacimiento, m.sexo, m.microchip,
               m.alergias, m.condiciones_preexistentes, m.peso_kg, 
               m.notas_medicas, m.estado,
               d.nombre_completo AS dueno_nombre,
               r.nombre AS raza_nombre
        FROM mascotas m
        JOIN duenos d ON m.dueno_id = d.id
        LEFT JOIN razas r ON m.raza_id = r.id
        WHERE m.estado = 'activo'
        ORDER BY m.nombre ASC
    """;

        try (Statement stmt = conexion.createStatement();
             ResultSet re = stmt.executeQuery(sql)) {
            while (re.next()) {
                Mascota mascota = new Mascota(
                        null,
                        re.getString("nombre"),
                        null,
                        re.getDate("fecha_nacimiento"),
                        re.getString("sexo"),
                        re.getString("microchip"),
                        null,
                        re.getString("alergias"),
                        re.getString("condiciones_preexistentes"),
                        re.getDouble("peso_kg"),
                        re.getString("notas_medicas"),
                        re.getString("estado")
                );
                Dueno dueno = new Dueno();
                dueno.setNombre(re.getString("dueno_nombre"));
                mascota.setDueno(dueno);

                Razas raza = new Razas();
                raza.setNombre(re.getString("raza_nombre"));
                mascota.setRaza(raza);

                lst.add(mascota);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar todas las mascotas activas: " + e);
        }
        return lst;
    }


//    --------------------------------------------------------------------------------4. LISTAR MASCOTAS INACTIVAS ---------------------------------------------------------------------------------

    @Override
    public List<Mascota> listarInactivas() {
        List<Mascota> lst = new ArrayList<>();
        String sql = """
        SELECT m.id, m.nombre, m.fecha_nacimiento, m.sexo, m.microchip,
               m.alergias, m.condiciones_preexistentes, m.peso_kg, 
               m.notas_medicas, m.estado,
               d.nombre_completo AS dueno_nombre,
               r.nombre AS raza_nombre
        FROM mascotas m
        JOIN duenos d ON m.dueno_id = d.id
        LEFT JOIN razas r ON m.raza_id = r.id
        WHERE m.estado = 'inactivo'
        ORDER BY m.nombre ASC
    """;

        try (Statement stmt = conexion.createStatement();
             ResultSet re = stmt.executeQuery(sql)) {
            while (re.next()) {
                Mascota mascota = new Mascota(
                        null,
                        re.getString("nombre"),
                        null,
                        re.getDate("fecha_nacimiento"),
                        re.getString("sexo"),
                        re.getString("microchip"),
                        null,
                        re.getString("alergias"),
                        re.getString("condiciones_preexistentes"),
                        re.getDouble("peso_kg"),
                        re.getString("notas_medicas"),
                        re.getString("estado")
                );
                Dueno dueno = new Dueno();
                dueno.setNombre(re.getString("dueno_nombre"));
                mascota.setDueno(dueno);

                Razas raza = new Razas();
                raza.setNombre(re.getString("raza_nombre"));
                mascota.setRaza(raza);

                lst.add(mascota);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar todas las mascotas inactivas: " + e);
        }
        return lst;
    }


//    --------------------------------------------------------------------------------5. LISTAR MASCOTAS MICROCHIP ---------------------------------------------------------------------------------

    @Override
    public Mascota listarPorMicrochip(String microchip) {
        Mascota mascota = null;
        String sql = """
                SELECT m.id,\s
                       m.dueno_id,\s
                       m.raza_id,
                       m.nombre,\s
                       m.fecha_nacimiento,\s
                       m.sexo,\s
                       m.microchip,
                       m.foto_url,
                       m.alergias,\s
                       m.condiciones_preexistentes,\s
                       m.peso_kg,
                       m.notas_medicas,\s
                       m.estado,
                       d.nombre_completo AS dueno_nombre,
                       r.nombre AS raza_nombre
                FROM mascotas m
                JOIN duenos d ON m.dueno_id = d.id
                LEFT JOIN razas r ON m.raza_id = r.id
                WHERE m.microchip = ?
    """;

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, microchip);
            try (ResultSet re = pstmt.executeQuery()) {
                if (re.next()) {
                    Dueno dueno = new Dueno();
                    dueno.setNombre(re.getString("dueno_nombre"));

                    Razas raza = new Razas();
                    raza.setNombre(re.getString("raza_nombre"));

                    mascota = new Mascota(
                            re.getInt("id"),
                            re.getInt("dueno_id"),
                            re.getString("nombre"),
                            re.getInt("raza_id"),
                            re.getDate("fecha_nacimiento"),
                            re.getString("sexo"),
                            re.getString("microchip"),
                            re.getString("foto_url"),
                            re.getString("alergias"),
                            re.getString("condiciones_preexistentes"),
                            re.getDouble("peso_kg"),
                            re.getString("notas_medicas"),
                            dueno,
                            raza,
                            re.getString("estado")
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la mascota por microchip: " + e);
        }
        return mascota;
    }


//    --------------------------------------------------------------------------------6. ACTUALIZAR MASCOTA ---------------------------------------------------------------------------------

    @Override
    public void actualizarMascota(Mascota mascota) {
        String sql = "UPDATE mascotas SET  nombre = ?,  fecha_nacimiento = ?, sexo = ?, alergias = ?, condiciones_preexistentes = ?, peso_kg = ? WHERE microchip = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, mascota.getNombre());
            pstmt.setDate(2, new java.sql.Date(mascota.getFecha_nacimiento().getTime()));
            pstmt.setString(3, mascota.getSexo());
            pstmt.setString(4, mascota.getAlergias());
            pstmt.setString(5, mascota.getCondiciones_preexistentes());
            pstmt.setDouble(6, mascota.getPeso_kg());
            pstmt.setString(7, mascota.getMicrochip());
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error al actualizar una mascota: " + e);
        }
    }

//    --------------------------------------------------------------------------------7. ELIMINAR MASCOTA ---------------------------------------------------------------------------------

    @Override
    public void eliminarMascota(String microchip) {
        String sql = "UPDATE mascotas SET estado = 'inactivo' WHERE microchip = ?";
        try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, microchip);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar una mascota ID: " + microchip + " " + e);
        }
    }

}
