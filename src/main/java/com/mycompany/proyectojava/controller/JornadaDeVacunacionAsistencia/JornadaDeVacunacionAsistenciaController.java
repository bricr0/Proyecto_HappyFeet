package com.mycompany.proyectojava.controller.JornadaDeVacunacionAsistencia;

import com.mycompany.proyectojava.model.entities.JornadaDeVacunacionAsistencia.JornadaDeVacunacionAsistencia;
import com.mycompany.proyectojava.repository.JornadaDeVacunacionAsistencia.IJornadaDeVacunacionAsistencia;

import java.util.List;

public class JornadaDeVacunacionAsistenciaController {
    private final IJornadaDeVacunacionAsistencia asistenciaDAO;

    public JornadaDeVacunacionAsistenciaController(IJornadaDeVacunacionAsistencia asistenciaDAO) {
        this.asistenciaDAO = asistenciaDAO;
    }

    public void registrarAsistencia(JornadaDeVacunacionAsistencia asistencia) {
        if (asistencia.getJornadaId() == null || asistencia.getMascotaId() == null || asistencia.getDuenoId() == null) {
            throw new IllegalArgumentException("Jornada, mascota y dueño son obligatorios");
        }
        asistenciaDAO.registrarAsistenncia(asistencia);
    }

    public List<JornadaDeVacunacionAsistencia> listarAsistenciasPorJornada(String nombreJornada) {
        return asistenciaDAO.listarJornadaAsistencia(nombreJornada);
    }

    public void actualizarAsistencia(JornadaDeVacunacionAsistencia asistencia) {
        if (asistencia.getId() == null) {
            throw new IllegalArgumentException("El ID de la asistencia es obligatorio para actualizar");
        }
        asistenciaDAO.actualizarJornadaAsistecia(asistencia);
    }

    public void eliminarAsistencia(JornadaDeVacunacionAsistencia asistencia) {
        if (asistencia.getId() == null) {
            throw new IllegalArgumentException("El ID de la asistencia es obligatorio para eliminar");
        }
        asistenciaDAO.eliminarJornadaAsistencia(asistencia);
    }

    public JornadaDeVacunacionAsistencia obtenerAsistenciaPorNombre(String nombreJornada) {
        return asistenciaDAO.obtenerJornadaPorNombre(nombreJornada);
    }
}
