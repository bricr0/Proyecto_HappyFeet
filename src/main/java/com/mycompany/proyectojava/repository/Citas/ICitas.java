package com.mycompany.proyectojava.repository.Citas;
import com.mycompany.proyectojava.model.entities.Citas.Citas;

import java.util.List;

public interface ICitas {
    //    ---------------------------------------------------1. AGREGAR CITAS ------------------------------------------------

    boolean registrarCita(Citas citas);

//    ---------------------------------------------------2. LISTAR CITAS ------------------------------------------------

    List<Citas> listarCitas();

//    ---------------------------------------------------3. ACTUALIZAR CITAS ------------------------------------------------

    boolean actualizarCitas(Citas citas);

//    ---------------------------------------------------4. ELIMINAR CITAS  ------------------------------------------------

    boolean eliminarCita(Integer id);

    //    -----------------------------------------------------5. BUSCAR CITA POR ID  ------------------------------------------------
    Citas buscarCitaPorId(Integer id);

}
