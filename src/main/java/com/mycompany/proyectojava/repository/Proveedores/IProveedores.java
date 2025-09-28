package com.mycompany.proyectojava.repository.Proveedores;

import com.mycompany.proyectojava.model.entities.Proveedores.Proveedores;
import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;

import java.util.List;

public interface IProveedores {
    //    ----------------------------------------------------1. AGREGAR PROVEEDOR ------------------------------------------------
    boolean registrarProveedor(Proveedores proveedor);

//    ----------------------------------------------------2. LISTAR PROVEEDORES ------------------------------------------------

    List<Proveedores> listarProveedores();

    //    ----------------------------------------------------3. LISTAR POR ID ------------------------------------------------
    Proveedores buscarProveedorPorId(Integer id);

//    ------------------------------------------------------------4. LISTAR POR NOMBRE ------------------------------------------------

    List<Proveedores> buscarProveedoresPorNombre(String nombre);

    //    ------------------------------------------------------------5. LISTAR POR CONTACTO ------------------------------------------------

    List<Proveedores> buscarProveedoresPorContacto(String contacto);

//    ----------------------------------------------------6. ACTUALIZAR PROVEEDOR ------------------------------------------------

    boolean actualizarProveedor(Proveedores proveedor);

//    ----------------------------------------------------7. ELIMINAR PROVEEDOR ------------------------------------------------

    boolean eliminarProveedor(Integer id);
}
