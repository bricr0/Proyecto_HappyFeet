package com.mycompany.proyectojava.controller.Especie;

import com.mycompany.proyectojava.model.entities.Especie.Especie;
import com.mycompany.proyectojava.repository.Especie.IEspecie;

public class EspecieController {
    private IEspecie especieDao;

    public EspecieController(IEspecie especieDao) {
        this.especieDao = especieDao;
    }

//    --------------------------------------------------------------------1. AGREGAR ESPECIE ------------------------------------------------

    public void agregarEspecie(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la especie no puede estar vacío.");
        }

        if (especieDao.listarPorNombre(nombre) != null) {
            throw new IllegalArgumentException("La especie con nombre '" + nombre + "' ya existe.");
        }

        especieDao.agregarEspecie(nombre);
    }

//    -----------------------------------------------------2. LISTAR ESPECIES ------------------------------------------------

    public void listarEspecies() {
        especieDao.listarTodas().forEach(e -> System.out.println(e));
    }

//    ------------------------------------------------------------3. BUSCAR ESPECIE ------------------------------------------------

    public void buscarEspecie(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la especie no puede estar vacío.");
        }
        Especie especie = especieDao.listarPorNombre(nombre);
        if (especie == null) {
            System.out.println("No se encontró una especie con el nombre: " + nombre);
        } else {
            System.out.println(especie);
        }
    }

//    ----------------------------------------------------4. ACTUALIZAR ESPECIE ------------------------------------------------
    public void actualizarEspecie(Especie especie) {
        if (especie == null || especie.getNombre() == null || especie.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("Datos de la especie inválidos");
        }
        if (especieDao.listarPorNombre(especie.getNombre()) == null) {
            throw new IllegalArgumentException("La especie con nombre '" + especie.getNombre() + "' no existe.");
        }
        especieDao.actualizarEspecie(especie);
        System.out.println("Especie actualizada con éxito");
    }
//    ----------------------------------------------------5. ELIMINAR ESPECIE ------------------------------------------------
    public void eliminarEspecie(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la especie no puede estar vacío.");
        }
        if (especieDao.listarPorNombre(nombre) == null) {
            throw new IllegalArgumentException("La especie con nombre '" + nombre + "' no existe.");
        }
        especieDao.eliminarEspecie(nombre);
        System.out.println("Especie eliminada con éxito");
    }

}


