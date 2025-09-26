package com.mycompany.proyectojava.controller.Mascota;

import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.repository.Mascota.IMascota;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;

public class MascotaControlller {
    private IMascota mascotaDao;

    public MascotaControlller(IMascota mascotaDao) {
        this.mascotaDao = mascotaDao;
    }

//    ----------------------------------------------------1. AGREGAR MASCOTA ------------------------------------------------

    public void agregarMascota(Mascota mascota) {
        if (!validarMascota(mascota)) {
            throw new IllegalArgumentException("Datos de mascota inválidos");
        }
        mascotaDao.agregarMascota(mascota);
        System.out.println("Mascota creada con éxito");
    }

//    ----------------------------------------------------2. LISTAR MASCOTAS ------------------------------------------------

    public void listarMascotas() {

        mascotaDao.listarTodas().forEach(m -> System.out.println(m));

    }

//    ----------------------------------------------------3. LISTAR MASCOTAS ACTIVAS ------------------------------------------------

    public void listarMascotasActivas() {

        mascotaDao.listarActivas().forEach(m -> System.out.println(m));
    }

//    ----------------------------------------------------4. LISTAR MASCOTAS INACTIVAS ------------------------------------------------

    public void listarMascotasInactivas() {

        mascotaDao.listarInactivas().forEach(m -> System.out.println(m));
    }

//    ----------------------------------------------------5. BUSCAR MASCOTA -------------------------------------------------

    public Mascota buscarMascotaPorMicrochip(String microchip) {
        if (microchip == null || microchip.trim().isEmpty()) {
            throw new IllegalArgumentException("Microchip inválido");
        }
        return mascotaDao.listarPorMicrochip(microchip);
    }

//    ----------------------------------------------------6. ACTUALIZAR MASCOTA -------------------------------------------------

    public void actualizarMascota(Mascota mascota) {
        if (!validarMascota(mascota)) {
            throw new IllegalArgumentException("Datos de mascota inválidos");
        }
        mascotaDao.actualizarMascota(mascota);
        System.out.println("Mascota actualizada con éxito");
    }

//    -------------------------------------------------------7. ELIMINAR MASCOTA -------------------------------------------------

    public void eliminarMascota(String microchip) {
        if (microchip == null || microchip.trim().isEmpty()) {
            throw new IllegalArgumentException("Microchip inválido");
        }
        mascotaDao.eliminarMascota(microchip);
        System.out.println("Mascota eliminada con éxito");
    }
//    -------------------------------------------------------- VALIDACIONES ------------------------------------------------

    private boolean validarMascota(Mascota mascota) {
        if (mascota == null) {
            System.out.println("❌ Mascota nula");
            return false;
        }

        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            System.out.println("❌ Nombre inválido");
            return false;
        }

        if (mascota.getSexo() == null ||
                !(mascota.getSexo().equalsIgnoreCase("Macho") || mascota.getSexo().equalsIgnoreCase("Hembra"))) {
            System.out.println("❌ Sexo inválido (usa 'M' o 'F')");
            return false;
        }

        if (mascota.getPeso_kg() != null && mascota.getPeso_kg() <= 0) {
            System.out.println("❌ Peso inválido");
            return false;
        }

        if (mascota.getFecha_nacimiento() != null &&
                mascota.getFecha_nacimiento().after(new java.util.Date())) {
            System.out.println("❌ Fecha de nacimiento en el futuro");
            return false;
        }

        return true;
    }


}
