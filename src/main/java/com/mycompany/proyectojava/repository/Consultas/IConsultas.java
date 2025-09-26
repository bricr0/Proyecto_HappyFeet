package com.mycompany.proyectojava.repository.Consultas;

import com.mycompany.proyectojava.model.entities.Consultas.Consultas;

import java.util.List;

public interface IConsultas {
    //    ---------------------------------------------------1. AGREGAR CITAS ------------------------------------------------

    boolean registrarConsulta(Consultas consultas);

//    ---------------------------------------------------2. LISTAR CITAS ------------------------------------------------

    List<Consultas> listarConsultas();
    List<Consultas> buscarConsultasPorCita(Integer citaId);
    List<Consultas> buscarConsultasPorVeterinario(Integer veterinarioId);
//    ---------------------------------------------------3. ACTUALIZAR CITAS ------------------------------------------------

    boolean actualizarConsulta(Consultas consulta);

//    ---------------------------------------------------4. ELIMINAR CITAS  ------------------------------------------------

    boolean eliminarConsulta(Integer id);

    //    -----------------------------------------------------5. BUSCAR CITA POR ID  ------------------------------------------------
    Consultas buscarConsultaPorId(Integer id);

}
