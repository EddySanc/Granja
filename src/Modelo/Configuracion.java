/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class Configuracion 
{
    
    /*03.04.19*/
    public static String obtenerFoliodePedido(int foliofactura)
    {
        String folio="";
        try
        {
         String query = "select distinct  historico.pollos_folio as factura from historico,pedidos  where pedidos.id=historico.pedidos_id and pedidos.configuracion_id="+foliofactura+" limit 1";
         Statement st = Conexion.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                folio=rs.getString("factura");
            }
            return folio;
            
        }catch(Exception r)
        {
            r.getCause();
            return null;
        }
    }
    /*03.04.19*/
    
    public static ArrayList<Factura> listaPedidos(int foliofactura,int op)
    {
        ArrayList<Factura> lista=new ArrayList<>();
        String query="";
        if(op>0)
        {
        query="select pedidos.id as id, historico.pollos_folio as folio from pedidos,historico where pedidos.id=historico.pedidos_id and pedidos.id="+op;            
        }
        else
        {
        query="select pedidos.id as id, historico.pollos_folio as folio from pedidos,historico where pedidos.id=historico.pedidos_id and pedidos.configuracion_id="+foliofactura;
        }
        
        try
        {
             PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
                lista.add(new Factura(
                res.getInt("id"),
                        res.getString("folio")
                ));
                
            }
            
            res.close();
            return lista;
        }catch(Exception r)
        {
            System.out.println("Error: "+r.getLocalizedMessage());
            return null;
        }
        
    }
    
    
    public static int getFactura()
    {
        int folio=-1;
        try
        {
         String query = "SELECT max(factura) as factura FROM configuracion";
         Statement st = Conexion.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                folio=rs.getInt("factura");
            }
            return folio;
            
        }catch(Exception r)
        {
            r.getCause();
            return -1;
        }
    }
    
 
    
    
    public static int getCantidad(int id)
    {
        int folio=-1;
        try
        {
            String query="select count(*) as cantidad from pedidos where configuracion_id="+id;
            Statement st = Conexion.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                folio=rs.getInt("cantidad");
            }
            return folio;
        }
        catch(Exception d)
        {
         return -1;   
        }
    }
    
        
    public static int getIdFolioFactura()
    {
        int folio=-1;
        try
        {
         String query = "SELECT max(id) as id  FROM configuracion";
         Statement st = Conexion.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                folio=rs.getInt("id");
            }
            return folio;
            
        }catch(Exception r)
        {
            r.getCause();
            return -1;
        }
    }
    

        public static void registrarFactura(int i)
    {
        String consulta="INSERT INTO configuracion values (0,?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setInt(1, i);
             preparedStatement.executeUpdate();
            
            ///return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al generar folio de factura"+e.getLocalizedMessage());
            //return false;
        }
    
    }
    
    
    public static int getTotalPollos()
    {
        int total=0;
        try
        {
            String query="SELECT  * FROM pollos_total";
            Statement st=Conexion.obtenerConexion().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next())
            {
                total=rs.getInt("total");
            }
            return total;
            
        }catch(Exception e)
        {
            
            return -1;
        }
    }
    
        public static double getCantidadPollos(int factura)
    {
        double total=0.0;
        try
        {
            String query="select sum(kilos) as kilos from pedidos where configuracion_id="+factura;
            Statement st=Conexion.obtenerConexion().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next())
            {
                total=rs.getDouble("kilos");
            }
            return total;
            
        }catch(Exception e)
        {
            
            return -1;
        }
    }
        
           
        public static double getCantidadTotalAves(int factura)
    {
        double total=0.0;
        try
        {
            String query="select sum(cantidad) as aves from pedidos where configuracion_id="+factura;
            Statement st=Conexion.obtenerConexion().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next())
            {
                total=rs.getInt("aves");
            }
            return total;
            
        }catch(Exception e)
        {
            
            return -1;
        }
    }
    
}
