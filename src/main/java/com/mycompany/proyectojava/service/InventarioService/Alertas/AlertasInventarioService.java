package com.mycompany.proyectojava.service.InventarioService.Alertas;

import com.mycompany.proyectojava.model.entities.AlertasInventario.AlertasInventario;
import com.mycompany.proyectojava.model.entities.Inventario.Inventario;
import com.mycompany.proyectojava.repository.AlertasInventario.IAlertasInventario;
import com.mycompany.proyectojava.repository.Inventario.IInventario;

import java.util.Date;
import java.util.List;

public class AlertasInventarioService {
    private IAlertasInventario alertasDAO;
    private IInventario inventarioDAO;

    public AlertasInventarioService(IAlertasInventario alertasDAO, IInventario inventarioDAO) {
        this.alertasDAO = alertasDAO;
        this.inventarioDAO = inventarioDAO;
    }

    public boolean registrarAlerta(AlertasInventario alerta) {
        // Validaciones
        if (alerta.getInventario_id() == null || alerta.getInventario_id().getId() == null) {
            throw new IllegalArgumentException("El producto del inventario es obligatorio");
        }

        if (alerta.getTipo_alerta() == null || alerta.getTipo_alerta().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de alerta es obligatorio");
        }

        if (alerta.getMensaje() == null || alerta.getMensaje().trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje de la alerta es obligatorio");
        }

        // Verificar que el producto exista
        Inventario producto = inventarioDAO.buscarProductoPorId(alerta.getInventario_id().getId());
        if (producto == null) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + alerta.getInventario_id().getId());
        }

        // Si no tiene fecha, asignar fecha actual
        if (alerta.getFecha_creacion() == null) {
            alerta.setFecha_creacion(new Date());
        }

        // Si no tiene estado leído, asignar false
        if (alerta.getLeido() == null) {
            alerta.setLeido(false);
        }

        return alertasDAO.registrarAlerta(alerta);
    }

    public void generarAlertaStockBajo(Inventario producto) {
        if (producto.getCantidad_stock() <= producto.getStock_minimo()) {
            AlertasInventario alerta = new AlertasInventario(
                    null,
                    producto,
                    "STOCK_BAJO",
                    "Stock bajo para " + producto.getNombre_producto() +
                            ". Stock actual: " + producto.getCantidad_stock() +
                            ", Mínimo: " + producto.getStock_minimo(),
                    new Date(),
                    false
            );
            alertasDAO.registrarAlerta(alerta);
        }
    }

    public void generarAlertaVencimiento(Inventario producto, int diasAntelacion) {
        if (producto.getFecha_vencimiento() != null) {
            long diferencia = producto.getFecha_vencimiento().getTime() - new Date().getTime();
            long dias = diferencia / (1000 * 60 * 60 * 24);

            if (dias <= diasAntelacion && dias >= 0) {
                AlertasInventario alerta = new AlertasInventario(
                        null,
                        producto,
                        "VENCIMIENTO",
                        "Producto " + producto.getNombre_producto() +
                                " vence en " + dias + " días (Lote: " + producto.getLote() + ")",
                        new Date(),
                        false
                );
                alertasDAO.registrarAlerta(alerta);
            }
        }
    }

    public List<AlertasInventario> listarTodasLasAlertas() {
        return alertasDAO.listarAlertas();
    }

    public AlertasInventario obtenerAlertaPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return alertasDAO.buscarAlertaPorId(id);
    }

    public boolean marcarAlertaComoLeida(Integer id) {
        AlertasInventario alerta = alertasDAO.buscarAlertaPorId(id);
        if (alerta == null) {
            throw new IllegalArgumentException("No se encontró la alerta con ID: " + id);
        }

        alerta.setLeido(true);
        return alertasDAO.actualizarAlerta(alerta);
    }

    public boolean eliminarAlerta(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        // Verificar que la alerta exista
        AlertasInventario alerta = alertasDAO.buscarAlertaPorId(id);
        if (alerta == null) {
            throw new IllegalArgumentException("No se encontró la alerta con ID: " + id);
        }

        return alertasDAO.eliminarAlerta(id);
    }

    public List<AlertasInventario> buscarAlertasPorTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            return listarTodasLasAlertas();
        }
        return alertasDAO.buscarAlertasPorTipo(tipo.trim());
    }

    public List<AlertasInventario> buscarAlertasNoLeidas() {
        return alertasDAO.buscarAlertasNoLeidas();
    }

    public List<AlertasInventario> buscarAlertasPorProducto(Integer inventarioId) {
        if (inventarioId == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return alertasDAO.buscarAlertasPorProducto(inventarioId);
    }

    public int contarAlertasNoLeidas() {
        return alertasDAO.buscarAlertasNoLeidas().size();
    }
}
