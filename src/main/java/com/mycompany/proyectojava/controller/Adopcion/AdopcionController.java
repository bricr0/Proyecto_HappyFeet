package com.mycompany.proyectojava.controller.Adopcion;

import com.mycompany.proyectojava.model.entities.Adopcion.Adopcion;
import com.mycompany.proyectojava.model.enums.Adopcion.AdopcionEnum;
import com.mycompany.proyectojava.repository.Adopcion.AdopcionDAO;

import java.sql.SQLException;
import java.util.List;

public class AdopcionController {
    private AdopcionDAO adopcionDAO;

    public AdopcionController(AdopcionDAO adopcionDAO) {
        this.adopcionDAO = adopcionDAO;
    }

    public void registrarAdopcion(Adopcion adopcion) {
        try {
            if (adopcion.getMascotaId() == null || adopcion.getAdoptanteId() == null) {
                throw new IllegalArgumentException("La mascota y el adoptante son obligatorios");
            }

            if (adopcion.getTipo() == null) {
                adopcion.setTipo(AdopcionEnum.ADOPCION);
            }

            adopcionDAO.crearAdopcion(adopcion);

            System.out.println("✅ Adopción registrada con éxito. ID generado: " + adopcion.getId());

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar adopción en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ Error inesperado: " + e.getMessage());
        }
    }

    public Adopcion buscarAdopcion(String documento) {
        return adopcionDAO.obtenerAdopcionPorDocumento(documento);
    }

    public List<Adopcion> listarAdopciones() throws Exception {
        return adopcionDAO.listarAdopciones();
    }

    public void actualizarAdopcion(Adopcion adopcion) {
        adopcionDAO.actualizarAdopcion(adopcion);
    }

    public void eliminarAdopcion(String id) throws Exception {
        adopcionDAO.eliminarAdopcion(id);
    }
}
