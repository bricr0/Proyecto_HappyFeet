package com.mycompany.proyectojava.View.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuInventario {
        private Scanner input = new Scanner(System.in);

        public void MenuInventario(){
            Integer opcion = -1;


            do{
                System.out.println("\n===== INVENTARIO =====");
                System.out.println("1. Control stock");
                System.out.println("2. Alertas inteligentes");
                System.out.println("3. Proveedores");
                System.out.println("0. Volver al menú principal");
                System.out.print(">>> Elige una opción: ");

                try{
                    opcion = Integer.parseInt(input.nextLine());
                    Map<String, Runnable> funciones = new HashMap<>();
                    funciones.put("1", ()->{
                       try{

                       } catch (Exception e){
                           throw  new RuntimeException("Error: " + e);
                       }
                    });
                } catch (Exception e){
                    throw  new RuntimeException("Error: " + e);
                }

            } while (opcion != 0);

        }

}
