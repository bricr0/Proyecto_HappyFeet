package com.mycompany.proyectojava.repository.Veterinarios;

import com.mycompany.proyectojava.model.entities.Razas.Razas;
import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;

import java.util.List;

public interface IVeterinarios {
    //    ----------------------------------------------------1. AGREGAR RAZA ------------------------------------------------
    boolean registrarVeterinario(Veterinarios veterinario);

//    ----------------------------------------------------2. LISTAR RAZAS ------------------------------------------------

    List<Veterinarios> listarVeterinarios();

    //    ----------------------------------------------------3. LISTAR POR ESPECIE ------------------------------------------------
    Veterinarios buscarVeterinarioPorId(Integer id);

//    ------------------------------------------------------------4. LISTAR POR NOMBRE ------------------------------------------------

    List<Veterinarios> buscarVeterinariosPorNombre(String nombre);

//    ----------------------------------------------------5. ACTUALIZAR RAZA ------------------------------------------------

    boolean actualizarVeterinario(Veterinarios veterinario);

//    ----------------------------------------------------6. ELIMINAR RAZA ------------------------------------------------

    boolean eliminarVeterinario(Integer id);
}
