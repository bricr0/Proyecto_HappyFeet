package com.mycompany.proyectojava.repository.Factura;

import com.mycompany.proyectojava.model.entities.Factura.Factura;

public interface IFactura {

//    --------------------------------------------------------------- 1. CREAR FACTURA ---------------------------------------------------------------
    void CrearFactura(Factura factura);

//    --------------------------------------------------------------- 2. ELIMINAR FACTURA ---------------------------------------------------------------

    void eliminarFactura();

}
