/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espesw.catalogoonline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author edgarlopez
 */
public class ConexionDb {
    public static Connection getConnection(){
        Connection conexion = null;
        try{
            String urldb = "jdbc:mysql://localhost:3306/Pelicula";
            String usuariodb = "root";
            String passworddb = "1104264112";
            
            conexion=DriverManager.getConnection(urldb, usuariodb,passworddb);
            
            System.out.println("Conectado a la base de datos!!");
        }catch(SQLException e){
            //thrown e;
            System.err.println("Error de conexion a la Base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
