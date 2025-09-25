package com.mycompany.proyectojava.repository.Razas;

import com.mycompany.proyectojava.model.entities.Razas.Razas;

import java.util.List;

public interface IRazas {

//    ----------------------------------------------------1. AGREGAR RAZA ------------------------------------------------
    void agregarRaza(Razas raza);

//    ----------------------------------------------------2. LISTAR RAZAS ------------------------------------------------

    List<Razas> listarRazas();

//    ----------------------------------------------------3. LISTAR POR ESPECIE ------------------------------------------------
    List<Razas> listarPorEspecie(Integer especie_id);

//    ------------------------------------------------------------4. LISTAR POR NOMBRE ------------------------------------------------

    Razas buscarPorNombre(String nombre);

//    ----------------------------------------------------5. ACTUALIZAR RAZA ------------------------------------------------

    void actualizarRaza(Razas raza);

//    ----------------------------------------------------6. ELIMINAR RAZA ------------------------------------------------

    void eliminarRaza(String nombre);
}



