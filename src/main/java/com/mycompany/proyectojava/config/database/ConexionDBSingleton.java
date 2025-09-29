package com.mycompany.proyectojava.config.database;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class ConexionDBSingleton {

    private static Dotenv dotenv = Dotenv.load();
    private static String host = dotenv.get("DB_HOST");
    private static String user = dotenv.get("DB_USER");
    private static String password = dotenv.get("DB_PASSWORD");
    private static String db = dotenv.get("DB_NAME");
    private static String strConn =  host + db;

    private static Connection connection;
    private static ConexionDBSingleton instancia;

    private ConexionDBSingleton() {}

    public static synchronized ConexionDBSingleton getInstance(){
        if (instancia == null){
            instancia = new ConexionDBSingleton();
            try {
                if (connection == null || connection.isClosed()){
                    Class.forName("com.mysql.cj.jdbc.Driver"); // 🔑 cargar driver
                    connection = DriverManager.getConnection(strConn, user, password);
                }
            } catch (SQLException e){
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());


                // Verifica si el driver está disponible
                System.err.println("Drivers disponibles:");
                DriverManager.drivers().forEach(driver ->
                        System.err.println(" - " + driver.getClass().getName()));

                connection = null;
                instancia = null;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return instancia;
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
                connection = null;
                instancia = null;
            }
        } catch (SQLException e){
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}