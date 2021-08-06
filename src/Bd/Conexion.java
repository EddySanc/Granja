/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String db="granja";
    private static String usuario="root";
    private static String pwd="mjE6Xcf3pD";
    //private static String pwd="Eddy21gamaliel";
    private static String host="jdbc:mysql://localhost/"+db;
    private static Connection con=null;
    
   public static Connection obtenerConexion() 
    {
        if(con!=null)//verifica si con tiene la conexion
        {
            return con;
        }
        else
        {
            try
            {
               Class.forName("com.mysql.jdbc.Driver");
               con=DriverManager.getConnection(host, usuario, pwd); 
               if(con!=null)
               {
                   return con;
               }
               else
               {
                   return null;
               }
            }
            catch(Exception e)
            {
                e.getCause();
                return null;
            }
        }
    }
    
    
}
