package com.mycompany.proyectojava.repository.Dueno;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DuenoDAO implements IDueno{
    private Connection conexion;

    public DuenoDAO() {
         this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarDueno(Dueno dueno) {
        String sql = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, correo_electronico, contacto_emergencia) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
             pstmt.setString(1, dueno.getNombre());
             pstmt.setString(2, dueno.getDocumento());
             pstmt.setString(3, dueno.getDireccion());
             pstmt.setString(4, dueno.getTelefono());
             pstmt.setString(5, dueno.getEmail());
             pstmt.setString(6, dueno.getContacto_emergencia());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar un dueño: " + e);
        }
    }

    @Override
    public List<Dueno> listarTodos() {
        List<Dueno> lst = new ArrayList<>();
        String sql = "SELECT * FROM duenos";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Dueno dueno = new Dueno(rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getString("estado"),
                        rs.getString("contacto_emergencia"));
                lst.add(dueno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los dueños: " + e);
        }
        return lst;
    }

    @Override
    public List<Dueno> listarActivos() {
        List<Dueno> lst = new ArrayList<>();
        String sql = "SELECT * FROM duenos";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Dueno dueno = new Dueno(rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getString("estado"),
                        rs.getString("contacto_emergencia"));
                lst.add(dueno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los dueños activos: " + e);
        }
        return lst.stream()
                .filter(d -> d.getEstado().equalsIgnoreCase("activo"))
                .sorted(Comparator.comparing(Dueno::getNombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dueno> listarInactivos() {
        List<Dueno> lst = new ArrayList<>();
        String sql = "SELECT * FROM duenos";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Dueno dueno = new Dueno(rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getString("estado"),
                        rs.getString("contacto_emergencia"));
                lst.add(dueno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los dueños inactivos: " + e);
        }
        return lst.stream()
                .filter(d -> d.getEstado().equalsIgnoreCase("inactivo"))
                .sorted(Comparator.comparing(Dueno::getNombre))
                .collect(Collectors.toList());
    }


    @Override
    public Dueno listarPorDocumento(String documento) {
        Dueno dueno = null;
        String sql = "SELECT * FROM duenos where documento_identidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, documento);
            try  (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    dueno = new Dueno(rs.getString("nombre_completo"),
                            rs.getString("documento_identidad"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo_electronico"),
                            rs.getString("estado"),
                            rs.getString("contacto_emergencia"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los dueños: " + e);
        }
        return dueno;
    }

    @Override
    public void actualizarDueno(Dueno dueno) {
        String sql = "UPDATE duenos SET nombre_completo = ?, documento_identidad = ?, direccion = ?, telefono = ?, correo_electronico = ?, contacto_emergencia = ? WHERE documento_identidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, dueno.getNombre());
            pstmt.setString(2, dueno.getDocumento());
            pstmt.setString(3, dueno.getDireccion());
            pstmt.setString(4, dueno.getTelefono());
            pstmt.setString(5, dueno.getEmail());
            pstmt.setString(6, dueno.getContacto_emergencia());
            pstmt.setString(7, dueno.getDocumento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar un dueño: " + e);
        }
    }

    @Override
    public void eliminarDueno(String documento) {
        String sql = "UPDATE duenos SET estado = 'inactivo' WHERE documento_identidad = ?";
        try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, documento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar un dueño ID: " + documento + " " + e);
        }
    }

    @Override
    public void verMascotasDeUnDueno(String documento) {
        String sql = "SELECT m.nombre, d.nombre_completo, d.documento_identidad FROM mascotas m JOIN duenos d ON m.dueno_id = d.id WHERE d.documento_identidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
            pstmt.setString(1, documento);
            try (ResultSet rs = pstmt.executeQuery()){
                System.out.println("Mascotas del dueño con documento " + documento + ":");
                while (rs.next()){
                    String nombreMascota = rs.getString("m.nombre");
                    String nombreDueno = rs.getString("d.nombre_completo");
                    String docDueno = rs.getString("d.documento_identidad");
                    System.out.println("Dueño: " + nombreDueno + " (Documento: " + docDueno + ") - Mascota: " + nombreMascota);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar las mascotas de un dueño: " + e);
        }
    }
}
