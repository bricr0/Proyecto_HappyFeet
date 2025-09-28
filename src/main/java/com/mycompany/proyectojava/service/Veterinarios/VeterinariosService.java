package com.mycompany.proyectojava.service.Veterinarios;

import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;
import com.mycompany.proyectojava.repository.Veterinarios.IVeterinarios;
import com.mycompany.proyectojava.repository.Veterinarios.VeterinariosDAO;

import java.util.List;

public class VeterinariosService {
    private IVeterinarios veterinariosDAO;

    public VeterinariosService(IVeterinarios veterinariosDAO) {
        this.veterinariosDAO = veterinariosDAO;
    }

    public boolean registrarVeterinario(Veterinarios veterinario) {
        // Validaciones
        if (veterinario.getNombre_completo() == null || veterinario.getNombre_completo().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }

        if (veterinario.getTelefono() == null || veterinario.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }

        if (veterinario.getEmail() == null || veterinario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        // Validar formato de email básico
        if (!veterinario.getEmail().contains("@")) {
            throw new IllegalArgumentException("El email debe tener un formato válido");
        }

        return veterinariosDAO.registrarVeterinario(veterinario);
    }

    public List<Veterinarios> listarTodosLosVeterinarios() {
        return veterinariosDAO.listarVeterinarios();
    }

    public Veterinarios obtenerVeterinarioPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return veterinariosDAO.buscarVeterinarioPorId(id);
    }

    public boolean actualizarVeterinario(Veterinarios veterinario) {
        if (veterinario.getId() == null) {
            throw new IllegalArgumentException("El ID del veterinario es obligatorio para actualizar");
        }

        // Verificar que el veterinario exista
        Veterinarios existente = veterinariosDAO.buscarVeterinarioPorId(veterinario.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No se encontró el veterinario con ID: " + veterinario.getId());
        }

        return veterinariosDAO.actualizarVeterinario(veterinario);
    }

    public boolean eliminarVeterinario(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        // Verificar que el veterinario exista
        Veterinarios veterinario = veterinariosDAO.buscarVeterinarioPorId(id);
        if (veterinario == null) {
            throw new IllegalArgumentException("No se encontró el veterinario con ID: " + id);
        }

        // Aquí podrías agregar validaciones adicionales (ej: no eliminar si tiene citas pendientes)

        return veterinariosDAO.eliminarVeterinario(id);
    }

    public List<Veterinarios> buscarVeterinariosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return listarTodosLosVeterinarios();
        }
        return veterinariosDAO.buscarVeterinariosPorNombre(nombre.trim());
    }
}
