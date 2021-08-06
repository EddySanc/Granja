/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDAves;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Eddy
 */
public class Pollo {
    private String folio;
    private int cantidad;
    private String fecha;
    private int proveedor;
    private double kilos;
    private int tipo;
    private String observacion;

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public double getKilos() {
        return kilos;
    }

    public void setKilos(double kilos) {
        this.kilos = kilos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Pollo(String folio, int cantidad, String fecha, int proveedor, double kilos, int tipo, String observacion) {
        this.folio = folio;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.kilos = kilos;
        this.tipo = tipo;
        this.observacion = observacion;
    }

    public Pollo() {
    }
    public Pollo(String folio,int cantidad)
    {
        this.folio=folio;
        this.cantidad=cantidad;
        
    }
    
    /*02.04.19 obtener cantidad pollos*/
    public ArrayList<Pollo> getTotalPollos()
    {
        ArrayList<Pollo> listaPollos=new ArrayList<>();
        
        try
        {
            
             String query="SELECT folio,cantidad from pollos where cantidad>0";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
               listaPollos.add(new Pollo(
                       res.getString("folio"),
                       res.getInt("cantidad")
               ));
                //lista.add(res.getString("nombre"));
            }
            
            res.close();
            return listaPollos;
            
        }catch(Exception e)
        {
            System.err.println("Error : "+e.getLocalizedMessage());
            return null;
        }
        
        
    }
    
    
    
    
    public DefaultTableModel obtenerPollos(String dato)
    {
        //String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo=new DefaultTableModel();
        int registros=0;
        String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","OBSERVACIÒN"};
        
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM historico where tipo = -1 and pedidos_id =-1";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Pollos");
        }
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][6];
        try
        {
            String query="SELECT folio,pollos.cantidad,pollos.fecha,nombre,kilos,tipo,historico.observacion FROM pollos INNER JOIN historico ON pollos.folio = historico.pollos_folio INNER JOIN proveedor ON proveedor.id = pollos.proveedor_id where tipo=-1 and historico.pedidos_id =-1 order by pollos.fecha DESC";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            int i=0;
            while(res.next())
            {
                
               //String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","TIPO","OBSERVACIÒN"};
                datos[i][0]=res.getString("folio");
                datos[i][1]=res.getString("cantidad");
                datos[i][2]=res.getString("fecha");
                datos[i][3]=res.getString("nombre");
                datos[i][4]=res.getString("kilos");
                //datos[i][5]=res.getString("tipo");
                datos[i][5]=res.getString("observacion");
                
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);
            
            
            
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Pollos:"+e.toString());
        }
        return modelo;
        
    }
    
    /*Registrar Proveedor*/
    public boolean registrarPollos(Pollo pollo)
    {
        
        boolean result = false;
        //Inserccion en la tabla pollos
        String consulta="INSERT INTO pollos values (?,?,now(),?,?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setString(1, pollo.getFolio());
            preparedStatement.setInt(2, pollo.getCantidad());
            preparedStatement.setInt(3, pollo.getProveedor());
            preparedStatement.setInt(4, Usuario.idUsuario);
            preparedStatement.executeUpdate();
            
            result = true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar los Pollos: "+e.toString());
            result = false;
        }
        
        //Inserccion en la tabla historico
        consulta="INSERT INTO historico values (0,?,?,?,now(),?,?,?)";
        try
        {
          
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setInt(1, pollo.getCantidad());
            preparedStatement.setDouble(2, pollo.getKilos());
            preparedStatement.setInt(3, -1);
            preparedStatement.setString(4, pollo.getObservacion());
            //Pendiente de verificar
            preparedStatement.setString(5, pollo.getFolio());
            preparedStatement.setInt(6, -1);
            preparedStatement.executeUpdate();
            
            result = true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar en la tabla pollos"+e.toString());
            result = false;
        }

        result = actualizarCantidadPollos(pollo.getCantidad(),0,1);
    
        return result;
    }
    
    /*Eliminar Proveedor*/
    public boolean eliminarPollo(String folio,int cantidad)
    {
          boolean res=false;
        //se arma la consulta
        
        //se ejecuta la consulta
         try {
            String query = " DELETE FROM historico WHERE  pollos_folio= ?";
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(query);
            pstm.setString(1, folio);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
            res = false;
        }
         
        try {
            String q = " DELETE FROM pollos WHERE  folio= ? ";
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(q);
            pstm.setString(1, folio);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
            res = false;
        }
        
        res = actualizarCantidadPollos(cantidad,0,2);
        
        return res;
    }
    
    /*Actualizar informacion*/
    
    public boolean actualizarPollo(Pollo pollo,int cantidad_anterior)
    {
        boolean result = false;
        String consulta="UPDATE pollos set cantidad=?, proveedor_id = ? where folio=?";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);

            preparedStatement.setInt(1, pollo.getCantidad());
            preparedStatement.setInt(2, pollo.getProveedor());
            preparedStatement.setString(3, pollo.getFolio());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            result = true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar la tabla pollos"+d.getLocalizedMessage().toString());
            result = false;
        }
    
        try
        {
           
            consulta="UPDATE historico set cantidad=?, kilos = ?, tipo = ?, observacion = ? where pollos_folio=?";
            PreparedStatement st = Conexion.obtenerConexion().prepareStatement(consulta);

            st.setInt(1, pollo.getCantidad());
            st.setDouble(2, pollo.getKilos());
            st.setInt(3, pollo.getTipo());
            st.setString(4, pollo.getObservacion());
            st.setString(5, pollo.getFolio());
            
            st.executeUpdate();
            st.close();

            result = true;
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar"+d.getLocalizedMessage().toString());
            result = false;
        }
        result = actualizarCantidadPollos(pollo.getCantidad(),cantidad_anterior, 1);
        
        return result;
    }
    
    
    public void LlenarComboProveedor(CRUDAves frame){
        
        try{
            frame.cmb_pollos.removeAllItems();
            frame.cmb_pollos.addItem("-----Selecciona una Opción----");
            String query="SELECT * FROM proveedor";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            while(res.next()){
                frame.cmb_pollos.addItem(res.getString("nombre"));
            }
        }
        
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al obtener los proveedores"+d.getLocalizedMessage().toString());
        }
        
    }
    
    public int GetIdProveedor(String proveedor){
        
        try{
            
            String query="SELECT id FROM proveedor where nombre= ? ";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setString(1, proveedor);
            ResultSet res=st.executeQuery();
            
            while(res.next()){
                return res.getInt("id");
            }
            st.close();
        }
        
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al obtener los proveedores"+d.getLocalizedMessage().toString());
            return 0;
        }
        return 0;
    }
    
    public int CantidadPollos(){
        
        try{
            
            String query="SELECT total FROM pollos_total";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            while(res.next()){
                
                return res.getInt("total");
            }
            st.close();
        }
        
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al obtener los proveedores"+d.getLocalizedMessage().toString());
            return 0;
        }
        return 0;
    }
    
    
    public boolean actualizarCantidadPollos(int cantidad,int cantidad_anterior,int tipo){
        
        boolean result = false;
        
        try
        {
            String consulta="SELECT count(*) as total FROM pollos_total"; 
            
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            
            if(rs.getInt("total")>0){
                
                if(tipo ==1){
                    consulta = "UPDATE pollos_total set total=total-?,fecha = now()";
                    preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
                    preparedStatement.setInt(1, cantidad_anterior);
                    preparedStatement.executeUpdate();
                   
                    
                    consulta = "UPDATE pollos_total set total=total+?,fecha = now()";
                    preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
                    preparedStatement.setInt(1, cantidad);
                    preparedStatement.executeUpdate();
                    
                }
                else if(tipo ==2){
                    consulta = "UPDATE pollos_total set total=total-?,fecha = now()";
                    preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
                    preparedStatement.setInt(1, cantidad);
                    preparedStatement.executeUpdate();
                }

            }
            
            else{
                
                consulta = "INSERT pollos_total VALUES(0,?,now())";
                preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
                preparedStatement.setInt(1, cantidad);
                preparedStatement.executeUpdate();

            }

            result = true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar los Pollos totales"+e.getMessage());
            result = false;
        }
        
        return result;
    }
    
    public boolean Transferir(String folioDescontar,String folioAgregar,int cantidad,double kilos)
    {
        boolean result = false;
        
        try
        {
            String consulta="UPDATE pollos set cantidad=cantidad-? where folio=?";
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setString(2, folioDescontar);
            preparedStatement.executeUpdate();
            
            consulta = "UPDATE pollos set cantidad=cantidad+? where folio=?";
            preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setString(2, folioAgregar);
            preparedStatement.executeUpdate();
            
            consulta = "INSERT INTO historico values(0,?,?,6,now(),?,?,6)";
            preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setDouble(2, kilos);
            preparedStatement.setString(3,"Descuento por tranferencia");
            preparedStatement.setString(4, folioDescontar);
            preparedStatement.executeUpdate();
            
            consulta = "INSERT INTO historico values(0,?,?,7,now(),?,?,7)";
            preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setDouble(2, kilos);
            preparedStatement.setString(3,"Agregacion por tranferencia");
            preparedStatement.setString(4, folioAgregar);
            preparedStatement.executeUpdate();

            result = true;
            
        }
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al realizar la tranferencia"+d.getLocalizedMessage().toString());
            result = false;
        }
    
        
        
        return result;
    }
    
    public DefaultTableModel obtenerPollosTransferencia()
    {
        //String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo=new DefaultTableModel();
        int registros=0;
        String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR"};
        
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="select count(*) as total from pollos inner join proveedor on pollos.proveedor_id=proveedor.id where cantidad>1";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Pollos");
        }
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][4];
        try
        {
            String query="select*from pollos inner join proveedor on pollos.proveedor_id=proveedor.id where cantidad>1";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            int i=0;
            while(res.next())
            {
                
               //String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","TIPO","OBSERVACIÒN"};
                datos[i][0]=res.getString("folio");
                datos[i][1]=res.getString("cantidad");
                datos[i][2]=res.getString("fecha");
                datos[i][3]=res.getString("nombre");
                
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);
            
            
            
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Pollos Tranferenacia:"+e.toString());
        }
        return modelo;
        
    }
    
    public void GenerarReporte(String folio){
    
        
        try {
            URL path=this.getClass().getResource("/Reportes/reportMovimientosFolio.jasper");
            System.out.println(path);
            JasperReport reporte=(JasperReport)JRLoader.loadObject(path);
              System.out.println("reporte"+reporte);
            HashMap parametros = new HashMap<>();
            parametros.put("folio", folio);
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, Conexion.obtenerConexion());
            JasperViewer viewer = new JasperViewer(jasperPrint,false);
            viewer.setTitle("Movimientos");
            viewer.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(Credito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
    
