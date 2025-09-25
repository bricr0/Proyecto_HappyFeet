package com.mycompany.proyectojava.repository.Razas;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Razas.Razas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RazasDAO implements IRazas {
    private final Connection conexion;

    public RazasDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

//    --------------------------------------------------------------------1. AGREGAR RAZA --------------------------------------------------------------------

    public void agregarRaza(Razas raza) {
        String sql = "INSERT INTO razas (especie_id, nombre) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, raza.getNombre());
            pstmt.setInt(2, raza.getEspecie_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar raza: " + e);
        }
    }
//    ----------------------------------------------------2. LISTAR RAZAS ---------------------------------------------------

    public List<Razas> listarRazas() {
        List<Razas> lst = new ArrayList<>();
        String sql = "SELECT * FROM razas";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Razas raza = new Razas(
                        rs.getString("nombre"),
                        rs.getInt("especie_id"));
                lst.add(raza);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar razas: " + e);
        }
        return lst;
    }


//    ----------------------------------------------------------------------3. LISTAR P0R ESPECIE ---------------------------------------------------

    public List<Razas> listarPorEspecie(Integer especieId) {
        List<Razas> lst = new ArrayList<>();
        String sql = "SELECT * FROM razas WHERE especie_id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, especieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Razas raza = new Razas(
                            rs.getString("nombre"),
                            rs.getInt("especie_id")
                    );
                    lst.add(raza);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar razas por especie: " + e);
        }
        return lst;
    }

//    ----------------------------------------------------------------------4. BUSCAR RAZA ---------------------------------------------------

    public Razas buscarPorNombre(String nombre) {
        Razas raza = null;
        String sql = "SELECT * FROM razas WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    raza = new Razas(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("especie_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar raza: " + e);
        }
        return raza;
    }

    @Override
    public void actualizarRaza(Razas raza) {

    }

//    ----------------------------------------------------------------------5. ELIMINAR RAZA ---------------------------------------------------

    public void eliminarRaza(String nombre) {
        String sql = "DELETE FROM razas WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar raza: " + e);
        }
    }
}
