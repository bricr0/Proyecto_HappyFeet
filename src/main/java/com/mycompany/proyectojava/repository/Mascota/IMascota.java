package com.mycompany.proyectojava.repository.Mascota;

import com.mycompany.proyectojava.model.entities.Mascota.Mascota;

import java.util.List;

public interface IMascota {

//    ---------------------------------------------------1. AGREGAR MASCOTA ------------------------------------------------

    void agregarMascota(Mascota mascota);

//    ---------------------------------------------------2. LISTAR MASCOTAS ------------------------------------------------

    List<Mascota> listarTodas();

//    ---------------------------------------------------3. LISTAR MASCOTAS ACTIVAS ------------------------------------------------
    List<Mascota> listarActivas();

//    ---------------------------------------------------4. LISTAR MASCOTAS INACTIVAS ------------------------------------------------
    List<Mascota> listarInactivas();

//    ---------------------------------------------------5. BUSCAR MASCOTA ------------------------------------------------
    Mascota listarPorMicrochip(String microchip);

//    ---------------------------------------------------6. ACTUALIZAR MASCOTA ------------------------------------------------
    void actualizarMascota(Mascota mascota);

//    -----------------------------------------------------------7. ELIMINAR MASCOTA ------------------------------------------------
    void eliminarMascota(String microchip);


}




