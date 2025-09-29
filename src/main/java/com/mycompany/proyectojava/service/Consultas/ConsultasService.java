package com.mycompany.proyectojava.service.Consultas;

import com.mycompany.proyectojava.model.entities.Consultas.Consultas;
import com.mycompany.proyectojava.repository.Citas.ICitas;
import com.mycompany.proyectojava.repository.Consultas.IConsultas;

import java.util.Date;
import java.util.List;

public class ConsultasService {
    private IConsultas consultasDAO;
    private ICitas citasDAO;

    public ConsultasService(IConsultas consultasDAO, ICitas citasDAO) {
        this.consultasDAO = consultasDAO;
        this.citasDAO = citasDAO;
    }

    public boolean registrarConsulta(Consultas consulta) {
        if (consulta.getCita_id() == null || consulta.getVeterinario_id() == null) {
            throw new IllegalArgumentException("Cita y veterinario son obligatorios");
        }

        if (consulta.getDiagnostico() == null || consulta.getDiagnostico().trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico es obligatorio");
        }

        if (citasDAO.buscarCitaPorId(consulta.getCita_id()) == null) {
            throw new IllegalArgumentException("La cita especificada no existe");
        }

        if (consulta.getFecha_registro() == null) {
            consulta.setFecha_registro(new Date());
        }

        return consultasDAO.registrarConsulta(consulta);
    }

    public List<Consultas> listarTodasLasConsultas() {
        return consultasDAO.listarConsultas();
    }

    public List<Consultas> listarConsultasPorCita(Integer citaId) {
        return consultasDAO.buscarConsultasPorCita(citaId);
    }

    public List<Consultas> listarConsultasPorVeterinario(Integer veterinarioId) {
        return consultasDAO.buscarConsultasPorVeterinario(veterinarioId);
    }

    public boolean eliminarConsulta(Integer id) {
        Consultas consulta = consultasDAO.buscarConsultaPorId(id);
        if (consulta == null) {
            throw new IllegalArgumentException("Consulta no encontrada");
        }

        long diferencia = new Date().getTime() - consulta.getFecha_registro().getTime();
        long horas = diferencia / (60 * 60 * 1000);

        if (horas > 24) {
            throw new IllegalArgumentException("No se puede eliminar consultas con más de 24 horas de antigüedad");
        }

        return consultasDAO.eliminarConsulta(id);
    }

    public Consultas obtenerConsultaPorId(Integer id) {
        return consultasDAO.buscarConsultaPorId(id);
    }

    public boolean actualizarConsulta(Consultas consulta) {
        Consultas existente = consultasDAO.buscarConsultaPorId(consulta.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Consulta no encontrada");
        }

        if (!existente.getCita_id().equals(consulta.getCita_id()) ||
                !existente.getVeterinario_id().equals(consulta.getVeterinario_id())) {
            throw new IllegalArgumentException("No se puede modificar la cita o veterinario de una consulta existente");
        }

        return consultasDAO.actualizarConsulta(consulta);
    }
}