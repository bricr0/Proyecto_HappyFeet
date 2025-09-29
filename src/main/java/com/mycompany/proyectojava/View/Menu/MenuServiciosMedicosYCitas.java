package com.mycompany.proyectojava.View.Menu;

import com.mycompany.proyectojava.View.Citas.CitasView;
import com.mycompany.proyectojava.View.Consultas.ConsultasView;
import com.mycompany.proyectojava.controller.Citas.CitasController;
import com.mycompany.proyectojava.controller.Consultas.ConsultasController;
import com.mycompany.proyectojava.repository.Citas.CitasDAO;
import com.mycompany.proyectojava.repository.Citas.ICitas;
import com.mycompany.proyectojava.repository.Consultas.ConsultasDAO;
import com.mycompany.proyectojava.repository.Consultas.IConsultas;
import com.mycompany.proyectojava.service.Citas.CitasService;
import com.mycompany.proyectojava.service.Consultas.ConsultasService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuServiciosMedicosYCitas {
    private Scanner input = new Scanner(System.in);

    public void MenuServiciosMedicosCitas() {
        Integer opcion = -1;
        CitasController citasController = new CitasController(new CitasService(new CitasDAO()));
        CitasView citasView = new CitasView(citasController);
        ICitas citasDao = new CitasDAO();
        IConsultas consultasDao = new ConsultasDAO();
        ConsultasController consultasController = new ConsultasController(new ConsultasService(consultasDao, citasDao));
        ConsultasView consultasView = new ConsultasView(consultasController);

        do {
            System.out.println("\n===== SERVICIOS MEDICOS Y CITAS =====");
            System.out.println("1. Citas");
            System.out.println("2. Consultas medicas");
            System.out.println("3. Procedimientos especiales");
            System.out.println("0. Volver al menú principal");
            System.out.print(">>> Elige una opción: ");

            try {
                opcion = Integer.parseInt(input.nextLine());

               Map<String, Runnable> funciones = new HashMap<>();
               funciones.put("1", ()->{
                   try{
                       citasView.MostrarMenu();
                   } catch (Exception e){
                       throw  new RuntimeException("Error" + e);
                   }
                });

               funciones.put("2", () -> {
                    try{
                        consultasView.MostrarMenu();
                    }catch (Exception e){
                        throw new RuntimeException("Error: " + e);
                    }
               });

               funciones.put("3", () ->{
                  try{

                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
               });

               Runnable funcion = funciones.get(String.valueOf(opcion));
               if (funcion != null){
                   funcion.run();
               } else {
                   System.out.println("Opcion invalida. Intente otra vez");
               }

            } catch (Exception e){
                throw  new RuntimeException("Error" + e);
            }


        } while (opcion != 0);
    }
}
