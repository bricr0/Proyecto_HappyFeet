package com.mycompany.proyectojava.repository.Mascota;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class MascotaDAO implements IMascota {
    private Connection conexion;

    public MascotaDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }


//    -------------------------------------------------------------------------------1. AGREGAR MASCOTA ---------------------------------------------------------------------------------
    @Override
    public void agregarMascota(Mascota mascota) {
        String sql = "INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, microchip, alergias, condiciones_preexistentes, peso_kg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar una mascota: " + e);
        }
    }

//    -------------------------------------------------------------------------------2. LISTAR MASCOTAS ---------------------------------------------------------------------------------

    @Override
    public List<Mascota> listarTodas() {
        return List.of();
    }

//    -------------------------------------------------------------------------------3. LISTAR MASCOTAS ACTIVAS ---------------------------------------------------------------------------------

    @Override
    public List<Mascota> listarActivas() {
        return List.of();
    }

//    --------------------------------------------------------------------------------4. LISTAR MASCOTAS INACTIVAS ---------------------------------------------------------------------------------

    @Override
    public List<Mascota> listarInactivas() {
        return List.of();
    }

//    --------------------------------------------------------------------------------5. LISTAR MASCOTAS MICROCHIP ---------------------------------------------------------------------------------

    @Override
    public Mascota listarPorMicrochip(String microchip) {
        return null;
    }

//    --------------------------------------------------------------------------------6. ACTUALIZAR MASCOTA ---------------------------------------------------------------------------------

    @Override
    public void actualizarMascota(Mascota mascota) {

    }

//    --------------------------------------------------------------------------------7. ELIMINAR MASCOTA ---------------------------------------------------------------------------------

    @Override
    public void eliminarMascota(String microchip) {

    }

//    --------------------------------------------------------------------------------8. CREAR ESPECIE ---------------------------------------------------------------------------------

    @Override
    public void crearEspecie(String especie) {

    }

//    ---------------------------------------------------------------------------------9. CREAR RAZA ---------------------------------------------------------------------------------

    @Override
    public void crearRaza(String raza, String especie) {

    }
}
