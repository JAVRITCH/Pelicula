/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espesw.catalogoonline;
//IMPORTS
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Pelicula {
    private int id;
    private String nombre;
    private int anoLanzamiento;
    private String genero;
    private int duracion;
    private String posterUrl;
    
    // CONSTRUCTOR 
    public Pelicula(int id , String nombre, int anoLanzamiento, String genero, int duracion) {
        this.id = id;
        this.nombre = nombre;
        this.anoLanzamiento = anoLanzamiento;
        this.genero = genero;
        this.duracion = duracion;
    }
    
    public Pelicula() {
        // Constructor vacío
    }
    // 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public int getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnoLanzamiento(int anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    
    
    public void guardarDatosPelicula() {
        Connection conexionDb = null;
    PreparedStatement parametro = null;

    try {
        // Establecer la conexión a la base de datos
        conexionDb = ConexionDb.getConnection();

        // Crear la sentencia SQL para la inserción
        String sentenciaSql = "INSERT INTO Pelicula (nombre, anoLanzamiento, genero, duracion) VALUES (?, ?, ?, ?)";

        // Configurar los parámetros de la sentencia SQL
        parametro = conexionDb.prepareStatement(sentenciaSql);
        parametro.setString(1, this.getNombre());
        parametro.setInt(2, this.getAnoLanzamiento());
        parametro.setString(3, this.getGenero());
        parametro.setInt(4, this.getDuracion());

        // Ejecutar la sentencia SQL
        parametro.executeUpdate();

        // Cerrar la conexión
        conexionDb.close();

        System.out.println("Registro guardado correctamente en la base de datos.");
    } catch (SQLException ex) {
        System.out.println("Error al intentar guardar el registro en la base de datos: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        // Cerrar los recursos en un bloque finally para asegurar que se cierren incluso si ocurre una excepción
        try {
            if (parametro != null) {
                parametro.close();
            }
            if (conexionDb != null) {
                conexionDb.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión o el PreparedStatement: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    }

    public void modificarDatosPelicula() {
        Connection conexionDb = ConexionDb.getConnection();

        // Ejecutar operaciones en la BD
        // Crear la sentencia SQL
        String sentenciaSql = "UPDATE pelicula SET nombre=?, anoLanzamiento=?, genero=?, duracion=?, WHERE id=?";
        try {
            // Configurar los parámetros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getNombre());
            parametro.setInt(2, this.getAnoLanzamiento());
            parametro.setString(3, this.getGenero());
            parametro.setInt(4, this.getDuracion());

            // Ejecutar la sentencia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Pelicula> obtenerPeliculas() {
        Connection conexionDb = ConexionDb.getConnection();
        ResultSet rsPeliculas;

        var peliculas = new ArrayList<Pelicula>();
        // Ejecutar operaciones en la BD
        // Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM pelicula";
        try {
            // Configurar los parámetros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);

            // Ejecutar la sentencia SQL
            rsPeliculas = parametro.executeQuery();

            while (rsPeliculas.next()) {
              peliculas.add(new Pelicula(rsPeliculas.getInt("id"), rsPeliculas.getString("nombre"), rsPeliculas.getInt("anoLanzamiento"),
                        rsPeliculas.getString("genero"), rsPeliculas.getInt("duracion")));
            }

            conexionDb.close();
            return peliculas;
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprimir la traza de la excepción
            System.out.println("Error al obtener películas: " + ex.getMessage()); // Imprimir el mensaje de la excepción
        } finally {
            // Cerrar recursos si es necesario
            try {
                if (conexionDb != null) {
                    conexionDb.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return null;
    }

    public void eliminarDatosPelicula(int idPelicula) {
    Connection conexionDb = ConexionDb.getConnection();

    // Ejecutar operaciones en la BD
    // Crear la sentencia SQL
    String sentenciaSql = "DELETE FROM pelicula WHERE id=?";
    try {
        // Configurar los parámetros de la sentencia SQL
        PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
        parametro.setInt(1, idPelicula); // Usar el parámetro idPelicula

        // Ejecutar la sentencia SQL
        parametro.execute();
        conexionDb.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

 }

