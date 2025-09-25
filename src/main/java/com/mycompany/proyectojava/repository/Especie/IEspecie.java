package com.mycompany.proyectojava.repository.Especie;

import com.mycompany.proyectojava.model.entities.Especie.Especie;

import java.util.List;

public interface IEspecie {
//    ----------------------------------------------------1. AGREGAR ESPECIE ------------------------------------------------

    void agregarEspecie(String nombre);

//    ----------------------------------------------------2. LISTAR ESPECIES ------------------------------------------------

    List<Especie> listarTodas();

//    ----------------------------------------------------3. BUSCAR ESPECIE ------------------------------------------------

    Especie listarPorNombre(String nombre);

//    ----------------------------------------------------4. ACTUALIZAR ESPECIE ------------------------------------------------

    void actualizarEspecie(Especie especie);

//    ----------------------------------------------------5. ELIMINAR ESPECIE ------------------------------------------------

    void eliminarEspecie(String nombre);

}


