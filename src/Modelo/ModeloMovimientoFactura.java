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
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhon
 */
public class ModeloMovimientoFactura 
{
    private char folio;
    DefaultTableModel modelo;
    
    
    
    public ModeloMovimientoFactura()
    {
        
    }

    public char getFolio() {
        return folio;
    }

    public void setFolio(char folio) {
        this.folio = folio;
    }
    
    /*--------------query para obtener facturas*/
       public DefaultTableModel obtenerFacturas() //op =1 condicion destino
    {

        
        modelo=new DefaultTableModel();
        
        int registros=0;
        String [] columnas={"PROVEEDOR","FACTURA","CANTIDAD EXISTENTE","PRECIO x KILO","FECHA DE REGISTRO"};
        
        /*Recuperar datos de la BD*/
        try
        {
            String query="SELECT  " +
"     count(pollos.folio) as total, " +
"     pollos.cantidad AS pollos_cantidad, " +
"     pollos.fecha AS pollos_fecha, " +
"     proveedor.nombre AS proveedor_nombre, " +
"     proveedor.preciokilo AS proveedor_preciokilo " +
"FROM proveedor,pollos,historico " +
"where " +
"     proveedor.id=pollos.proveedor_id and " +
"     pollos.folio=historico.pollos_folio and (historico.pedidos_id=-1 or historico.pedidos_id=6 or historico.pedidos_id=7)";
            System.out.println("Query: "+query);
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla facturas"+e.getLocalizedMessage());
        }
        
        
  
   
        
        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos=new String[registros][5];
        
        try
        {
            String query="SELECT distinct " +
"     pollos.folio AS folio, " +
"     pollos.cantidad AS pollos_cantidad, " +
"     pollos.fecha AS pollos_fecha, " +
"     proveedor.nombre AS proveedor_nombre, " +
"     proveedor.preciokilo AS proveedor_preciokilo " +
" FROM proveedor,pollos,historico " +
" where  " +
"     proveedor.id=pollos.proveedor_id and " +
"     pollos.folio=historico.pollos_folio  group by folio";
            
            
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            int i=0;
            while(res.next())
            {
                datos[i][0]=res.getString("proveedor_nombre");
                datos[i][1]=res.getString("folio");
                datos[i][2]=res.getString("pollos_cantidad");
                datos[i][3]="$ "+res.getString("proveedor_preciokilo");
                datos[i][4]=res.getString("pollos_fecha"); 

                

                i++;
            }
            res.close();
            //añadir la matriz al modelo
    
            modelo.setDataVector(datos, columnas);
                 
            
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla facturas por"+e.getLocalizedMessage());
            System.out.println("Error : "+e.getMessage());
        }
        return modelo;
        
    }
    
       
       /*Generar reporte */
       public void generarticket(String folio)
    {
            try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/ReporteFactura.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();
                      parametros.put("factura", folio);
                
                
                    JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
                     JasperViewer viewer = new JasperViewer(jasperPrint,false);
                    viewer.setTitle("Movimientos de Ventas/Cancelación");
                    viewer.setVisible(true);
//                          JasperPrintManager.printReport(jasperPrint, false); 
//                    JRExporter exporter = new JRPdfExporter();
//                                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//                                            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("oso.pdf")); // your output goes here
//
//                    exporter.exportReport();
                              
                              
                
            }
            catch(Exception er)
            {
                JOptionPane.showMessageDialog(null,"Error al generar reporte "+er.getLocalizedMessage());
            }
    }
}
