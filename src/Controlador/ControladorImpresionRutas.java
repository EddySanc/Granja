/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Factura;
import Modelo.ModeloImpresionRutas;
import Modelo.ReporteTicket;
import Modelo.Ruta;
import Vista.ImpresionRutas;
import Vista.Principal;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class ControladorImpresionRutas  implements ActionListener,ItemListener
{
  Principal principal;
  ImpresionRutas vistaImpresionRutas;
  private ArrayList<Ruta> listaRuta;
  private Factura modeloFactura;
  private String destino="";

  
  
  public ControladorImpresionRutas(Principal principal,ImpresionRutas visImpresionRutas)
  {
      this.principal=principal;
      this.vistaImpresionRutas=visImpresionRutas;
      modeloFactura=new Factura();

  }
    
  public void abrirVentana()
  {
      
      this.principal.contenedor.add(vistaImpresionRutas);
      vistaImpresionRutas.setLocation(centradoXY(vistaImpresionRutas));
      vistaImpresionRutas.setVisible(true);
      vistaImpresionRutas.setTitle("Impresion de Facturas");
   
      /*escucha botones*/
      this.vistaImpresionRutas.btn_buscarFactura.setActionCommand("buscarFactura");//buscar rutas del dia indicado
      this.vistaImpresionRutas.btn_buscarFactura.addActionListener(this);
      this.vistaImpresionRutas.btn_buscarRuta.setActionCommand("buscarRuta");
      this.vistaImpresionRutas.btn_buscarRuta.addActionListener(this);
      this.vistaImpresionRutas.comboRutas.addItemListener(this);
      this.vistaImpresionRutas.txt_numeroFactura.setValue(new Integer(0));
      this.vistaImpresionRutas.txt_numeroFactura.setText("0");
     
      llenarCombo();
     
      
      
      
  }
  
  
    public void llenarCombo()
     {
         
         listaRuta=modeloFactura.obtenerRutas();
         this.vistaImpresionRutas.comboRutas.removeAllItems();
         for(int i=0; i<listaRuta.size(); i++)
         {
             this.vistaImpresionRutas.comboRutas.addItem(listaRuta.get(i).getDestino());
             //this.vistaRuta.comboConductor.addItem(listaChofer.get(i).getNombre());
         }
         //this.vistaFactura.comboRutas
     }
  
  
  
  
  
   /*_Centrar Jinternal*/
    private Point centradoXY(JInternalFrame jif)
    {
        Point p = new Point(0,0);
        //se obtiene dimension del contenedor
        Dimension pantalla = this.principal.contenedor.getSize();
        //se obtiene dimension del JInternalFrame
        Dimension ventana = jif.getSize();
        //se calcula posición para el centrado
        p.x = (pantalla.width - ventana.width) / 2;
        p.y = (pantalla.height - ventana.height) / 2;
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      String accion=e.getActionCommand();
      
      
      switch(accion)
      {
          case "buscarRuta":
              if(vistaImpresionRutas.txt_fecha.getDate()!=null)
              {
                    
                    Date date=this.vistaImpresionRutas.txt_fecha.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    String fecha=sdf.format(date);
                   
                    ModeloImpresionRutas reporte=new ModeloImpresionRutas();
                    reporte.generarticket(destino, fecha);
                    
                    
              }
              else
              {
                    JOptionPane.showMessageDialog(principal,"Favor de escribir una fecha");
              }
             
            
              
              break;
/*-------------------------------------------------------------------------------------------------------------------------------------*/
          case "buscarFactura":
              if(Integer.parseInt(this.vistaImpresionRutas.txt_numeroFactura.getText())>0)
              {
                  ReporteTicket repor=new ReporteTicket();
                  repor.generarReImpresionticket(Integer.parseInt(this.vistaImpresionRutas.txt_numeroFactura.getText()));
              }
              else
              {
                   JOptionPane.showMessageDialog(principal,"Factura Inválida");
              }
              break;
/*-------------------------------------------------------------------------------------------------------------------------------------*/
          default:
              
              break;
      }
    
    
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
           if (e.getStateChange() == ItemEvent.SELECTED) {
            Object item = e.getItem();
        // System.out.println("Seleccion: "+item);
              if(e.getSource().equals(this.vistaImpresionRutas.comboRutas))
                {
                    
                    this.destino=this.vistaImpresionRutas.comboRutas.getSelectedItem().toString(); 
                  //  System.out.println("destino: "+destino);
                    
                }
       
       }
        
        
    }
  
}
