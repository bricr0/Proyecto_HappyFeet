package com.mycompany.proyectojava.controller.Proveedores;

import com.mycompany.proyectojava.model.entities.Proveedores.Proveedores;
import com.mycompany.proyectojava.service.Proveedores.ProveedoresService;

import java.util.List;

public class ProveedoresController {
    private ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    public String registrarProveedor(String nombre, String contacto, String telefono, String email) {
        try {
            Proveedores nuevoProveedor = new Proveedores(null, nombre, contacto, telefono, email);
            boolean resultado = proveedoresService.registrarProveedor(nuevoProveedor);

            if (resultado) {
                return "✅ Proveedor registrado exitosamente con ID: " + nuevoProveedor.getId();
            } else {
                return "❌ Error al registrar el proveedor";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Proveedores> obtenerTodosLosProveedores() {
        return proveedoresService.listarTodosLosProveedores();
    }

    public Proveedores obtenerProveedor(Integer id) {
        try {
            return proveedoresService.obtenerProveedorPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener proveedor: " + e.getMessage());
            return null;
        }
    }

    public String actualizarProveedor(Integer id, String nombre, String contacto, String telefono, String email) {
        try {
            Proveedores proveedor = new Proveedores(id, nombre, contacto, telefono, email);
            boolean resultado = proveedoresService.actualizarProveedor(proveedor);

            if (resultado) {
                return "✅ Proveedor actualizado exitosamente";
            } else {
                return "❌ Error al actualizar el proveedor";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public String eliminarProveedor(Integer id) {
        try {
            boolean resultado = proveedoresService.eliminarProveedor(id);

            if (resultado) {
                return "✅ Proveedor eliminado exitosamente";
            } else {
                return "❌ Error al eliminar el proveedor";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Proveedores> buscarProveedoresPorNombre(String nombre) {
        try {
            return proveedoresService.buscarProveedoresPorNombre(nombre);
        } catch (Exception e) {
            System.out.println("Error al buscar proveedores: " + e.getMessage());
            return List.of();
        }
    }

    public List<Proveedores> buscarProveedoresPorContacto(String contacto) {
        try {
            return proveedoresService.buscarProveedoresPorContacto(contacto);
        } catch (Exception e) {
            System.out.println("Error al buscar proveedores: " + e.getMessage());
            return List.of();
        }
    }
}
