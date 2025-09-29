package com.mycompany.proyectojava.repository.Reporte;

import com.mycompany.proyectojava.model.entities.ReportesGenerales.FacturacionReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.InventarioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.ServicioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.VeterinarioReporte;

import java.util.List;

public interface IReporteDAO {
    List<ServicioReporte> serviciosMasSolicitados();
    List<VeterinarioReporte> desempenoVeterinarios();
    List<InventarioReporte> inventarioCritico();
    List<FacturacionReporte> facturacionPorPeriodo(String periodo);
}
