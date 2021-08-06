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
public class ModeloConfiguracion
{
 private String nombre;
 private String direccion;
 private String telefono;
 private String codigo;

 public ModeloConfiguracion()
 {
     
 }
 
    public ModeloConfiguracion(String nombre, String direccion, String telefono, String codigo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigo = codigo;
    }

 
 
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
 
 /*----------------------------obtener parametros----------------*/
        public ArrayList<ModeloConfiguracion> obtenerConfiguracion()
     {
         ArrayList<ModeloConfiguracion> lista=new ArrayList<>();
         try
         {
             String query="select * from negocio";
              PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
                /*
                        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigo = codigo;
                */
                lista.add(new ModeloConfiguracion(
                        res.getString("nombre"),
                        res.getString("direccion"),
                        res.getString("telefono"),
                        res.getString("codigo")
                ));
                
                
                
            }
            
         
         }
         
         catch(Exception er)
         {
             JOptionPane.showMessageDialog(null, "Error al cargar los datos del negocio"+er.getLocalizedMessage());
         }
         return lista;
     }
 
        
        public String  getCode()
    {
        String folio="";
        try
        {
         String query = "SELECT codigo FROM negocio";
         Statement st = Conexion.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                folio=rs.getString("codigo");
            }
            return folio;
            
        }catch(Exception r)
        {
            r.getCause();
            return "-1";
        }
    }
    
        
        public boolean registrarNegocio(ModeloConfiguracion modeloConfiguracion)
    {
        String consulta="INSERT INTO negocio values (0,?,?,?,sha1(?))";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, modeloConfiguracion.getNombre());
            preparedStatement.setString(2, modeloConfiguracion.getDireccion());
            preparedStatement.setString(3, modeloConfiguracion.getTelefono());
            preparedStatement.setString(4, modeloConfiguracion.getCodigo());

            
             preparedStatement.executeUpdate();
             
             preparedStatement.close();
            
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar datos del negocio");
            return false;
        }
    
    }
        
}
