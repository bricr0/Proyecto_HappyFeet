package com.mycompany.proyectojava.controller.Dueno;

import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.repository.Dueno.IDueno;

public class DuenoController {
    private IDueno duenoDao;

    public DuenoController(IDueno duenoDao) {
        this.duenoDao = duenoDao;
    }


//    ------------------------------------------------ 1. AGREGAR DUEÑO ------------------------------------------------

    public void agregarDueno(Dueno dueno) {
        if (!validarDueno(dueno)) {
            throw new IllegalArgumentException("Datos de dueño inválidos");
        }
        duenoDao.agregarDueno(dueno);
        System.out.println("Dueño creado con éxito");
    }


//    -------------------------------------------------- 2. LISTAR DUEÑOS ------------------------------------------------

    public void listarDuenos() {
        for (Dueno dueno : duenoDao.listarTodos()) {
            System.out.println(dueno);
        }
    }

//    ----------------------------------------------------3. LISTAR DUEÑOS ACTIVOS ------------------------------------------------

    public void listarDuenosActivos() {
        for (Dueno dueno : duenoDao.listarActivos()) {
            System.out.println(dueno);
        }
    }

//    ----------------------------------------------------4. LISTAR DUEÑOS INACTIVOS ------------------------------------------------

    public void listarDuenosInactivos() {
        for (Dueno dueno : duenoDao.listarInactivos()) {
            System.out.println(dueno);
        }
    }

//    ----------------------------------------------------5. BUSCAR DUEÑO -------------------------------------------------

    public Dueno buscarDuenoPorDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento inválido");
        }
        return duenoDao.listarPorDocumento(documento);
    }

//    -------------------------------------------------------6. ACTUALIZAR DUEÑO -------------------------------------------------

    public void actualizarDueno(Dueno dueno) {
        if (!validarDueno(dueno)) {
            throw new IllegalArgumentException("Datos de dueño inválidos");
        }
        duenoDao.actualizarDueno(dueno);
        System.out.println("Dueño actualizado con éxito");
    }

//    -------------------------------------------------------7. ELIMINAR DUEÑO -------------------------------------------------


    public void eliminarDueno(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento inválido");
        }
        duenoDao.eliminarDueno(documento);
        System.out.println("Dueño eliminado con éxito");
    }

//    -----------------------------------------------------8. VER MASCOTAS DE UN DUEÑO -------------------------------------------------
    public void verMascotasDeUnDueno(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento inválido");
        }
        duenoDao.verMascotasDeUnDueno(documento);
    }

//    ------------------------------------------------------ VALIDACIONES -------------------------------------------------

    private boolean validarDueno(Dueno dueno) {
        if (dueno == null) {
            System.out.println("❌ Dueño nulo");
            return false;
        }

        if (dueno.getNombre() == null || dueno.getNombre().trim().isEmpty()) {
            System.out.println("❌ Nombre inválido");
            return false;
        }

        if (dueno.getTelefono() == null || !dueno.getTelefono().matches("\\d{10}")) {
            System.out.println("❌ Teléfono inválido (debe tener 10 dígitos)");
            return false;
        }

        if (dueno.getDireccion() == null || dueno.getDireccion().trim().isEmpty()) {
            System.out.println("❌ Dirección inválida");
            return false;
        }

        if (dueno.getEmail() == null || !dueno.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            System.out.println("❌ Email inválido");
            return false;
        }

        return true;
    }
}
