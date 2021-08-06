/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDReja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class Reja 
{
    private int id;
    private int numero;
    private String descripcion;
    DefaultTableModel modelo;

    public Reja(int id, int numero, String descripcion) {
        this.id = id;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Reja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    /*Obtener las rejas*/
     public DefaultTableModel obtenerCarros(String dato)
    {
 
        String condicion="where numero LIKE '%"+dato+"%' OR descripcion LIKE '%"+dato+"%'";
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"ID","No. REJA","DESCRIPCIÓN"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM reja "+condicion;
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla rejas");
        }
        
        
  
   
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][5];
        
        try
        {
            String query="SELECT * FROM reja "+condicion+" order by id DESC";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("numero");
                datos[i][2]=res.getString("descripcion");

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla rejas "+e.getMessage());
        }
        return modelo;
        
    }
     
     
     /*obtener rejas disponibles*/
     public ArrayList<Reja> obtenerRejasLibres()
     {
         ArrayList<Reja> lista=new ArrayList<>();
         try
         {
             String query="select * from reja where id not in(select reja_id from pedidos where DATE_FORMAT(fecha,'%Y-%m-%d')=DATE(now()) and estado = 1)";
              PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
          
                lista.add(new Reja(
                       res.getInt("id"),
                        res.getInt("numero"),
                res.getString("descripcion")
                
                ));
                
            }
            
         
         }
         
         catch(Exception er)
         {
             JOptionPane.showMessageDialog(null, "Error al cargar las rejas"+er.getLocalizedMessage());
         }
         return lista;
     }
     
     
      /*Registrar Proveedor*/
    public boolean registrarReja(Reja reja)
    {
        String consulta="INSERT INTO reja values (0,?,?,?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setInt(1, reja.getNumero());
            preparedStatement.setString(2, reja.getDescripcion());
            preparedStatement.setInt(3, Usuario.idUsuario);

            
             preparedStatement.executeUpdate();
             
             preparedStatement.close();
            
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar carro");
            return false;
        }
    
    }
    
    
    /*Actualizar reja*/
        
    public boolean actualizarReja(Reja reja)
    {
        String consulta="UPDATE reja set numero=?,descripcion=?  where id=?";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
    
        ps.setInt(1,reja.getNumero());
        ps.setString(2, reja.getDescripcion());
        ps.setInt(3, reja.getId());
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar Reja "+d.getLocalizedMessage().toString());
            return false;
        }
    }
    /*Eliminar reja*/
        /*Eliminar Proveedor*/
    public boolean eliminarReja(int id)
    {
          boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM reja WHERE  id="+id;
        //se ejecuta la consulta
         try {
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
     
     /*Limpiar campos*/
     public void limpiarCampos(CRUDReja vistaReja,Boolean ...x)
     {
        vistaReja.txt_buscar.setText("");
        vistaReja.txt_descripcion.setText("");
        vistaReja.txt_numero.setText("0");
        
        vistaReja.btn_actualizar.setEnabled(x[0]);
        vistaReja.btn_agrear.setEnabled(x[1]);
        vistaReja.btn_eliminar.setEnabled(x[2]);
        vistaReja.btn_limpiar.setEnabled(x[3]);
     }
}
