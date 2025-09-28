package com.mycompany.proyectojava.controller.JornadaDeVacunacion;

import com.mycompany.proyectojava.model.entities.JornadasVacunacion.JornadasDeVacunacion;
import com.mycompany.proyectojava.model.enums.JornadasVacunacion.JornadaDeVacunacionEstado;
import com.mycompany.proyectojava.repository.JornadaDeVacunacion.IJornadaDeVacunacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JornadaDeVacunacionController {
    private final IJornadaDeVacunacion jornadaDeVacunacionDAO;

    public JornadaDeVacunacionController(IJornadaDeVacunacion jornadaDeVacunacionDAO) {
        this.jornadaDeVacunacionDAO = jornadaDeVacunacionDAO;
    }

    public void crearJornada(JornadasDeVacunacion jornada) {
        try {
            // Asignar estado por defecto si es null
            if (jornada.getEstado() == null) {
                jornada.setEstado(String.valueOf(JornadaDeVacunacionEstado.programada));
            }
            jornadaDeVacunacionDAO.crearJornada(jornada);
            System.out.println("✅ Jornada creada con ID: " + jornada.getId());
        } catch (Exception e) {
            System.out.println("⚠️ Error al crear jornada: " + e.getMessage());
        }
    }

    public List<JornadasDeVacunacion> listarJornadas(JornadaDeVacunacionEstado estadoFiltro) {
        try {
            String estadoStr = estadoFiltro != null ? estadoFiltro.name() : null;
            return jornadaDeVacunacionDAO.listarJornadas(estadoStr);
        } catch (Exception e) {
            System.out.println("⚠️ Error al listar jornadas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void actualizarJornada(JornadasDeVacunacion jornada) {
        try {
            // Convertir enum a string para DAO
            if (jornada.getEstado() != null) {
                jornada.setEstado(jornada.getEstado());
            }
            jornadaDeVacunacionDAO.actualizarJornada(jornada);
            System.out.println("✅ Jornada actualizada con éxito");
        } catch (Exception e) {
            System.out.println("⚠️ Error al actualizar jornada: " + e.getMessage());
        }
    }

    public void eliminarJornada(int id) {
        try {
            jornadaDeVacunacionDAO.eliminarJornada(id);
            System.out.println("✅ Jornada eliminada con éxito");
        } catch (Exception e) {
            System.out.println("⚠️ Error al eliminar jornada: " + e.getMessage());
        }
    }

    public JornadasDeVacunacion obtenerJornadaPorNombre(String nombre) {
        try {
            return jornadaDeVacunacionDAO.obtenerJornadaPorNombre(nombre);
        } catch (Exception e) {
            System.out.println("⚠️ Error al obtener jornada: " + e.getMessage());
            return null;
        }
    }
}
