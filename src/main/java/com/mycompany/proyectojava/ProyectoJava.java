package com.mycompany.proyectojava;

import com.mycompany.proyectojava.View.Citas.CitasView;
import com.mycompany.proyectojava.View.Consultas.ConsultasView;
import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.controller.Consultas.ConsultasController;
import com.mycompany.proyectojava.repository.Citas.CitasDAO;
import com.mycompany.proyectojava.repository.Citas.ICitas;
import com.mycompany.proyectojava.repository.Consultas.ConsultasDAO;
import com.mycompany.proyectojava.repository.Consultas.IConsultas;
import com.mycompany.proyectojava.service.Citas.CitasService;
import com.mycompany.proyectojava.controller.Citas.CitasController;
import com.mycompany.proyectojava.service.Consultas.ConsultasService;

import java.sql.Connection;

public class ProyectoJava {
    public static void main(String[] args) {
        try {
            System.out.println("=== SISTEMA DE GESTIÓN DE CITAS VETERINARIAS ===");
            Connection connection = ConexionDBSingleton.getInstance().getConnection();
            System.out.println("✓ Conexión a la base de datos establecida");
            IConsultas consultasDAO = new ConsultasDAO();
            ICitas citasDAO = new CitasDAO();// Repository/DAO
            ConsultasService consultasService = new ConsultasService(consultasDAO, citasDAO);// Service
            ConsultasController consultasController = new ConsultasController(consultasService);// Controller

            ConsultasView consultasView = new ConsultasView(consultasController);

            System.out.println("Sistema iniciado correctamente\n");

            consultasView.MostrarMenu();
            connection.close();
            System.out.println("Sistema finalizado.");

        } catch (Exception e) {
            System.out.println("❌ Error al iniciar el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}