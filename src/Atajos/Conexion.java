package Atajos;

import java.sql.*;
/**
 *
 * @author Daniela
 */
public class Conexion {
    // creacion de metodo para conectar a la base de datos del refugio gatuno.
    public static Connection conectar(){
  
        try{
            
            String url= "jdbc:mysql://localhost/refugiogatos";
            String usuario = "root";
            String pass = "";
            
            Connection cn = DriverManager.getConnection(url, usuario, pass);
            return cn;
        }catch(SQLException e){
            System.out.println("Error al conectar con la base de datos " + e);
        }
        return (null);
    }
}
