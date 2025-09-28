package com.mycompany.proyectojava.repository.Inventario;

import com.mycompany.proyectojava.model.entities.Inventario.Inventario;

import java.util.List;

public interface IInventario {

    //    ----------------------------------------------------1. AGREGAR PRODUCTO ------------------------------------------------
    boolean registrarProducto(Inventario producto);

//    ----------------------------------------------------2. LISTAR PRODUCTOS ------------------------------------------------

    List<Inventario> listarProductos();

    //    ----------------------------------------------------3. LISTAR POR ID ------------------------------------------------
    Inventario buscarProductoPorId(Integer id);

//    ------------------------------------------------------------4. LISTAR POR NOMBRE ------------------------------------------------

    List<Inventario> buscarProductosPorNombre(String nombre);

    //    ------------------------------------------------------------5. LISTAR POR TIPO ------------------------------------------------

    List<Inventario> buscarProductosPorTipo(String tipo);

    //    ------------------------------------------------------------4. LISTAR POR PRODUCTOS BAJOS -------------------------------------------

    List<Inventario> buscarProductosStockBajo();

    //    ------------------------------------------------------------4. LISTAR POR PORDUCTOS A VENCER -------------------------------------------

    List<Inventario> buscarProductosProximosAVencer(int dias);

//    ----------------------------------------------------6. ACTUALIZAR PRODUCTO ------------------------------------------------

    boolean actualizarProducto(Inventario producto);

//    ----------------------------------------------------7. ELIMINAR PRODUCTO ------------------------------------------------

    boolean eliminarProducto(Integer id);
}
