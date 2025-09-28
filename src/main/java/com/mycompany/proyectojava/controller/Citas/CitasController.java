package com.mycompany.proyectojava.controller.Citas;

import com.mycompany.proyectojava.model.entities.Citas.Citas;
import com.mycompany.proyectojava.service.Citas.CitasService;

import java.util.List;

public class CitasController {
    private CitasService citasService;

    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }

    public String registrarCita(Integer mascotaId, java.util.Date fechaHora,
                                Integer estadoId, Integer veterinarioId,
                                String motivo, String observaciones) {
        try {
            Citas nuevaCita = new Citas(mascotaId, fechaHora, estadoId, veterinarioId, motivo, observaciones);
            System.out.println("  - Mascota ID: " + nuevaCita.getMascota_id());
            System.out.println("  - Fecha: " + nuevaCita.getFecha_hora());
            System.out.println("  - Estado: " + nuevaCita.getEstado_id());
            boolean resultado = citasService.registrarCita(nuevaCita);

            if (resultado) {
                return "✅ Cita registrada exitosamente con ID: " + nuevaCita.getId();
            } else {
                return "❌ Error al registrar la cita";
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getClass().getName());
            System.out.println("ERROR Mensaje: " + e.getMessage());
            e.printStackTrace();
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Citas> obtenerTodasLasCitas() {
        return citasService.listarTodasLasCitas();
    }

    public List<Citas> obtenerCitasPorMascota(Integer mascotaId) {
        return citasService.listarCitasPorMascota(mascotaId);
    }

    public String actualizarCitas(Integer id, Integer mascotaId, java.util.Date fechaHora,
                                  Integer estadoId, Integer veterinarioId,
                                  String motivo, String observaciones) {
        try {
            Citas citaExistente = obtenerCita(id);
            if (citaExistente == null) {
                return "❌ Error: No se encontró una cita con ID: " + id;
            }
            Citas citaActualizada = new Citas(mascotaId, fechaHora, estadoId, veterinarioId, motivo, observaciones);
            citaActualizada.setId(id);
            boolean resultado = citasService.actualizarCitas(citaActualizada);

            if (resultado) {
                return "✅ Cita actualizada exitosamente";
            } else {
                return "❌ Error al actualizar la cita";
            }
        } catch (Exception e) {
            System.out.println("🔧 CONTROLLER ERROR al actualizar: " + e.getMessage());
            e.printStackTrace();
            return "❌ Error al actualizar la cita: " + e.getMessage();
        }
    }


    public String eliminarCita(Integer id) {
        try {
            boolean resultado = citasService.eliminarCita(id);
            if (resultado) {
                return "Cita eliminada exitosamente";
            } else {
                return "Error al eliminar la cita";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public Citas obtenerCita(Integer id) {
        return citasService.obtenerCitaPorId(id);
    }
}
