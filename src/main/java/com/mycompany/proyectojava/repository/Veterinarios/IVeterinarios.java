package com.mycompany.proyectojava.repository.Veterinarios;

import com.mycompany.proyectojava.model.entities.Razas.Razas;
import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;

import java.util.List;

public interface IVeterinarios {
    //    ----------------------------------------------------1. AGREGAR VETERINARIOS ------------------------------------------------
    boolean registrarVeterinario(Veterinarios veterinario);

//    ----------------------------------------------------2. LISTAR VETERINARIOS ------------------------------------------------

    List<Veterinarios> listarVeterinarios();

    //    ----------------------------------------------------3. LISTAR POR ID ------------------------------------------------
    Veterinarios buscarVeterinarioPorId(Integer id);

//    ------------------------------------------------------------4. LISTAR POR NOMBRE ------------------------------------------------

    List<Veterinarios> buscarVeterinariosPorNombre(String nombre);

//    ----------------------------------------------------5. ACTUALIZAR VETERINARIO ------------------------------------------------

    boolean actualizarVeterinario(Veterinarios veterinario);

//    ----------------------------------------------------6. ELIMINAR VETERINARIO ------------------------------------------------

    boolean eliminarVeterinario(Integer id);
}
