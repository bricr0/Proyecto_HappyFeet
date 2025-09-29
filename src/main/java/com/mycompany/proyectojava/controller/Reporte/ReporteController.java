package com.mycompany.proyectojava.controller.Reporte;

import com.mycompany.proyectojava.model.entities.ReportesGenerales.FacturacionReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.InventarioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.ServicioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.VeterinarioReporte;
import com.mycompany.proyectojava.repository.Reporte.IReporteDAO;

import java.util.List;
import java.util.Map;

public class ReporteController {
    private IReporteDAO dao;

    public ReporteController(IReporteDAO dao) {
        this.dao = dao;
    }

    public List<ServicioReporte> obtenerServiciosMasSolicitados() {
        return dao.serviciosMasSolicitados();
    }

    public List<VeterinarioReporte> obtenerDesempenoVeterinarios() {
        return dao.desempenoVeterinarios();
    }

    public List<InventarioReporte> obtenerInventarioCritico() {
        return dao.inventarioCritico();
    }

    public List<FacturacionReporte> obtenerFacturacionPorPeriodo(String periodo) {
        return dao.facturacionPorPeriodo(periodo);
    }
}
