package com.mycompany.proyectojava.View.Reportes;

import com.mycompany.proyectojava.controller.Reporte.ReporteController;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.FacturacionReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.InventarioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.ServicioReporte;
import com.mycompany.proyectojava.model.entities.ReportesGenerales.VeterinarioReporte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReporteView {

    private ReporteController controller;
    private Scanner input = new Scanner(System.in);

    public ReporteView(ReporteController controller) {
        this.controller = controller;
    }

    public void mostrarServiciosMasSolicitados() {
        List<ServicioReporte> lista = controller.obtenerServiciosMasSolicitados();
        System.out.println("\nServicio | Total Vendido");
        for (ServicioReporte row : lista) {
            System.out.printf("%s | %d%n",
                    row.getServicio(),
                    row.getVeces());
        }
    }

    public void mostrarDesempenoVeterinarios() {
        List<VeterinarioReporte> lista = controller.obtenerDesempenoVeterinarios();
        System.out.println("\nVeterinario | Consultas Atendidas");
        for (VeterinarioReporte row : lista) {
            System.out.printf("%s | %d%n",
                    row.getVeterinario(),
                    row.getConsultas());
        }
    }

    public void mostrarInventarioCritico() {
        List<InventarioReporte> lista = controller.obtenerInventarioCritico();
        System.out.println("\nProducto | Stock | Vence");
        for (InventarioReporte row : lista) {
            System.out.printf("%s | %d | %s%n",
                    row.getProducto(),
                    row.getStock(),
                    row.getFechaVencimiento());
        }
    }

    public void mostrarFacturacionPorPeriodo() {
        System.out.print("Ingrese el período (ej: '2025-09' o '2025-09-01'): ");
        String periodo = input.nextLine();
        List<FacturacionReporte> lista = controller.obtenerFacturacionPorPeriodo(periodo);
        System.out.println("\nPeríodo | Total Facturado");
        for (FacturacionReporte row : lista) {
            System.out.printf("%s | %.2f%n",
                    row.getPeriodo(),
                    row.getTotalFacturado());
        }
    }
}
