package com.mycompany.proyectojava.repository.Adopcion;

import com.mycompany.proyectojava.model.entities.Adopcion.Adopcion;

import java.sql.SQLException;
import java.util.List;

public interface IAdopcion {
    void crearAdopcion(Adopcion adopcion) throws SQLException;
    Adopcion obtenerAdopcionPorDocumento(String documento);
    List<Adopcion> listarAdopciones() throws SQLException;
    void actualizarAdopcion(Adopcion adopcion);
    void eliminarAdopcion(String id) throws SQLException;
}
