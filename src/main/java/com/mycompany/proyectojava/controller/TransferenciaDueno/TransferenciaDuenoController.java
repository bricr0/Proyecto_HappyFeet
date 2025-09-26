package com.mycompany.proyectojava.controller.TransferenciaDueno;

import com.mycompany.proyectojava.model.entities.TransferenciaDueno.TransferenciaDueno;
import com.mycompany.proyectojava.repository.TransferenciaDueno.ITransferenciaDueno;

public class TransferenciaDuenoController {
    private ITransferenciaDueno transferenciaDuenoDAO;

    public TransferenciaDuenoController(ITransferenciaDueno transferenciaDuenoDAO) {
        this.transferenciaDuenoDAO = transferenciaDuenoDAO;
    }

//    -----------------------------------------------TRANSFERIR DUENO ------------------------------------------
    public void transferirDueno(TransferenciaDueno transferenciaDueno){

        transferenciaDuenoDAO.transferirDueno(transferenciaDueno);

    }
}
