/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

	 
import Bd.Conexion;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author jhon
 */
public class ReporteTicket {
    
    public void generarticket(int valor)
    {
            try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/ticket.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();
                      parametros.put("factura", valor);
                
        		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
		              JasperPrintManager.printReport(jasperPrint, false); 
                
            }
            catch(Exception er)
            {
                JOptionPane.showMessageDialog(null,"Error al generar reporte"+er.getLocalizedMessage());
                Logger.getLogger(Credito.class.getName()).log(Level.SEVERE, null, er);
            }
    }
    
    
        public void generarReImpresionticket(int valor)
    {
            try
            {
                //File miDir = new File (Crear.class.getResource("/Reportes/ticket.jasper").getFile());
                 //File miDir=new File("/Reportes/ticket.jasper");
                URL entrada=this.getClass().getResource("/Reportes/Reticket.jasper");
                JasperReport jasperReport;
                  jasperReport=(JasperReport)JRLoader.loadObject(entrada);
                  
                      HashMap parametros = new HashMap<>();
                      parametros.put("factura", valor);
                
        		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parametros, Conexion.obtenerConexion());
		              //JasperPrintManager.printReport(jasperPrint, false); 
                             
                     JasperViewer viewer = new JasperViewer(jasperPrint,false);
                    viewer.setTitle("Factura No."+valor);
                    viewer.setVisible(true);
                
            }
            catch(Exception er)
            {
                JOptionPane.showMessageDialog(null,"Error al generar reporte"+er.getLocalizedMessage());
                System.out.println("Error 3"+er.getMessage());
            }
    }
    
}
