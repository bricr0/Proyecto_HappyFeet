package com.mycompany.proyectojava;

import com.mycompany.proyectojava.View.Mascota.MascotaView;
import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.repository.Mascota.IMascota;
import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;

public class ProyectoJava {
    public static void main(String[] args) {
        IMascota dao = new MascotaDAO();

        MascotaControlller controller = new MascotaControlller(dao);

        MascotaView view = new MascotaView(controller);

        view.mostrarMenu();
    }
}
