package com.mycompany.proyectojava.repository.Mascota;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MascotaDAO implements IMascota {
    private Connection conexion;

    public MascotaDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

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
}
