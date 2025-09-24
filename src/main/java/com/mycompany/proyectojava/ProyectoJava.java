package com.mycompany.proyectojava;

import com.mycompany.proyectojava.View.Duenos.DuenosView;
import com.mycompany.proyectojava.View.Mascota.MascotaView;
import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Dueno.IDueno;
//import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
//import com.mycompany.proyectojava.repository.Mascota.IMascota;
//import com.mycompany.proyectojava.repository.Mascota.MascotaDAO;

public class ProyectoJava {
    public static void main(String[] args) {

        IDueno dao = new DuenoDAO();

//        IMascota dao = new MascotaDAO();
//        MascotaControlller controller = new MascotaControlller(dao);
//        MascotaView view = new MascotaView(controller);
//        view.mostrarMenu();

        DuenoController controller = new DuenoController(dao);

        DuenosView viewDuenos = new DuenosView(controller);

        viewDuenos.MostrarMenu();

    }
}
