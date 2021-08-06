/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDAves;
import Vista.CRUDProveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class Proveedor {
    /*Declaracion de variables*/
    private int id;
    private String nombre;
    private String direccion;
    private double preciokilo;
    private String observacion;
    private String fecha;
    
    public Proveedor()
    {}

    public Proveedor(int id, String nombre, String direccion, double preciokilo, String observacion, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.preciokilo = preciokilo;
        this.observacion = observacion;
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

    public double getPreciokilo() {
        return preciokilo;
    }

    public void setPreciokilo(double preciokilo) {
        this.preciokilo = preciokilo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    /*Limpiar Cajas de Texto*/
    public void limpiarCajas(CRUDProveedor proveedor)
    {
        proveedor.txt_busquedaProveedor.setText("");
        proveedor.txt_nombreP.setText("");
        proveedor.txt_direccionP.setText("");
        proveedor.txt_obsP.setText("");
        proveedor.txt_kiloP.setText("0.0");
        
    }
    
    /*Habilitar botones dependiendo el estado*/
    public void habilitarBotones(CRUDProveedor proveedor,Boolean ...estado)
    {
        /*Cinco Botones*/
        proveedor.btn_guardarP.setEnabled(estado[0]);
        proveedor.btn_eliminarP.setEnabled(estado[1]);
        proveedor.btn_actualizarP.setEnabled(estado[2]);
    }
    
    
    
    /*Modelo de la Tabla*/
    public DefaultTableModel obtenerProveedores(String dato)
    {
        String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo=new DefaultTableModel();
        int registros=0;
        String [] columnas={"ID","NOMBRE","DIRECCIÓN","PRECIO","OBSERVACIÓN","FECHA"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM proveedor";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Proveedor");
        }
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][6];
        try
        {
            String query="SELECT * FROM proveedor "+condicion+"order by fecha DESC";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("nombre");
                datos[i][2]=res.getString("direccion");
                datos[i][3]=res.getString("preciokilo");
                datos[i][4]=res.getString("observacion");
                datos[i][5]=res.getString("fecha");
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Proveedores");
        }
        return modelo;
        
    }
    
    
    /*Registrar Proveedor*/
    public boolean registrarProveedor(Proveedor proveedor)
    {
        String consulta="INSERT INTO proveedor values (0,?,?,?,?,now(),?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, proveedor.getNombre());
            preparedStatement.setString(2, proveedor.getDireccion());
            preparedStatement.setDouble(3, proveedor.getPreciokilo());
            preparedStatement.setString(4, proveedor.getObservacion());
            preparedStatement.setInt(5, Usuario.idUsuario);
            
            
             preparedStatement.executeUpdate();
            
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar Proveedor");
            return false;
        }
    
    }
    
    /*Eliminar Proveedor*/
    public boolean eliminarProveedor(int id)
    {
          boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM proveedor WHERE  id="+id;
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
    
    public boolean actualizarProveedor(Proveedor proveedor)
    {
        String consulta="UPDATE proveedor set nombre=?,direccion=?,preciokilo=?,observacion=? where id=?";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
    
        ps.setString(1,proveedor.getNombre());
        ps.setString(2, proveedor.getDireccion());
        ps.setDouble(3, proveedor.getPreciokilo());
        ps.setString(4, proveedor.getObservacion());
            ps.setInt(5, proveedor.getId());
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar Proveedor"+d.getLocalizedMessage().toString());
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
