package com.mycompany.proyectojava.repository.Factura;

import com.mycompany.proyectojava.Util.Factura.FacturaTxtGenerator;
import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Factura.ElementosFactura.ElementosFactura;
import com.mycompany.proyectojava.model.entities.Factura.Factura;
import com.mycompany.proyectojava.model.enums.Factura.FacturaEstado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IFacturaDAO implements IFactura{
    private Connection conexion;

    public IFacturaDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }


    @Override
    public void CrearFactura(Factura factura) {
        try {
            String sqlFactura = "INSERT INTO facturas (dueno_id, fecha_emision, subtotal, impuestos, total, estado) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psFactura = conexion.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            psFactura.setInt(1, factura.getDuenoId());
            psFactura.setTimestamp(2, Timestamp.valueOf(factura.getFechaEmision()));
            psFactura.setBigDecimal(3, factura.getSubtotal());
            psFactura.setBigDecimal(4, factura.getImpuestos());
            psFactura.setBigDecimal(5, factura.getTotal());
            psFactura.setString(6, factura.getEstado().name());
            psFactura.executeUpdate();

            ResultSet rs = psFactura.getGeneratedKeys();
            if (rs.next()) {
                factura.setId(rs.getInt(1));
            }

            String sqlElemento = "INSERT INTO elementos_factura " +
                    "(factura_id, producto_id, descripcion, cantidad, precio_unitario, tipo, consulta_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psElemento = conexion.prepareStatement(sqlElemento);

            for (ElementosFactura elem : factura.getElementos()) {
                psElemento.setInt(1, factura.getId());

                if (elem.getProductoId() != null) {
                    psElemento.setInt(2, elem.getProductoId());
                } else {
                    psElemento.setNull(2, java.sql.Types.INTEGER);
                }

                psElemento.setString(3, elem.getDescripcion());
                psElemento.setInt(4, elem.getCantidad());
                psElemento.setBigDecimal(5, elem.getPrecioUnitario());
                psElemento.setString(6, elem.getTipo().name()); // Enum mapeado

                if (elem.getConsultaId() != null) {
                    psElemento.setInt(7, elem.getConsultaId());
                } else {
                    psElemento.setNull(7, java.sql.Types.INTEGER);
                }

                psElemento.addBatch();
            }
            psElemento.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Factura> obtenerFacturasPorDuenoId(Integer duenoId) {
        List<Factura> facturas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM facturas WHERE dueno_id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, duenoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id"),
                        rs.getInt("dueno_id"),
                        rs.getTimestamp("fecha_emision").toLocalDateTime(),
                        rs.getBigDecimal("subtotal"),
                        rs.getBigDecimal("impuestos"),
                        rs.getBigDecimal("total"),
                        FacturaEstado.valueOf(rs.getString("estado")),
                        new ArrayList<>()
                );

                String sqlElem = "SELECT * FROM elementos_factura WHERE factura_id = ?";
                PreparedStatement psElem = conexion.prepareStatement(sqlElem);
                psElem.setInt(1, factura.getId());
                ResultSet rsElem = psElem.executeQuery();

                while (rsElem.next()) {
                    ElementosFactura elem = new ElementosFactura(
                            rsElem.getString("descripcion"),
                            rsElem.getInt("cantidad"),
                            rsElem.getBigDecimal("precio_unitario"),
                            rsElem.getBigDecimal("subtotal")
                    );
                    factura.getElementos().add(elem);
                }

                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public void eliminarFactura() {

    }
}
