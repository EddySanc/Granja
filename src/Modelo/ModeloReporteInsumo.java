/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhon
 */
public class ModeloReporteInsumo 
{
    
    private int idDestino;
    private String fechainicial;
    private String fechafinal;
    
     public void generarticket(String destino,String fechainicial,String fechafinal)
    {
            try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/ReporteInsumos.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();

                        parametros.put("destino", destino);
                        parametros.put("fechainicial", fechainicial);
                        parametros.put("fechafinal", fechafinal);
                                            
                
        		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
		                JasperViewer viewer = new JasperViewer(jasperPrint,false);
                    viewer.setTitle("Movimientos de Insumos");
                    viewer.setVisible(true);
                
            }
            catch(Exception er)
            {
                JOptionPane.showMessageDialog(null,"Error al generar reporte"+er.getCause()+""+er.getLocalizedMessage());
            }
    }
     
     /*----------Reporte de Venta e insumos----------------*/
     
          public void generarReporteGanancia(String destino,String fechainicial,String fechafinal)
    {
        
        if(destino.equals("Todos"))
        {
                    try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/GananciaVentasGeneral.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();

                      
                      
                        parametros.put("destino", destino);
                        parametros.put("fechainicial", fechainicial);
                        parametros.put("fechafinal", fechafinal);
                                            
                
        		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
		                JasperViewer viewer = new JasperViewer(jasperPrint,false);
                    viewer.setTitle("Movimientos de Ventas Credito/Contado");
                    viewer.setVisible(true);
                
            }
            catch(Exception er)
            {
                JOptionPane.showMessageDialog(null,"Error al generar reporte"+er.getCause()+""+er.getLocalizedMessage());
            }
        }
        else
        {    
        
            try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/GananciaVentas.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();

                      
                      
                        parametros.put("destino", destino);
                        parametros.put("fechainicial", fechainicial);
                        parametros.put("fechafinal", fechafinal);
                                            
                
        		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
		                JasperViewer viewer = new JasperViewer(jasperPrint,false);
                    viewer.setTitle("Movimientos de Insumos");
                    viewer.setVisible(true);
                
            }
            catch(Exception er)
            {
                JOptionPane.showMessageDialog(null,"Error al generar reporte"+er.getCause()+""+er.getLocalizedMessage());
            }
        }    
    }
    
    
}
