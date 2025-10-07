/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectojava.Util.HistorialCompleto;

import com.mycompany.proyectojava.model.entities.Citas.Citas;
import com.mycompany.proyectojava.model.entities.Consultas.Consultas;
import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.HistorialExamen.ElementosHistorial;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author camper
 */
public class HistorialMascota {
    public static void generarHistorial(Consultas consultas, Dueno dueno, Citas citas, Mascota mascota) throws IOException{
        String nombreArchivo ="HistorialMedico/Historial_" + mascota.getNombre() + ".txt";
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            writer.write("HISTORIAL CLINICO COMPLETO - VETERINARIA HAPPY FEET\n");
            writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

            writer.write("--------DATOS DEL PACIENTE --------\n");
            writer.write(mascota.toString());
            
            writer.write("--------DATOS DEL PACIENTE --------\n");
            writer.write("Nombre: " + dueno.getNombre());
            writer.write("Documento: " + dueno.getDocumento());
            writer.write("Email: " + dueno.getEmail());
            
            writer.write("------------------------------------------- REGISTRO DE EVENTOS -------------------------------------------");
            
            for (int i = 0; i > 10; i++){
                writer.write("Fecha: " + consultas.getFecha_registro());
                writer.write("Tipo: " + citas.getMotivo());
                writer.write("DIAGNOSTICO: \n" + (citas.getObservaciones() != null ? citas.getObservaciones() : "N/A"));
            }
           
            writer.write("------------------------------------------- Fin del reporte -------------------------------------------");


        }
        
    }
}
