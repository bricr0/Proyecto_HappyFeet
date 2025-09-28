package com.mycompany.proyectojava.controller.AlertasInventario;

import com.mycompany.proyectojava.model.entities.AlertasInventario.AlertasInventario;
import com.mycompany.proyectojava.model.entities.Inventario.Inventario;
import com.mycompany.proyectojava.service.InventarioService.Alertas.AlertasInventarioService;

import java.util.List;

public class AlertasInventarioController {
    private AlertasInventarioService alertasService;

    public AlertasInventarioController(AlertasInventarioService alertasService) {
        this.alertasService = alertasService;
    }

    public String registrarAlerta(Inventario inventario, String tipoAlerta, String mensaje) {
        try {
            AlertasInventario nuevaAlerta = new AlertasInventario(null, inventario, tipoAlerta, mensaje, null, false);
            boolean resultado = alertasService.registrarAlerta(nuevaAlerta);

            if (resultado) {
                return "✅ Alerta registrada exitosamente con ID: " + nuevaAlerta.getId();
            } else {
                return "❌ Error al registrar la alerta";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }
    public String generarAlertasAutomaticas() {
        try {
            return "✅ Proceso de generación de alertas completado";
        } catch (Exception e) {
            return "❌ Error generando alertas automáticas: " + e.getMessage();
        }
    }

    public List<AlertasInventario> obtenerTodasLasAlertas() {
        return alertasService.listarTodasLasAlertas();
    }

    public AlertasInventario obtenerAlerta(Integer id) {
        try {
            return alertasService.obtenerAlertaPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener alerta: " + e.getMessage());
            return null;
        }
    }

    public String marcarComoLeida(Integer id) {
        try {
            boolean resultado = alertasService.marcarAlertaComoLeida(id);

            if (resultado) {
                return "✅ Alerta marcada como leída";
            } else {
                return "❌ Error al marcar la alerta como leída";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public String eliminarAlerta(Integer id) {
        try {
            boolean resultado = alertasService.eliminarAlerta(id);

            if (resultado) {
                return "✅ Alerta eliminada exitosamente";
            } else {
                return "❌ Error al eliminar la alerta";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    public List<AlertasInventario> buscarAlertasPorTipo(String tipo) {
        try {
            return alertasService.buscarAlertasPorTipo(tipo);
        } catch (Exception e) {
            System.out.println("Error al buscar alertas: " + e.getMessage());
            return List.of();
        }
    }

    public List<AlertasInventario> buscarAlertasNoLeidas() {
        try {
            return alertasService.buscarAlertasNoLeidas();
        } catch (Exception e) {
            System.out.println("Error al buscar alertas no leídas: " + e.getMessage());
            return List.of();
        }
    }

    public List<AlertasInventario> buscarAlertasPorProducto(Integer inventarioId) {
        try {
            return alertasService.buscarAlertasPorProducto(inventarioId);
        } catch (Exception e) {
            System.out.println("Error al buscar alertas por producto: " + e.getMessage());
            return List.of();
        }
    }

    public int contarAlertasNoLeidas() {
        try {
            return alertasService.contarAlertasNoLeidas();
        } catch (Exception e) {
            System.out.println("Error al contar alertas no leídas: " + e.getMessage());
            return 0;
        }
    }
}
