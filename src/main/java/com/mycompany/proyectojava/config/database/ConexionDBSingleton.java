package com.mycompany.proyectojava.config.database;

import java.sql.*;
// Conexion como un singleton
public class ConexionDBSingleton {
    private static String host = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String password = "sebastian";
    private static String db = "pruebaLogin";

    private static String strConn = host + db;
    private static Connection connection;
    private static ConexionDBSingleton instancia;

    public ConexionDBSingleton() {
        this.instancia = null;
        this.connection = null;
    }

    public static ConexionDBSingleton getInstance(){
        if (instancia == null){
            instancia = new ConexionDBSingleton();
            try {
                if (connection == null || connection.isClosed()){
                    connection = DriverManager.getConnection(strConn, user, password);
                    System.out.println("Conexión exitosa");
                }
            } catch (SQLException e){
                connection = null;
                System.err.println("Error al conectar : " + e.getMessage());
            }
        }
        return instancia;

    }

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        try {
            if (connection != null || !connection.isClosed()){
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }

}