package com.mycompany.proyectojava.repository.Proveedores;

import com.mycompany.proyectojava.model.entities.Proveedores.Proveedores;
import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;

import java.util.List;

public interface IProveedores {
    //    ----------------------------------------------------1. AGREGAR RAZA ------------------------------------------------
    boolean registrarProveedor(Proveedores proveedor);

//    ----------------------------------------------------2. LISTAR RAZAS ------------------------------------------------

    List<Proveedores> listarProveedores();

    //    ----------------------------------------------------3. LISTAR POR ESPECIE ------------------------------------------------
    Proveedores buscarProveedorPorId(Integer id);

//    ------------------------------------------------------------4. LISTAR POR NOMBRE ------------------------------------------------

    List<Proveedores> buscarProveedoresPorNombre(String nombre);
    List<Proveedores> buscarProveedoresPorContacto(String contacto);
//    ----------------------------------------------------5. ACTUALIZAR RAZA ------------------------------------------------

    boolean actualizarProveedor(Proveedores proveedor);

//    ----------------------------------------------------6. ELIMINAR RAZA ------------------------------------------------

    boolean eliminarProveedor(Integer id);
}
