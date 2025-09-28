package com.mycompany.proyectojava.repository.AlertasInventario;

import com.mycompany.proyectojava.model.entities.AlertasInventario.AlertasInventario;
import com.mycompany.proyectojava.model.entities.Inventario.Inventario;

import java.util.List;

public interface IAlertasInventario {
    //    ----------------------------------------------------1. AGREGAR PRODUCTO ------------------------------------------------
    boolean registrarAlerta(AlertasInventario alerta);

//    ----------------------------------------------------2. LISTAR PRODUCTOS ------------------------------------------------

    List<AlertasInventario> listarAlertas();

    //    ----------------------------------------------------3. LISTAR POR ID ------------------------------------------------
    AlertasInventario buscarAlertaPorId(Integer id);


    //    ------------------------------------------------------------5. LISTAR POR TIPO ------------------------------------------------

    List<AlertasInventario> buscarAlertasPorTipo(String tipo);

    //    ------------------------------------------------------------4. LISTAR POR PRODUCTOS BAJOS -------------------------------------------

    List<AlertasInventario> buscarAlertasPorProducto(Integer inventarioId);

    //    ------------------------------------------------------------4. LISTAR POR PORDUCTOS A VENCER -------------------------------------------

    List<AlertasInventario> buscarAlertasNoLeidas();

//    ----------------------------------------------------6. ACTUALIZAR PRODUCTO ------------------------------------------------

    boolean actualizarAlerta(AlertasInventario alerta);

//    ----------------------------------------------------7. ELIMINAR PRODUCTO ------------------------------------------------

    boolean eliminarAlerta(Integer id);
}
