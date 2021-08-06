/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class Factura 
{
    
        DefaultTableModel modelo;
        private int idpedido;
        private int cantidadDevolucion;
        private int kilosDevolucion;
        private String folioFactura;

    
    /*03.04.19*/
    public Factura(int idpedido,String folioFactura)
    {
        this.idpedido=idpedido;
        this.folioFactura=folioFactura;
    }
    /*03.04.19*/
        
    public Factura() {
    }

    
    
    
    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getCantidadDevolucion() {
        return cantidadDevolucion;
    }

    public void setCantidadDevolucion(int cantidadDevolucion) {
        this.cantidadDevolucion = cantidadDevolucion;
    }

    public int getKilosDevolucion() {
        return kilosDevolucion;
    }

    public void setKilosDevolucion(int kilosDevolucion) {
        this.kilosDevolucion = kilosDevolucion;
    }

    public String getFolioFactura() {
        return folioFactura;
    }

    public void setFolioFactura(String folioFactura) {
        this.folioFactura = folioFactura;
    }
        
/*-----------------------------------*/
  /*registro de pedidos*/
    public boolean registrarDevolucion(int idPedido,double kilos,int cantidad,String comentarios,String folio)
   {
      
       try
       { 
           ResultSet rs;
           CallableStatement cs = Conexion.obtenerConexion().prepareCall("{call registroCancelacion (?,?,?,?,?)}");
           cs.setInt(1,idPedido);
           cs.setDouble(2, kilos);
           cs.setInt(3, cantidad);
           cs.setString(4, comentarios);
           cs.setString(5, folio);           


           rs=cs.executeQuery();
           rs.first();
           int resultado=rs.getInt("estado");    
           
           rs.close();
          
           if(resultado==1)
           {
               return true;
           }
           else
           {
               return false;
           }
           
       
       }catch(Exception e)
       {
          // JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar el pedido "+e.getLocalizedMessage().toString());
           System.out.println("Error al registrar el SP : "+e.getMessage());
           return false;
       }
   }

/*-----------START------------------Registrar devolucion sin reintegro al inventario--------------------*/    
    public boolean registrarDevolucionSin(int idPedido,double kilos,int cantidad,String comentarios,String folio)
   {
      
       try
       { 
           ResultSet rs;
           CallableStatement cs = Conexion.obtenerConexion().prepareCall("{call registroCancelacionNo(?,?,?,?,?)}");
           cs.setInt(1,idPedido);
           cs.setDouble(2, kilos);
           cs.setInt(3, cantidad);
           cs.setString(4, comentarios);
           cs.setString(5, folio);           


           rs=cs.executeQuery();
           rs.first();
           int resultado=rs.getInt("estado");    
           
           rs.close();
          
           if(resultado==1)
           {
               return true;
           }
           else
           {
               return false;
           }
           
       
       }catch(Exception e)
       {
          // JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar el pedido "+e.getLocalizedMessage().toString());
           System.out.println("Error al registrar el SP : "+e.getMessage());
           return false;
       }
   }    
/*-----------EDN------------------Registrar devolucion sin reintegro al inventario--------------------*/


    
/*-----------------------------------*/    
    
      public DefaultTableModel obtenerFacturas(String dato,int op) //op =1 condicion destino
    {
          String condicion="";
        if(op==1)
        {
        condicion= " AND destino.nombre='"+dato+"'";    
        }else
        {
        condicion="AND pedidos.configuracion_id like '%"+dato+"%'";    
        }
        
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"ID","FACTURA","DESTINO","$ x KILO","REJA","KILOS","# AVES","IMPORTE","CONDUCTOR","PLACAS","FECHA"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="select count(*) as total," +
"    pedidos.id as id," +
"    pedidos.cantidad as cantidad," +
"    destino.preciokilo as precio," +
"    (destino.preciokilo*pedidos.kilos) as importe," +
"    pedidos.kilos as kilos," +
"    pedidos.fecha as fecha," +
"    pedidos.configuracion_id as factura," +
"    chofer.nombre as chofer," +
"    destino.nombre as destino," +
"    carro.placas as placas," +
"    reja.numero as reja" +
"                from pedidos,reja,ruta,destino,chofer,carro" +
"    where" +
"        pedidos.ruta_id=ruta.id and ruta.chofer_id=chofer.id " +
"        and ruta.destino_id=destino.id and destino.carro_id=carro.id" +
"        and pedidos.reja_id=reja.id " +
"        and pedidos.estado=1 "+condicion;
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla facturas"+e.getLocalizedMessage());
        }
        
        
  
   
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][11];
        
        try
        {
            String query="select " +
"    pedidos.id as id," +
"    pedidos.cantidad as cantidad," +
"    destino.preciokilo as precio," +
"    TRUNCATE((destino.preciokilo*pedidos.kilos),1) as importe," +
"    pedidos.kilos as kilos," +
"    pedidos.fecha as fecha," +
"    pedidos.configuracion_id as factura," +
"    chofer.nombre as chofer," +
"    destino.nombre as destino," +
"    carro.placas as placas," +
"    reja.numero as reja" +
"                from pedidos,reja,ruta,destino,chofer,carro" +
"    where" +
"        pedidos.ruta_id=ruta.id and ruta.chofer_id=chofer.id " +
"        and ruta.destino_id=destino.id and destino.carro_id=carro.id" +
"        and pedidos.reja_id=reja.id" +
"        and pedidos.estado=1 "+condicion;
            
           // System.out.println("Query: "+query);
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("factura");
                datos[i][2]=res.getString("destino");
                datos[i][3]="$ "+res.getString("precio");
                datos[i][4]=res.getString("reja"); 
                datos[i][5]=res.getString("kilos");
                datos[i][6]=res.getString("cantidad");
                datos[i][7]="$ "+res.getString("importe");
                datos[i][8]=res.getString("chofer");
                datos[i][9]=res.getString("placas");
                datos[i][10]=res.getString("fecha");
                

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla facturas "+e.getMessage());
        }
        return modelo;
        
    }
     
    /*-----actualizar pedido como venta*/
          public boolean facturarPedido(int i)
    {
        String consulta="UPDATE pedidos set estado=2 where configuracion_id=?";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
    
        ps.setInt(1,i);
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al generar la factura "+d.getLocalizedMessage().toString());
            return false;
        }
    }
/*-------------------------actualizar historico--------------update historico set tipo=2 where historico.pedidos_id in (select id from pedidos where configuracion_id=243);*/
          public boolean actualizarHistorico(int i)
    {
        String consulta="update historico set tipo=2 where historico.pedidos_id in (select id from pedidos where configuracion_id=?)";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
    
        ps.setInt(1,i);
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar la factura como pagada "+d.getLocalizedMessage().toString());
            return false;
        }
    }     
          
    /*----------------------------------------historico cancelación-------------------*/
          
          
          
    /*Rutas disponibles*/
          public ArrayList<Ruta> obtenerRutas()
          {
              ArrayList<Ruta> lista=new ArrayList<>();
               try
        {
            String query="SELECT * FROM rutasDisponibles";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
               
                lista.add(new Ruta(
                res.getInt("id"),
                res.getString("destino")
                ));
                //lista.add(res.getString("nombre"));
            }
            
            res.close();
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al cargar las rutas");
        }
        
        return lista;
        
              
          }
          
          
                public ArrayList<Ruta> obtenerRutas2()
          {
              ArrayList<Ruta> lista=new ArrayList<>();
               try
        {
            String query="SELECT * FROM rutasDisponibles";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
               
                lista.add(new Ruta(
                res.getInt("iddestino"),
                res.getString("destino")
                ));
                //lista.add(res.getString("nombre"));
            }
            
            res.close();
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al cargar las rutas");
        }
        
        return lista;
        
              
          }
          
}
