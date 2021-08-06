/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class ModeloInsumos 
{
    private int id;
    private String descripcion;
    private double precio;
    private String fecha;
    private int destino_id;
    private int estado;
    private int usuario_id;
    DefaultTableModel modelo;


    public ModeloInsumos(int id, String descripcion, double precio, String fecha, int destino_id,int estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.destino_id = destino_id;
        this.estado=estado;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    

    public ModeloInsumos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(int destino_id) {
        this.destino_id = destino_id;
    }
    
     /*Obtener las rejas*/
     public DefaultTableModel obtenerInsumo(String dato)
    {
 
        String condicion="and  destino.nombre LIKE '%"+dato+"%'";
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"ID","Ruta","Insumo","Cantidad Ingresada","Registrado por:","Fecha"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="select count(*) as total,destino.nombre as nombre,insumos.descripcion as descripcion,"
                    + " insumos.precio as precio,usuario.nombre as nombre,insumos.fecha as fecha "
                    + " from destino,insumos,usuario where destino.id=insumos.destino_id and insumos.usuario_id=usuario.id and insumos.estado=1 "+condicion;
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
        Object[][] datos=new String[registros][6];
        
        try
        {
            String query="select insumos.id as id,destino.nombre as nombre,insumos.descripcion as descripcion,insumos.precio as precio,usuario.nombre as nombreU,"
                    + " insumos.fecha as fecha from destino,insumos,usuario "
                    + " where destino.id=insumos.destino_id and insumos.usuario_id=usuario.id and insumos.estado=1 "+condicion+" order by insumos.id DESC";
            System.out.println("Query_ "+ query);
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("nombre");
                datos[i][2]=res.getString("descripcion");
                datos[i][3]="$"+res.getString("precio");
                datos[i][4]=res.getString("nombreU");
                datos[i][5]=res.getString("fecha");

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla insumos "+e.getMessage());
        }
        return modelo;
        
    }
     /*Regitrar insumo*/
        public boolean registrarInsumo(ModeloInsumos modeloInsumo)
    {
        String consulta="INSERT INTO insumos values (0,?,?,now(),1,?,?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, modeloInsumo.getDescripcion());
            preparedStatement.setDouble(2, modeloInsumo.getPrecio());
            preparedStatement.setInt(3, modeloInsumo.getDestino_id());
            preparedStatement.setInt(4, Usuario.idUsuario);
            

            
               preparedStatement.executeUpdate();
            
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar carro");
            return false;
        }
    
    }
        
        /*-------Eliminar insumos*/
            public boolean eliminarInsumo(int idUsuario,int idInsumo)
    {
        String consulta="UPDATE insumos set estado=0,usuario_id=? where id=?";
        try
        {
        PreparedStatement ps=Conexion.obtenerConexion().prepareStatement(consulta);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idInsumo);
        ps.executeUpdate();
        ps.close();
        return true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al eliminar insumo "+d.getLocalizedMessage().toString());
            return false;
        }
    }
    
}
