/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JRExporter; 
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhon
 */
public class ModeloImpresionRutas 
{
    
    private String fecha;
    private String destino;
    private int foliofactura;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getFoliofactura() {
        return foliofactura;
    }

    public void setFoliofactura(int foliofactura) {
        this.foliofactura = foliofactura;
    }
    
    
    /*Generar reporte */
       public void generarticket(String destino,String fecha)
    {
            try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/ReporteRuta.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();
                      parametros.put("destino", destino);
                      parametros.put("fecha", fecha);
                
                    JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
                     JasperViewer viewer = new JasperViewer(jasperPrint,false);
                    viewer.setTitle("Lista de Pedidos, RUTA: "+destino);
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
