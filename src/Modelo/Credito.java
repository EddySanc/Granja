/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Credito {
    private int id;
    private double monto;
    private int tipo;
    private String fecha;
    private int idCliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Credito(int id, double monto, int tipo, String fecha, int idCliente) {
        this.id = id;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public Credito() {
        
    }
    
    
    public DefaultTableModel obtenerCreditos(int estado,String nombre)
    {
        //String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo=new DefaultTableModel();
        int registros=0;
        String [] columnas={"ID","IDC","FACTURA","CANTIDAD","KILOS","PRECIO*KILO","TOTAL","ABONADO","CLIENTE","FECHA"};
        
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total FROM vcredito where estado = ? and nombre like '%"+nombre+"%'";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setInt(1, estado);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Creditos");
        }
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][10];
        try
        {
            //String query="SELECT * FROM vcredito where estado = ? and nombre like '%"+nombre+"%' order by fecha desc";
              String query="SELECT id,idcliente,factura,cantidad,TRUNCATE(kilos,1) as kilos,TRUNCATE(preciokilo,1) as preciokilo,TRUNCATE(total,2) as total,factura,nombre,fecha FROM vcredito where estado = ? and nombre like '%"+nombre+"%' order by fecha desc";
            
              PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setInt(1, estado);
            ResultSet res=st.executeQuery();
            
            int i=0;
            while(res.next())
            {

               //String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","TIPO","OBSERVACIÒN"};
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("idcliente");
                datos[i][2]=res.getString("factura");
                datos[i][3]=res.getString("cantidad");
                datos[i][4]=res.getString("kilos");
                datos[i][5]="$"+res.getString("preciokilo");
                datos[i][6]="$"+res.getString("total");
                datos[i][7]="$"+getAbonado(res.getInt("factura"));
                datos[i][8]=res.getString("nombre");
                datos[i][9]=res.getString("fecha");
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);
            
            
            
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Creditos:"+e.toString());
        }
        return modelo;
        
    }
    
    public DefaultTableModel obtenerObonos(int id)
    {
        //String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo=new DefaultTableModel();
        int registros=0;
        String [] columnas={"ID","MONTO","FECHA"};
        
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT count(*) as total from credito as c left join venta_credito as vc on c.id = vc.credito_id where vc.configuracion_id = ?";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Abonos");
        }
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][3];
        try
        {
            String query="select vc.id,monto,fecha from credito as c left join venta_credito as vc on c.id = vc.credito_id where vc.configuracion_id = ? order by fecha desc";
           
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res=st.executeQuery();
            
            int i=0;
            while(res.next())
            {

               //String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","TIPO","OBSERVACIÒN"};
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("monto");
                datos[i][2]=res.getString("fecha");
                
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);
            
            
            
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Abonos:"+e.toString());
        }
        return modelo;
        
    }
    
    public boolean registrarAbono(double monto,int idCliente,int idFactura)
    {
        
        boolean result = false;
        //Inserccion en la tabla pollos
        String consulta="INSERT INTO credito values (0,?,1,now(),?)";
        try
        {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setDouble(1, monto);
            preparedStatement.setInt(2,idCliente);
            preparedStatement.executeUpdate();
            
            
            int lastCredito = getLastId();
            consulta = "INSERT INTO venta_credito values (0,?,?,?)";
            preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);  
            preparedStatement.setDouble(1, getLastId());
            preparedStatement.setInt(2,idFactura);
            preparedStatement.setInt(3,Usuario.idUsuario);
            preparedStatement.executeUpdate();
            result = true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al registrar los el abono a la cuenta: "+e.toString());
            result = false;
        }
        return result;
    }
    
    public boolean eliminarAbono(int id)
    {
          boolean res=false;
        //se arma la consulta
        
        //se ejecuta la consulta
         try {
            String q = "delete venta_credito,credito from venta_credito inner join credito on venta_credito.credito_id = credito.id where venta_credito.id=?";
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(q);
            pstm.setInt(1, id);
            pstm.execute();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
            res = false;
        }
        
        return res;
    }
    
    public int getLastId(){
        
        try{
            
            String query="SELECT max(id) as id FROM credito";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            while(res.next()){
                
                return res.getInt("id");
            }
            st.close();
        }
        
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al obtener el identificador del Credito"+d.getLocalizedMessage().toString());
            return 0;
        }
        return 0;
    }
    
    public double getAbonado(int factura){
        
        try{
            
            String query="select sum(monto) as abonado from venta_credito as vc "
                    + "inner join credito as c on vc.credito_id = c.id where vc.configuracion_id = ?;";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setInt(1, factura);
            ResultSet res=st.executeQuery();
            
            while(res.next()){
                
                return res.getDouble("abonado");
            }
            st.close();
        }
        
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al obtener el identificador del Credito"+d.getLocalizedMessage().toString());
            return 0;
        }
        return 0;
    }
    
    public Double GetTotalCredito(int factura){
        double total=0;
        try {
            String query = "select sum(kilos)*preciokilo as total from pedidos as p inner join ruta as r on p.ruta_id = r.id inner join destino as d on r.destino_id = d.id where configuracion_id = ?;";
            PreparedStatement pst = Conexion.obtenerConexion().prepareStatement(query);
            pst.setInt(1, factura);
            ResultSet rs  = pst.executeQuery();
            rs.next();
            
            total = rs.getDouble("total");
            
            
            
        } catch (Exception e) {
            System.err.println("Error total credito"+e.getLocalizedMessage());
        }
    
        return total;
    }
    
    public void GenerarReporte(int estado,String nombre){
    
        
        try {
            URL path=this.getClass().getResource("/Reportes/reportCredito.jasper");
            System.out.println(path);
            JasperReport reporte=(JasperReport)JRLoader.loadObject(path);
              System.out.println("reporte"+reporte);
            HashMap parametros = new HashMap<>();
            parametros.put("estado", estado);
            parametros.put("nombre", nombre);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, Conexion.obtenerConexion());
            JasperViewer viewer = new JasperViewer(jasperPrint,false);
            viewer.setTitle("Cuentas pendientes");
            viewer.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(Credito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public boolean CuentaRegistros(int factura){
        boolean result = false;
        try
        {
            String query="SELECT count(*) as total from venta_credito where configuracion_id = ?";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            st.setInt(1, factura);
            ResultSet res=st.executeQuery();
            res.next();
            int total = res.getInt("total");
            
            if(total>1){
                
                result = true;
            }
            else{
                result = false;
            }
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar el total de creditos_ventas");
            
        }
        return result;
    }
    
}
/*

CREATE VIEW vcredito as

select p.estado,p.id,cl.id as idcliente,vc.configuracion_id as factura,sum(cantidad) as cantidad,sum(kilos)as kilos,preciokilo,sum(kilos)*preciokilo as total,cl.nombre,p.fecha  
from pedidos as p 
inner join configuracion as co on p.configuracion_id = co.id
inner join ruta as r on p.ruta_id = r.id
inner join destino as d on r.destino_id = d.id
inner join vventa_credito as vc on co.id = vc.configuracion_id
inner join credito as cr on vc.credito_id = cr.id
inner join cliente as cl on cr.cliente_id = cl.id
group by co.id

--where p.estado = 3
----------------------------
create view vventa_credito as

select*from venta_credito group by configuracion_id


*/