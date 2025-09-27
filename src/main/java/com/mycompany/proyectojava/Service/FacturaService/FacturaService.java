package com.mycompany.proyectojava.Service.FacturaService;

import com.mycompany.proyectojava.Util.Factura.FacturaTxtGenerator;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Factura.Factura;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Dueno.IDueno;
import com.mycompany.proyectojava.repository.Factura.IFacturaDAO;

import java.util.List;

public class FacturaService {
    private IFacturaDAO facturaDAO;
    private DuenoDAO duenoDAO;
    private FacturaTxtGenerator txtGenerator;

    public FacturaService() {
        this.facturaDAO = new IFacturaDAO();
        this.duenoDAO = new DuenoDAO();
        this.txtGenerator = new FacturaTxtGenerator();
    }

    public void generarFacturaPorDocumento(String documentoDueno) {
        Dueno dueno = duenoDAO.listarPorDocumento(documentoDueno);

        if (dueno == null) {
            System.out.println("⚠️ No existe dueño con ese documento");
            return;
        }

        int duenoId = dueno.getId();

        List<Factura> facturas = facturaDAO.obtenerFacturasPorDuenoId(duenoId);

        if (facturas.isEmpty()) {
            System.out.println("⚠️ No hay facturas registradas para este dueño");
        } else {
            for (Factura factura : facturas) {
                FacturaTxtGenerator.generar(factura, dueno);
            }
            System.out.println("✅ Facturas exportadas en TXT para el dueño: " + dueno.getNombre());
        }
    }

}
