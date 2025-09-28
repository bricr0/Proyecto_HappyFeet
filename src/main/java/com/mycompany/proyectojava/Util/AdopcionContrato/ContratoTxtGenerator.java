package com.mycompany.proyectojava.Util.AdopcionContrato;

import com.mycompany.proyectojava.model.entities.Adopcion.Adopcion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ContratoTxtGenerator {
    public static void generarContrato(Adopcion adopcion) {
        String plantilla = """
        CONTRATO DE ADOPCIÓN DE MASCOTA

        Yo, {ADOPTANTE}, adopto a la mascota {MASCOTA} en la fecha {FECHA_ADOPCION}.

        Tipo de adopción: {TIPO}
        Estado: {ESTADO}

        Notas adicionales:
        {NOTAS}

        Firmas:
        Adoptante: _____________________
        Representante del refugio: _____________________
        """;

        String contrato = plantilla
                .replace("{ADOPTANTE}", adopcion.getNombreAdoptante() != null ? adopcion.getNombreAdoptante() : "")
                .replace("{MASCOTA}", adopcion.getNombreMascota() != null ? adopcion.getNombreMascota() : "")
                .replace("{FECHA_ADOPCION}", adopcion.getFechaAdopcion() != null ? adopcion.getFechaAdopcion().toLocalDate().toString() : "")
                .replace("{TIPO}", adopcion.getTipo() != null ? adopcion.getTipo().name() : "")
                .replace("{ESTADO}", adopcion.getEstado() != null ? adopcion.getEstado().name() : "")
                .replace("{NOTAS}", adopcion.getNotas() != null ? adopcion.getNotas() : "");


        try (PrintWriter out = new PrintWriter("Contratos/Contrato_" + adopcion.getNombreAdoptante() + ".txt")) {
            out.println(contrato);
        } catch (FileNotFoundException e) {
            System.out.println("Error al generar contrato: " + e.getMessage());
        }

        System.out.println("Contrato generado exitosamente para " + adopcion.getNombreAdoptante());
    }
}
