package com.mycompany.proyectojava.service.Citas;

import com.mycompany.proyectojava.model.entities.Citas.Citas;
import com.mycompany.proyectojava.repository.Citas.ICitas;

import java.util.List;

public class CitasService {
    private ICitas citasDAO;

    public CitasService(ICitas citasDAO) {
        this.citasDAO = citasDAO;
    }

    public boolean registrarCita(Citas cita) {
        try {
            if (cita.getMascota_id() == null) {
                System.out.println("❌ Mascota ID es nulo");
                return false;
            }
            if (cita.getFecha_hora() == null) {
                System.out.println("❌ Fecha es nula");
                return false;
            }
            if (cita.getVeterinario_id() == null) {
                System.out.println("❌ Veterinario ID es nulo");
                return false;
            }
            boolean resultado = citasDAO.registrarCita(cita);

            return resultado;

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Citas> listarTodasLasCitas() {
        return citasDAO.listarCitas();
    }

    public List<Citas> listarCitasPorMascota(Integer mascotaId) {
        return citasDAO.listarCitas().stream()
                .filter(c -> c.getMascota_id().equals(mascotaId))
                .collect(java.util.stream.Collectors.toList());
    }

    public boolean actualizarCitas(Citas cita) {
        try {
            if (cita.getId() == null) {
                System.out.println("❌ ID de cita es nulo - necesario para actualizar");
                return false;
            }

            if (cita.getMascota_id() == null) {
                System.out.println("❌ Mascota ID es nulo");
                return false;
            }

            if (cita.getFecha_hora() == null) {
                System.out.println("❌ Fecha es nula");
                return false;
            }

            if (cita.getVeterinario_id() == null) {
                System.out.println("❌ Veterinario ID es nulo");
                return false;
            }
            boolean resultado = citasDAO.actualizarCitas(cita);
            return resultado;

        } catch (Exception e) {
            System.out.println("ERROR al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCita(Integer id) {
        Citas cita = citasDAO.buscarCitaPorId(id);
        if (cita == null) {
            throw new IllegalArgumentException("Cita no encontrada");
        }

        if (cita.getEstado_id() == 2 || cita.getEstado_id() == 3) {
            throw new IllegalArgumentException("No se puede eliminar una cita en progreso o completada");
        }

        return citasDAO.eliminarCita(id);
    }

    public Citas obtenerCitaPorId(Integer id) {
        return citasDAO.buscarCitaPorId(id);
    }
}
