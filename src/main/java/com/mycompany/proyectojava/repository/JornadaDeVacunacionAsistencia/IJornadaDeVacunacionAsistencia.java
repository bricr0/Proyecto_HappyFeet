package com.mycompany.proyectojava.repository.JornadaDeVacunacionAsistencia;

import com.mycompany.proyectojava.model.entities.JornadaDeVacunacionAsistencia.JornadaDeVacunacionAsistencia;

import java.util.List;

public interface IJornadaDeVacunacionAsistencia {
    void registrarAsistenncia(JornadaDeVacunacionAsistencia jornadaDeVacunacionAsistencia);
    List<JornadaDeVacunacionAsistencia> listarJornadaAsistencia(String jornada);
    void actualizarJornadaAsistecia(JornadaDeVacunacionAsistencia jornadaDeVacunacionAsistencia);
    void eliminarJornadaAsistencia(JornadaDeVacunacionAsistencia jornadaDeVacunacionAsistencia);
    JornadaDeVacunacionAsistencia obtenerJornadaPorNombre(String nombre);
}
