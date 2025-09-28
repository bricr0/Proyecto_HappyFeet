package com.mycompany.proyectojava.service.Proveedores;

import com.mycompany.proyectojava.model.entities.Proveedores.Proveedores;
import com.mycompany.proyectojava.repository.Proveedores.IProveedores;

import java.util.List;

public class ProveedoresService {
    private IProveedores proveedoresDAO;

    public ProveedoresService(IProveedores proveedoresDAO) {
        this.proveedoresDAO = proveedoresDAO;
    }

    public boolean registrarProveedor(Proveedores proveedor) {
        // Validaciones
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }

        if (proveedor.getContacto() == null || proveedor.getContacto().trim().isEmpty()) {
            throw new IllegalArgumentException("El contacto es obligatorio");
        }

        if (proveedor.getTelofono() == null || proveedor.getTelofono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }

        // Validar formato de teléfono básico (al menos 8 dígitos)
        if (!proveedor.getTelofono().matches(".*\\d{8,}.*")) {
            throw new IllegalArgumentException("El teléfono debe contener al menos 8 dígitos");
        }

        if (proveedor.getEmail() != null && !proveedor.getEmail().trim().isEmpty()) {
            if (!proveedor.getEmail().contains("@")) {
                throw new IllegalArgumentException("El email debe tener un formato válido");
            }
        }

        return proveedoresDAO.registrarProveedor(proveedor);
    }

    public List<Proveedores> listarTodosLosProveedores() {
        return proveedoresDAO.listarProveedores();
    }

    public Proveedores obtenerProveedorPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return proveedoresDAO.buscarProveedorPorId(id);
    }

    public boolean actualizarProveedor(Proveedores proveedor) {
        if (proveedor.getId() == null) {
            throw new IllegalArgumentException("El ID del proveedor es obligatorio para actualizar");
        }

        // Verificar que el proveedor exista
        Proveedores existente = proveedoresDAO.buscarProveedorPorId(proveedor.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No se encontró el proveedor con ID: " + proveedor.getId());
        }

        return proveedoresDAO.actualizarProveedor(proveedor);
    }

    public boolean eliminarProveedor(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        // Verificar que el proveedor exista
        Proveedores proveedor = proveedoresDAO.buscarProveedorPorId(id);
        if (proveedor == null) {
            throw new IllegalArgumentException("No se encontró el proveedor con ID: " + id);
        }

        return proveedoresDAO.eliminarProveedor(id);
    }

    public List<Proveedores> buscarProveedoresPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return listarTodosLosProveedores();
        }
        return proveedoresDAO.buscarProveedoresPorNombre(nombre.trim());
    }

    public List<Proveedores> buscarProveedoresPorContacto(String contacto) {
        if (contacto == null || contacto.trim().isEmpty()) {
            return listarTodosLosProveedores();
        }
        return proveedoresDAO.buscarProveedoresPorContacto(contacto.trim());
    }
}
