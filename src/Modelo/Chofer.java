/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDChofer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class Chofer 
{
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String fecha;
    DefaultTableModel modelo;
    public Chofer() {
    }

    public Chofer(int id, String nombre, String direccion, String telefono,String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha=fecha;
    }
    
    public Chofer(int id,String nombre)
    {
        this.id=id;
        this.nombre=nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    /*Limpiar cajas*/
    public void limpiarCajas(CRUDChofer chofer)
    {
        chofer.txt_busquedaChofer.setText("");
        chofer.txt_nombreChofer.setText("");
        chofer.txt_direccionChofer.setText("");
        chofer.txt_telefonoChofer.setText("");

    }
    
    /*Inhabilitar/habilitar*/
    public void habilitarBotones(CRUDChofer chofer,Boolean ...estado)
    {
        chofer.btn_registroChofer.setEnabled(estado[0]);
        chofer.btn_eliminarChofer.setEnabled(estado[1]);
        chofer.btn_actualizarChofer.setEnabled(estado[2]);
        
    }
    
    
    /*Construir tabla Chofer*/
    /*Modelo de la Tabla*/
    public DefaultTableModel obtenerConductores(String dato)
    {
 
        String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"ID","NOMBRE","DIRECCIÓN","TELÉFONO","FECHA"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM chofer "+condicion;
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Chofer");
        }
        
        
  
   
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][5];
        System.out.println("Total de Registros: "+registros);
        try
        {
            String query="SELECT * FROM chofer "+condicion+" order by fecha DESC";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("nombre");
                datos[i][2]=res.getString("direccion");
                datos[i][3]=res.getString("telefono");
                datos[i][4]=res.getString("fecha");

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Chofer"+e.getMessage());
        }
        return modelo;
        
    }
    

    
    /*Registrar Proveedor*/
    public boolean registrarChofer(Chofer chofer)
    {
        String consulta="INSERT INTO chofer values (0,?,?,?,now(),?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, chofer.getNombre());
            preparedStatement.setString(2, chofer.getDireccion());
            preparedStatement.setString(3, chofer.getTelefono());
            preparedStatement.setInt(4, Usuario.idUsuario);

            
             preparedStatement.executeUpdate();
            
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar chofer");
            return false;
        }
    
    }
    
    /*Eliminar Proveedor*/
    public boolean eliminarChofer(int id)
    {
          boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM chofer WHERE  id="+id;
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
    
    public boolean actualizarChofer(Chofer chofer)
    {
        String consulta="UPDATE chofer set nombre=?,direccion=?,telefono=? where id=?";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
    
        ps.setString(1,chofer.getNombre());
        ps.setString(2, chofer.getDireccion());
        ps.setString(3, chofer.getTelefono());
        ps.setInt(4, chofer.getId());
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar Chofer"+d.getLocalizedMessage().toString());
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
