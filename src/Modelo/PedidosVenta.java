/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.Pedidos;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class PedidosVenta 
{
    /**/
    
          
    
    /*Variables de clase*/
    private int idRuta;
    private String destino;
    private String observacion;
    private String conductor;
    private String placas;
    private String descripcion_auto;
    private double precio_kilo;
    private int idReja;
    private String descripcion;
    private int aves;
    private double kilos;
    private int factura;
    private int total_rejas;
    private double precio_total;
    private String comentario_ruta;
    private int cantidadAves;
    
    /*02.04.19*/
    private String foliofactura;
    
    

    public PedidosVenta() {
    }
    
    public PedidosVenta(int idRuta,String conductor,String destino,String placas,String descripcion_auto,double kilos,String comentario_ruta)
    {
        this.idRuta=idRuta;
        this.conductor=conductor;
        this.destino=destino;
        this.placas=placas;
        this.descripcion_auto=descripcion_auto;
        this.precio_kilo=kilos;
        this.comentario_ruta=comentario_ruta;
        
    }
    
    //02.04.19
    public String getFoliofactura() {    
        return foliofactura;
    }

 
    public void setFoliofactura(String foliofactura) {
        this.foliofactura = foliofactura;
    }

    
    public String getDescripcion_auto() {
        return descripcion_auto;
    }

    public void setDescripcion_auto(String descripcion_auto) {
        this.descripcion_auto = descripcion_auto;
    }

    public String getComentario_ruta() {
        return comentario_ruta;
    }

    public void setComentario_ruta(String comentario_ruta) {
        this.comentario_ruta = comentario_ruta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCantidadAves() {
        return cantidadAves;
    }

    public void setCantidadAves(int cantidadAves) {
        this.cantidadAves = cantidadAves;
    }
    
    

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public double getPrecio_kilo() {
        return precio_kilo;
    }

    public void setPrecio_kilo(double precio_kilo) {
        this.precio_kilo = precio_kilo;
    }

    public int getIdReja() {
        return idReja;
    }

    public void setIdReja(int idReja) {
        this.idReja = idReja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAves() {
        return aves;
    }

    public void setAves(int aves) {
        this.aves = aves;
    }

    public double getKilos() {
        return kilos;
    }

    public void setKilos(double kilos) {
        this.kilos = kilos;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getTotal_rejas() {
        return total_rejas;
    }

    public void setTotal_rejas(int total_rejas) {
        this.total_rejas = total_rejas;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }
    
    /*Recoger rutas*/
    public ArrayList<PedidosVenta> obtenerRutas()
    {
        ArrayList<PedidosVenta> lista=new ArrayList<>();
        
        try
        {
        String query="SELECT * FROM rutasDisponibles";
          PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
                //lista.add(res.getString("placas")+" -----"+res.getString("descripcion"));1
                /*
                  public PedidosVenta(int idRuta,String conductor,String destino,String placas,String descripcion_auto,double kilos,String comentario_ruta)
                */
                lista.add(new PedidosVenta(
                        res.getInt("id"),
                        res.getString("chofer"),
                        res.getString("destino"),
                        res.getString("placas"),
                        res.getString("descripcion"),
                        res.getDouble("kilo"),
                        res.getString("comentario")
                
                ));
                
            }
            
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar las rutas"+e.getLocalizedMessage());
        }
        
        return lista;
    }
    
    
    /*limpiar*/
    public void limpiarCampos(Pedidos vista)
    {
     vista.txt_aves.setText("0");
     vista.txt_kilos.setText("0");
    }
    
    public void limpiarTodo(Pedidos vista)
    {
        vista.txt_total_rejas.setText("0");
        vista.txt_total.setText("0");
        vista.txt_aves.setText("0");
        Pedidos.txt_kilos.setText("0");
    }
    /*registro de pedidos*/
    public boolean registrarRuta(PedidosVenta pedidos)
   {
      
       try
       { 
           ResultSet rs;
           CallableStatement cs = Conexion.obtenerConexion().prepareCall("{call registroPedidos (?,?,?,?,?,?,?)}");
           cs.setInt(1,pedidos.getCantidadAves());
           cs.setDouble(2, pedidos.getKilos());
           cs.setInt(3, pedidos.getIdRuta());
           cs.setInt(4, pedidos.getIdReja());
           cs.setInt(5, pedidos.getFactura());
           cs.setString(6, pedidos.getFoliofactura());
           cs.setInt(7, Usuario.idUsuario);
           
           rs=cs.executeQuery();
           rs.first();
           int resultado=rs.getInt("Estado");    
           
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
           JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar el pedido "+e.getLocalizedMessage().toString());
           return false;
       }
   }
    
   public boolean RegistrarVentaCredito(int factura,int idCliente,double monto){
       
       try {
           String query = "Insert into credito values(0,?,1,now(),?)";
           PreparedStatement pst = Conexion.obtenerConexion().prepareStatement(query);
           pst.setDouble(1, monto);
           pst.setInt(2, idCliente);
           pst.executeUpdate();
           
           query = "Insert into venta_credito values (0,(select max(id) from credito),?,?)";
           pst = Conexion.obtenerConexion().prepareStatement(query);
           pst.setInt(1,factura);
           pst.setInt(2,Usuario.idUsuario);
           pst.executeUpdate();
           
           query = "update pedidos set estado = 3 where configuracion_id = ?";
           pst = Conexion.obtenerConexion().prepareStatement(query);
           pst.setInt(1, factura);
           pst.executeUpdate();
           
           query = "UPDATE historico set tipo = 3 where pedidos_id in (select id from pedidos where configuracion_id = ?)";
           pst = Conexion.obtenerConexion().prepareStatement(query);
           pst.setInt(1, factura);
           pst.executeUpdate();
           return true;
           
       } 
       catch (Exception e) {
           return  false;
       }
  
   }
    
}
