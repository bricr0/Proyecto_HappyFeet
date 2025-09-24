package com.mycompany.proyectojava.controller.Mascota;

import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.repository.Mascota.IMascota;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;

public class MascotaControlller {
    private IMascota mascotaDao;

    public MascotaControlller(IMascota mascotaDao) {
        this.mascotaDao = mascotaDao;
    }

    public void agregarMascota(Mascota mascota) {
        if (!validarMascota(mascota)) {
            throw new IllegalArgumentException("Datos de mascota inválidos");
        }
        mascotaDao.agregarMascota(mascota);
        System.out.println("Mascota creada con éxito");
    }

    private boolean validarMascota(Mascota mascota) {
        if (mascota == null) {
            System.out.println("❌ Mascota nula");
            return false;
        }

        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            System.out.println("❌ Nombre inválido");
            return false;
        }

        if (mascota.getDueno_id() == null || mascota.getDueno_id() <= 0) {
            System.out.println("❌ ID de dueño inválido");
            return false;
        }

        if (mascota.getRaza_id() == null || mascota.getRaza_id() <= 0) {
            System.out.println("❌ ID de raza inválido");
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
