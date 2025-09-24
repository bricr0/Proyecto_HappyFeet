package com.mycompany.proyectojava.repository.Dueno;

import com.mycompany.proyectojava.model.entities.Dueno.Dueno;

import java.util.List;

public interface IDueno {
//    ---------------------------------------------------1. AGREGAR DUEÑO ------------------------------------------------

    void agregarDueno(Dueno dueno);

//    ---------------------------------------------------2. LISTAR DUEÑOS ------------------------------------------------

    List<Dueno> listarTodos();

//    ---------------------------------------------------3. LISTAR DUEÑOS ACTIVOS ------------------------------------------------

    List<Dueno> listarActivos();

//    ---------------------------------------------------4. LISTAR DUEÑOS INACTIVOS ------------------------------------------------

    List<Dueno> listarInactivos();

//    ---------------------------------------------------5. BUSCAR DUEÑO ------------------------------------------------

    Dueno listarPorDocumento(String documento);
//    ---------------------------------------------------6. ACTUALIZAR DUEÑO ------------------------------------------------

    void actualizarDueno(Dueno dueno);

//    ---------------------------------------------------7. ELIMINAR DUEÑO ------------------------------------------------

    void eliminarDueno(String documento);

//    -----------------------------------------------------8. VER MASCOTAS DE UN DUEÑO ------------------------------------------------
    void verMascotasDeUnDueno(String documento);
}

