package com.mycompany.proyectojava.repository.JornadaDeVacunacion;

import com.mycompany.proyectojava.model.entities.JornadasVacunacion.JornadasDeVacunacion;

import java.util.List;

public interface IJornadaDeVacunacion {
    void crearJornada(JornadasDeVacunacion jornada);
    List<JornadasDeVacunacion> listarJornadas(String jornada);
    void actualizarJornada(JornadasDeVacunacion jornadasDeVacunacion);
    void eliminarJornada(Integer id);
    JornadasDeVacunacion obtenerJornadaPorNombre(String nombre) ;

}
