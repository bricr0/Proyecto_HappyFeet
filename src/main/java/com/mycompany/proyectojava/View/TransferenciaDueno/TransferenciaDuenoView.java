package com.mycompany.proyectojava.View.TransferenciaDueno;

import com.mycompany.proyectojava.controller.Dueno.DuenoController;
import com.mycompany.proyectojava.controller.Mascota.MascotaControlller;
import com.mycompany.proyectojava.controller.TransferenciaDueno.TransferenciaDuenoController;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.model.entities.TransferenciaDueno.TransferenciaDueno;
import java.util.Scanner;

public class TransferenciaDuenoView {
    private final DuenoController duenoController;
    private final TransferenciaDuenoController controller;
    private final MascotaControlller mascotaControlller;
    private final Scanner input;


    public TransferenciaDuenoView(DuenoController duenoController, TransferenciaDuenoController controller, MascotaControlller mascotaControlller) {
        this.duenoController = duenoController;
        this.controller = controller;
        this.mascotaControlller = mascotaControlller;
        this.input = new Scanner (System.in);
    }


    public void transferirDueno() {
        System.out.println("\n************** TRANSFERIR DUEÑO **************");

        System.out.print("Ingrese el documento del dueño original: ");
        String documento = input.nextLine();
        Dueno duenoActual = duenoController.buscarDuenoPorDocumento(documento);

        if (duenoActual == null) {
            System.out.println("❌ No se puede transferir una mascota de un dueño que no existe.");
            return;
        }

        System.out.print("Ingrese el documento del nuevo dueño: ");
        String documentoNuevo = input.nextLine();
        Dueno nuevoDueno = duenoController.buscarDuenoPorDocumento(documentoNuevo);

        if (nuevoDueno == null) {
            System.out.println("❌ El nuevo dueño no existe, debe registrarlo primero.");
            return;
        }

        System.out.print("Ingrese el microchip de la mascota a transferir: ");
        String microchip = input.nextLine();
        Mascota mascota = mascotaControlller.buscarMascotaPorMicrochip(microchip);

        if (mascota == null) {
            System.out.println("❌ No se encontró ninguna mascota con ese microchip.");
            return;
        }

        TransferenciaDueno transferenciaDueno = new TransferenciaDueno(
                mascota.getId(),
                nuevoDueno.getId()
        );

        try {
            controller.transferirDueno(transferenciaDueno);
            System.out.println("✅ Transferencia realizada con éxito.");
        } catch (Exception e) {
            System.out.println("❌ Error al transferir: " + e.getMessage());
        }
    }


}

