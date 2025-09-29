package com.mycompany.proyectojava.repository.Reporte;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.FacturacionReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.InventarioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.ServicioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.VeterinarioReporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteDAO implements IReporteDAO{


    private Connection conexion;

    public ReporteDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

    @Override
    public List<ServicioReporte> serviciosMasSolicitados() {
        List<ServicioReporte> lista = new ArrayList<>();
        String sql = """
            SELECT descripcion AS servicio, COUNT(*) AS veces
            FROM elementos_factura
            WHERE tipo IN ('servicio','consulta')
            GROUP BY descripcion
            ORDER BY veces DESC
            LIMIT 10
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new ServicioReporte(
                        rs.getString("servicio"),
                        rs.getInt("veces")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener servicios más solicitados: " + e);
        }
        return lista;
    }

    @Override
    public List<VeterinarioReporte> desempenoVeterinarios() {
        List<VeterinarioReporte> lista = new ArrayList<>();
        String sql = """
            SELECT v.nombre_completo AS veterinario, COUNT(c.id) AS consultas
            FROM consultas c
            JOIN veterinarios v ON c.veterinario_id = v.id
            GROUP BY v.id, v.nombre_completo
            ORDER BY consultas DESC
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new VeterinarioReporte(
                        rs.getString("veterinario"),
                        rs.getInt("consultas")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener desempeño de veterinarios: " + e);
        }
        return lista;
    }

    @Override
    public List<InventarioReporte> inventarioCritico() {
        List<InventarioReporte> lista = new ArrayList<>();
        String sql = """
            SELECT nombre_producto, cantidad_stock, stock_minimo, fecha_vencimiento
            FROM inventario
            WHERE cantidad_stock < stock_minimo
               OR fecha_vencimiento <= DATE_ADD(CURDATE(), INTERVAL 30 DAY)
            ORDER BY fecha_vencimiento ASC
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new InventarioReporte(
                        rs.getString("nombre_producto"),
                        rs.getInt("cantidad_stock"),
                        rs.getInt("stock_minimo"),
                        rs.getDate("fecha_vencimiento")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener inventario crítico: " + e);
        }
        return lista;
    }

    public List<FacturacionReporte> facturacionPorPeriodo(String fecha) {
        String sql = """
        SELECT SUM(total) AS total_facturado
        FROM facturas
        WHERE estado = 'pagada'
          AND DATE_FORMAT(fecha_emision, '%Y-%m') = ?
    """;

        List<FacturacionReporte> lista = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, fecha);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new FacturacionReporte(fecha, rs.getBigDecimal("total_facturado")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en facturación por período: " + e);
        }
        return lista;
    }
}
