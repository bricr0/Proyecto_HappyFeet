package com.mycompany.proyectojava.controller.Consultas;

import com.mycompany.proyectojava.model.entities.Consultas.Consultas;
import com.mycompany.proyectojava.service.Consultas.ConsultasService;

import java.util.Date;
import java.util.List;

public class ConsultasController {
    private ConsultasService consultasService;

    public ConsultasController(ConsultasService consultasService) {
        this.consultasService = consultasService;
    }

    public String registrarConsulta(Integer citaId, Integer veterinarioId, String diagnostico,
                                    String tratamientoRecomendado, String procedimientos, Date fechaRegistro) {
        try {
            Consultas nuevaConsulta = new Consultas(null, citaId, veterinarioId, diagnostico,
                    tratamientoRecomendado, procedimientos, fechaRegistro);
            boolean resultado = consultasService.registrarConsulta(nuevaConsulta);

            if (resultado) {
                return "Consulta registrada exitosamente con ID: " + nuevaConsulta.getId();
            } else {
                return "Error al registrar la consulta";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public List<Consultas> obtenerTodasLasConsultas() {
        return consultasService.listarTodasLasConsultas();
    }

    public List<Consultas> obtenerConsultasPorCita(Integer citaId) {
        return consultasService.listarConsultasPorCita(citaId);
    }

    public List<Consultas> obtenerConsultasPorVeterinario(Integer veterinarioId) {
        return consultasService.listarConsultasPorVeterinario(veterinarioId);
    }

    public String eliminarConsulta(Integer id) {
        try {
            boolean resultado = consultasService.eliminarConsulta(id);
            if (resultado) {
                return "Consulta eliminada exitosamente";
            } else {
                return "Error al eliminar la consulta";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public Consultas obtenerConsulta(Integer id) {
        return consultasService.obtenerConsultaPorId(id);
    }

    public String actualizarConsulta(Integer id, String diagnostico, String tratamientoRecomendado, String procedimientos) {
        try {
            Consultas consultaExistente = consultasService.obtenerConsultaPorId(id);
            if (consultaExistente == null) {
                return "Consulta no encontrada";
            }

            consultaExistente.setDiagnostico(diagnostico);
            consultaExistente.setTratamiento_recomendado(tratamientoRecomendado);
            consultaExistente.setProcedimientos(procedimientos);

            boolean resultado = consultasService.actualizarConsulta(consultaExistente);

            if (resultado) {
                return "Consulta actualizada exitosamente";
            } else {
                return "Error al actualizar la consulta";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}