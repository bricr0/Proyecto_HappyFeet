package com.mycompany.proyectojava.controller.Veterinarios;

import com.mycompany.proyectojava.model.entities.Veterinarios.Veterinarios;
import com.mycompany.proyectojava.service.Veterinarios.VeterinariosService;

import java.util.List;

public class VeterinariosController {
    private VeterinariosService veterinariosService;

    public VeterinariosController(VeterinariosService veterinariosService) {
        this.veterinariosService = veterinariosService;
    }

    public String registrarVeterinario(String nombreCompleto, String telefono, String email) {
        try {
            Veterinarios nuevoVeterinario = new Veterinarios(null, nombreCompleto, telefono, email);
            boolean resultado = veterinariosService.registrarVeterinario(nuevoVeterinario);

            if (resultado) {
                return "✅ Veterinario registrado exitosamente con ID: " + nuevoVeterinario.getId();
            } else {
                return "❌ Error al registrar el veterinario";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Veterinarios> obtenerTodosLosVeterinarios() {
        return veterinariosService.listarTodosLosVeterinarios();
    }

    public Veterinarios obtenerVeterinario(Integer id) {
        try {
            return veterinariosService.obtenerVeterinarioPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener veterinario: " + e.getMessage());
            return null;
        }
    }

    public String actualizarVeterinario(Integer id, String nombreCompleto, String telefono, String email) {
        try {
            Veterinarios veterinario = new Veterinarios(id, nombreCompleto, telefono, email);
            boolean resultado = veterinariosService.actualizarVeterinario(veterinario);

            if (resultado) {
                return "✅ Veterinario actualizado exitosamente";
            } else {
                return "❌ Error al actualizar el veterinario";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public String eliminarVeterinario(Integer id) {
        try {
            boolean resultado = veterinariosService.eliminarVeterinario(id);

            if (resultado) {
                return "✅ Veterinario eliminado exitosamente";
            } else {
                return "❌ Error al eliminar el veterinario";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<Veterinarios> buscarVeterinariosPorNombre(String nombre) {
        try {
            return veterinariosService.buscarVeterinariosPorNombre(nombre);
        } catch (Exception e) {
            System.out.println("Error al buscar veterinarios: " + e.getMessage());
            return List.of(); // Lista vacía
        }
    }
}
