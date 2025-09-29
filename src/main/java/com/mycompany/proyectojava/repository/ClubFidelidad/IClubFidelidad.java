package com.mycompany.proyectojava.repository.ClubFidelidad;

import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.ClubFidelidad;
import com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes.PuntosMovimiento;

import java.util.List;

public interface IClubFidelidad {

    List<ClubFidelidad> listarClubFidelidad();

    ClubFidelidad obtenerMiembroPorDocumento(String documento);

    void actualizarPuntos(ClubFidelidad clubFidelidad);


    void registrarMovimiento(PuntosMovimiento movimiento);

    List<PuntosMovimiento> listarMovimientosPorDocumento(String documento);

    boolean canjearPuntos(String documento, int puntos, String descripcion);
}
