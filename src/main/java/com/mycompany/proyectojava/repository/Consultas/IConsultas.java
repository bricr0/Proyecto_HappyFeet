package com.mycompany.proyectojava.repository.Consultas;

import com.mycompany.proyectojava.model.entities.Consultas.Consultas;

import java.util.List;

public interface IConsultas {
    //    ---------------------------------------------------1. AGREGAR CITAS ------------------------------------------------

    boolean registrarConsulta(Consultas consultas);

//    ---------------------------------------------------2. LISTAR CITAS ------------------------------------------------

    List<Consultas> listarConsultas();
    //    ---------------------------------------------------3. BUSCAR CITA ------------------------------------------------

    List<Consultas> buscarConsultasPorCita(Integer citaId);
    //    ---------------------------------------------------4. BUSCAR CITA POR VETERINARIO ------------------------------------------------
    List<Consultas> buscarConsultasPorVeterinario(Integer veterinarioId);

//    ---------------------------------------------------5. ACTUALIZAR CITAS ------------------------------------------------

    boolean actualizarConsulta(Consultas consulta);

//    ---------------------------------------------------6. ELIMINAR CITAS  ------------------------------------------------

    boolean eliminarConsulta(Integer id);

    //    -----------------------------------------------------7. BUSCAR CITA POR ID  ------------------------------------------------
    Consultas buscarConsultaPorId(Integer id);

}
