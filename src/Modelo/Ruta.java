/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import Vista.CRUDRuta;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class Ruta 
{
    private int id;
    private int estado;
    private String fecha;
    private String observacion;
    private int chofer_id;
    private int destino_id;
    DefaultTableModel modelo;
    private double kilo;
    private int carro_id;
    private String destino;
    

    public Ruta() {
    }

    public Ruta(int id, int estado, String fecha, String observacion, int chofer_id, int destino_id) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.observacion = observacion;
        this.chofer_id = chofer_id;
        this.destino_id = destino_id;
    }

    public Ruta(int id,String destino)
    {
        this.id=id;
        this.destino=destino;
    }
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    
    public double getKilo() {
        return kilo;
    }

    public void setKilo(double kilo) {
        this.kilo = kilo;
    }

    public int getCarro_id() {
        return carro_id;
    }

    public void setCarro_id(int carro_id) {
        this.carro_id = carro_id;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getChofer_id() {
        return chofer_id;
    }

    public void setChofer_id(int chofer_id) {
        this.chofer_id = chofer_id;
    }

    public int getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(int destino_id) {
        this.destino_id = destino_id;
    }
    
    /*Llenar combos*/
    public ArrayList<Chofer> listaChofer()
    {
        ArrayList<Chofer> lista=new ArrayList<>();
        
        try
        {
            //String query="SELECT * FROM chofer order by fecha DESC";
            String query="SELECT * FROM chofer where id not in (SELECT chofer_id from ruta where estado=1)";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
                lista.add(new Chofer(res.getInt("id"),res.getString("nombre")));
                //lista.add(res.getString("nombre"));
            }
            
            res.close();
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al cargar los conductores");
        }
        
        return lista;
    }
    
    /*Llenar combo carros*/
        public ArrayList<Carro> listaCarros()
    {
        ArrayList<Carro> lista=new ArrayList<>();
        
        try
        {
            //String query="SELECT * FROM carro order by id DESC";
            //String query="SELECT * FROM carro where id not in (SELECT carro_id from destino)";
            String query="SELECT * FROM carro where id not in (select carro_id from destino where id not in (select destino_id from ruta where estado=0))";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next())
            {
                //lista.add(res.getString("placas")+" -----"+res.getString("descripcion"));
                lista.add(new Carro(res.getInt("id"),res.getString("placas"),res.getString("descripcion")));
            }
            
            res.close();
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al cargar los autos");
        }
        
        return lista;
    }
    
        /*Tabla destinos*/
   public DefaultTableModel obtenerRutas(String dato)
    {
        String condicion;
        if(dato.equals(""))
        {
            condicion="";
        }
        else
        {
            condicion=" AND (chofer.nombre LIKE '%"+dato+"%' OR destino.nombre LIKE '%"+dato+"%') ";
        
        }
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"ID","CONDUCTOR","DESTINO","PLACAS","DESCRIPCIÓN AUTO","PRECIO KILO","COMENTARIOS","ID"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="select count(*) as total, "
                    + "ruta.id as id,chofer.nombre as chofer,destino.nombre as destino,"
                    + "carro.placas as placas,carro.descripcion as descripcion,destino.preciokilo as kilo from ruta,"
                    + "destino,chofer,carro "
                    + "where chofer.id=ruta.chofer_id and  "
                    + "ruta.destino_id=destino.id and "
                    + "destino.carro_id=carro.id and ruta.estado=1 "+condicion;
        
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
        Object[][] datos=new String[registros][8];
        System.out.println("Total de Registros: "+registros);
        try
        {
            String query="select ruta.destino_id as iddestino, "
                     + "ruta.id as id,chofer.nombre as chofer,destino.nombre as destino,"
                    + "carro.placas as placas,carro.descripcion as descripcion,destino.preciokilo as kilo,ruta.observacion as comentario from ruta,"
                    + "destino,chofer,carro "
                    + "where chofer.id=ruta.chofer_id and  "
                    + "ruta.destino_id=destino.id and "
                    + "destino.carro_id=carro.id and ruta.estado=1 "+condicion+" order by ruta.fecha DESC";
    
            System.out.println("Query Ruta: "+query);    
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("id");
                datos[i][1]=res.getString("chofer");
                datos[i][2]=res.getString("destino");
                datos[i][3]=res.getString("placas");
                datos[i][4]=res.getString("descripcion");
                datos[i][5]=res.getString("kilo");
                datos[i][6]=res.getString("comentario");
                datos[i][7]=res.getString("iddestino");

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Rutas"+e.getMessage());
        }
        return modelo;
        
    }
   /*Guardar una ruta*/
   public boolean registrarRuta(Ruta ruta)
   {
      
       try
       { 
           ResultSet rs;
           CallableStatement cs = Conexion.obtenerConexion().prepareCall("{call registroRuta (?,?,?,?,?,?)}");
           cs.setString(1, ruta.getDestino());
           cs.setDouble(2, ruta.getKilo());
           cs.setInt(3, ruta.getChofer_id());
           cs.setInt(4,ruta.getCarro_id());
           cs.setString(5, ruta.getObservacion());  
           cs.setInt(6, Usuario.idUsuario);
           
           rs=cs.executeQuery();
           rs.first();
           int resultado=rs.getInt("Estado");    
           
           
           
          
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
           JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar la ruta "+e.getLocalizedMessage().toString());
           return false;
       }
   }
   
   
   /*eliminar una ruta */
   public boolean eliminarRuta(int id)
   {

       String query="UPDATE ruta set estado=0 where id="+id;
       
       try
       {
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
           
       }catch(Exception e)
       {
           JOptionPane.showMessageDialog(null,"Error al eliminar una ruta"+e.getLocalizedMessage());
           return false;
       }
       
   }
   /*Actualizar ruta*/
   
   
   
   
   /*
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
   */
   

   /*Limpiar cajas*/
   public void limpiar(CRUDRuta vistaRuta)
   {
       vistaRuta.txt_buscar.setText("");
       vistaRuta.txt_comentario.setText("");
       vistaRuta.txt_kilo.setText("0");
       vistaRuta.txt_destino.setText("");
   }
   
   public boolean validarCombos(int x,int y)
   {
       if(x==-1 || y==-1)
               {
                   return false;
               }
       else
       {
           return true;
       }
   }
   
    public boolean validarEspacios(String ...x)
    {
        boolean estado=false;
        for(int i=0; i<x.length; i++)
        {
            System.out.println("Valor de los X: "+x[i]);
            if(x[i].equalsIgnoreCase("") || x[i].equalsIgnoreCase("0.0"))
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
    /*--------------------------------------------------------------------------------------------*/
    public boolean registrarHistorico(int id_destino,double kilo,String obs)
   {
      
       try
       { 
           CallableStatement cs = Conexion.obtenerConexion().prepareCall("{call registroHistorico (?,?,?)}");
           cs.setInt(1, id_destino);
           cs.setDouble(2, kilo);
           cs.setString(3, obs);
           
           cs.executeUpdate();
           
           cs.close();

           return true;
           
       
       }catch(Exception e)
       {
           JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar la ruta "+e.getLocalizedMessage().toString());
           return false;
       }
   }
   
   
}
