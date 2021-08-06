/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDCarros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class Carro 
{
    private int id;
    private String placas;
    private String descripcion;
    DefaultTableModel modelo;

    public Carro() {
    }

    public Carro(int id, String placas, String descripcion) {
        this.id = id;
        this.placas = placas;
        this.descripcion = descripcion;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /*Limpiar Cajas*/
    public void limpiarCajas(CRUDCarros carros)
    {
        carros.txt_buscar.setText("");
        carros.txt_descripcion.setText("");
        carros.txt_placas.setText("");
    }
    
    /*Habilitar/deshabilitar*/
    public void habilitar(CRUDCarros carros,Boolean ...dato)
    {
        carros.btn_agregar.setEnabled(dato[0]);
        carros.btn_eliminar.setEnabled(dato[1]);
        carros.btn_actualizar.setEnabled(dato[2]);
    }
    
    /*-------------------------------------------MODELOS Y CRUD TABLA CHOFER-------------------------------------------------------------*/
    public DefaultTableModel obtenerCarros(String dato)
    {
 
        String condicion="where placas LIKE '%"+dato+"%' OR descripcion LIKE '%"+dato+"%'";
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"ID","PLACAS","DESCRIPCIÓN"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM carro "+condicion;
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla carro");
        }
        
        
  
   
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][5];
        
        try
        {
            String query="SELECT * FROM carro "+condicion+" order by id DESC";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("placas");
                datos[i][2]=res.getString("descripcion");

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Carros "+e.getMessage());
        }
        return modelo;
        
    }
    

    
    /*Registrar Proveedor*/
    public boolean registrarCarro(Carro carro)
    {
        String consulta="INSERT INTO carro values (0,?,?,?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, carro.getPlacas());
            preparedStatement.setString(2, carro.getDescripcion());
            preparedStatement.setInt(3, Usuario.idUsuario);

            
             preparedStatement.executeUpdate();
            
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar carro");
            return false;
        }
    
    }
    
    /*Eliminar Proveedor*/
    public boolean eliminarCarro(int id)
    {
          boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM carro WHERE  id="+id;
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
    
    /*Actualizar informacion*/
    
    public boolean actualizarCarro(Carro carro)
    {
        String consulta="UPDATE carro set placas=?,descripcion=?  where id=?";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
    
        ps.setString(1,carro.getPlacas());
        ps.setString(2, carro.getDescripcion());
        ps.setInt(3, carro.getId());
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar Carro "+d.getLocalizedMessage().toString());
            return false;
        }
    }
    
    public boolean validarEspacios(String ...x)
    {
        boolean estado=false;
        for(int i=0; i<x.length; i++)
        {
            if(x[i].equalsIgnoreCase(""))
            {
                i=x.length+2;
                estado=false;
            }
            else
            {
                estado=true;
            }
        }
        return estado;
    }
    
    
}
