/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDAves;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eddy
 */
public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente(int id, String nombre, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente() {
    }
    
    public DefaultTableModel obtenerClientes(String dato)
    {
        //String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo=new DefaultTableModel();
        int registros=0;
        String [] columnas={"ID","NOMBRE","TELEFONO","DIRECCION"};
        
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM cliente where nombre like '%"+dato+"%'";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Cliente");
        }
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][4];
        try
        {
            String query="SELECT * FROM cliente where nombre like '%"+dato+"%'";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            int i=0;
            while(res.next())
            {
                
               //String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","TIPO","OBSERVACIÒN"};
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("nombre");
                datos[i][2]=res.getString("telefono");
                datos[i][3]=res.getString("direccion");
              
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);
            
            
            
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Clientes:"+e.toString());
        }
        return modelo;
        
    }
    
    /*Registrar Proveedor*/
    public boolean registrarCliente(Cliente cliente)
    {
        
        boolean result = false;
        //Inseccion en la tabla pollos
        String consulta="INSERT INTO cliente values (0,?,?,?,?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getTelefono());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setInt(4, Usuario.idUsuario);
            preparedStatement.executeUpdate();
            
            result = true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar el Cliente");
            result = false;
        }
        
        return result;
    }
    
    /*Eliminar Proveedor*/
    public boolean eliminarCliente(int id)
    {
          boolean res=false;
        //se arma la consulta
        
        //se ejecuta la consulta
         try {
            String q = " DELETE FROM cliente WHERE  id = ?";
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(q);
            pstm.setInt(1, id);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
            res = false;
        }
        
        return res;
    }
    
    /*Actualizar informacion*/
    
    public boolean actualizarCliente(Cliente cliente)
    {
        boolean result = false;
        String consulta="UPDATE cliente set nombre=?, telefono = ? , direccion = ? where id=?";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getTelefono());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setInt(4, cliente.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            result = true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar el Cliente"+d.getLocalizedMessage().toString());
            result = false;
        }

        return result;
    }
    
}
