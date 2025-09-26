package com.mycompany.proyectojava.controller.Razas;

import com.mycompany.proyectojava.model.entities.Razas.Razas;
import com.mycompany.proyectojava.repository.Razas.IRazas;

import java.util.List;

public class RazaController {
    private IRazas razasDao;

    public RazaController(IRazas razasDao) {
        this.razasDao = razasDao;
    }

//    --------------------------------------------------------------1. AGREGAR RAZA--------------------------------------------------------------

    public void agregarRaza(Razas raza) {

        if (!validarRaza(raza)) {
            throw new IllegalArgumentException("Datos de raza inválidos");
        }
        razasDao.agregarRaza(raza);
        System.out.println("Raza creada con éxito");
    }

//    ---------------------------------------------------------------2. LISTAR RAZAS--------------------------------------------------------------


    public void listarRazas() {

        razasDao.listarRazas().forEach(r -> System.out.println(r));
    }

//    ---------------------------------------------------------------3. LISTAR POR ESPECIE--------------------------------------------------------------

    public List<Razas> listarPorEspecie(Integer especie_id) {
        if (especie_id == null || especie_id <= 0) {
            throw new IllegalArgumentException("ID de especie inválido");
        }
        return razasDao.listarPorEspecie(especie_id);
    }



//    ---------------------------------------------------------------4. LISTAR POR NOMBRE--------------------------------------------------------------

    public Razas buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        return razasDao.buscarPorNombre(nombre);
    }

//    ---------------------------------------------------------------5. ACTUALIZAR RAZA--------------------------------------------------------------

    public void actualizarRaza(Razas raza) {
        if (!validarRaza(raza)) {
            throw new IllegalArgumentException("Datos de raza inválidos");
        }
        razasDao.actualizarRaza(raza);
        System.out.println("Raza actualizada con éxito");
    }

//    ---------------------------------------------------------------6. ELIMINAR RAZA--------------------------------------------------------------

    public void eliminarRaza(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        razasDao.eliminarRaza(nombre);
        System.out.println("Raza eliminada con éxito");
    }

//    ---------------------------------------------------------------- VALIDACION --------------------------------------------------------------

    private boolean validarRaza(Razas raza) {
        if (raza == null) {
            System.out.println("❌ Raza nula");
            return false;
        }

        if (raza.getNombre() == null || raza.getNombre().trim().isEmpty()) {
            System.out.println("❌ Nombre de raza inválido");
            return false;
        }

        if (raza.getEspecie_id() == null || raza.getEspecie_id() <= 0) {
            System.out.println("❌ Especie inválida (id debe ser positivo)");
            return false;
        }

        return true;
    }

}



