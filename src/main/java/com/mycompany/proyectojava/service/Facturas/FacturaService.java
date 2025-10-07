package com.mycompany.proyectojava.service.Facturas;

import com.mycompany.proyectojava.Util.Factura.FacturaConsolaGenerator;
import com.mycompany.proyectojava.Util.Factura.FacturaNoExisteConsolaGenerator;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.Factura.Factura;
import com.mycompany.proyectojava.repository.Dueno.DuenoDAO;
import com.mycompany.proyectojava.repository.Factura.IFacturaDAO;

import java.util.List;

public class FacturaService {
    private IFacturaDAO facturaDAO;
    private DuenoDAO duenoDAO;
    private FacturaConsolaGenerator consolaGenerator;
    private FacturaNoExisteConsolaGenerator NoExisteConsolaGenerator;

    public FacturaService() {
        this.facturaDAO = new IFacturaDAO();
        this.duenoDAO = new DuenoDAO();
        this.consolaGenerator = new FacturaConsolaGenerator();
        this.NoExisteConsolaGenerator = new FacturaNoExisteConsolaGenerator();
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
            for (Factura factura : facturas) {
              FacturaNoExisteConsolaGenerator.generar(factura, dueno);
            }
        } else {
            for (Factura factura : facturas) {
                FacturaConsolaGenerator.generar(factura, dueno);
            }
            System.out.println("✅ Facturas exportadas en Consola para el dueño: " + dueno.getNombre());
        }
    }

}
