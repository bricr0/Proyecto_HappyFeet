package com.mycompany.proyectojava;

import com.mycompany.proyectojava.View.Mascota.MascotaView;
import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.controller.Especie.EspecieController;
import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.controller.Razas.RazaController;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Dueno.IDueno;
import com.mycompany.proyectojava.repository.Especie.EspecieDAO;
import com.mycompany.proyectojava.repository.Especie.IEspecie;
import com.mycompany.proyectojava.repository.Mascota.IMascota;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;
import com.mycompany.proyectojava.repository.Razas.IRazas;
import com.mycompany.proyectojava.repository.Razas.RazasDAO;

public class ProyectoJava {
    public static void main(String[] args) {
        // -------------------- 1. Crear DAOs --------------------
        IDueno duenoDAO = new DuenoDAO();
        IEspecie especieDAO = new EspecieDAO();
        IRazas razaDAO = new RazasDAO();
        IMascota mascotaDAO = new MascotaDAO();


        // -------------------- 2. Crear Controllers --------------------
        DuenoController duenoController = new DuenoController(duenoDAO);
        EspecieController especieController = new EspecieController(especieDAO);
        RazaController razaController = new RazaController(razaDAO);
        MascotaControlller mascotaController = new MascotaControlller(mascotaDAO);

        // -------------------- 3. Crear Views --------------------
        MascotaView mascotaView = new MascotaView(
                mascotaController,
                duenoController,
                razaController,
                especieController
        );

        // -------------------- 4. Ejecutar --------------------
        mascotaView.mostrarMenu();
    }
}
