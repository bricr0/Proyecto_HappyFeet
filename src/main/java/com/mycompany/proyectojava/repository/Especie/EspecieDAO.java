package com.mycompany.proyectojava.repository.Especie;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Especie.Especie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO implements IEspecie {
    private Connection conexion;

    public EspecieDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

//    --------------------------------------------------------------------1. AGREGAR ESPECIE ------------------------------------------------

    @Override
    public void agregarEspecie(String nombre) {
        String sql = "INSERT INTO especies (nombre) VALUES (?)";

        try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar una especie: " + e);
        }
    }

//    -----------------------------------------------------2. LISTAR ESPECIES ------------------------------------------------

    @Override
    public List<Especie> listarTodas() {
        List<Especie> lst = new ArrayList<>();
        String sql = "SELECT * FROM especies";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Especie especie = new Especie(
                        rs.getInt("id"),
                        rs.getString("nombre"));;
                lst.add(especie);
            }
            return lst;
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar todas las especies: " + e);
        }
    }

//    -------------------------------------------------------------3. BUSCAR ESPECIE ------------------------------------------------

    @Override
    public Especie listarPorNombre(String nombre) {
        String sql = "SELECT * FROM especies WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Especie especie = new Especie();
                especie.setId(rs.getInt("id"));           // ✅ usar setter
                especie.setNombre(rs.getString("nombre"));
                return especie;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar una especie por nombre: " + e);
        }
    }


//    -----------------------------------------------------4. ACTUALIZAR ESPECIE ------------------------------------------------

    @Override
    public void actualizarEspecie(Especie especie) {
        String sql = "UPDATE especies SET nombre = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, especie.getNombre());
            pstmt.setString(2, especie.getNombre());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar una especie: " + e);
        }
    }

//    -----------------------------------------------------5. ELIMINAR ESPECIE ------------------------------------------------

    @Override
    public void eliminarEspecie(String nombre){
        String sql = "DELETE FROM especies WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar una especie: " + e);
        }
    }

}
