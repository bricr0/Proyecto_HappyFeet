package com.mycompany.proyectojava.controller.ClubFidelidad;

import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.ClubFidelidad;
import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.PuntosMovimiento;
import com.mycompany.proyectojava.repository.ClubFidelidad.ClubFidelidadDAO;

import java.util.List;

public class ClubFidelidadController {

    private ClubFidelidadDAO dao;

    public ClubFidelidadController(ClubFidelidadDAO dao) {
        this.dao = dao;
    }

    public List<ClubFidelidad> listarClubFidelidad() {
        return dao.listarClubFidelidad();
    }

    public ClubFidelidad obtenerMiembroPorDocumento(String documento) {
        return dao.obtenerMiembroPorDocumento(documento);
    }

    public void registrarMovimiento(PuntosMovimiento movimiento) {
        dao.registrarMovimiento(movimiento);
    }

    public boolean canjearPuntos(String documentoDueno, int puntos, String descripcion) {
        return dao.canjearPuntos(documentoDueno, puntos, descripcion);
    }

    public void actualizarPuntos(ClubFidelidad clubFidelidad) {
        dao.actualizarPuntos(clubFidelidad);
    }

    public List<PuntosMovimiento> listarMovimientosPorDocumento(String documento) {
        return dao.listarMovimientosPorDocumento(documento);
    }
}

