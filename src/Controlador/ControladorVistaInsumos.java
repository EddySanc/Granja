/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Factura;
import Modelo.ModeloReporteInsumo;
import Modelo.Ruta;
import Vista.Principal;
import Vista.VistaInsumos;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class ControladorVistaInsumos implements ActionListener,ItemListener
{

    
      private Principal principal;    
      private VistaInsumos vistaReporteInsumos;
      private ArrayList<Ruta> listaRuta;
      private Factura modeloFactura;
      private int idDestino;
      private String destino;
     
      public ControladorVistaInsumos(Principal principal,VistaInsumos vistaInsumos)
      {
          this.principal=principal;
          this.vistaReporteInsumos=vistaInsumos;
          modeloFactura=new Factura();
      }
      
      public void abrirVentana()
      {
          
          this.principal.contenedor.add(vistaReporteInsumos);
          this.vistaReporteInsumos.setLocation(centradoXY(vistaReporteInsumos));
          this.vistaReporteInsumos.setVisible(true);
          this.vistaReporteInsumos.setTitle("Reporte Venta por Destino | Reporte de Insumos");
          
          this.vistaReporteInsumos.btn_buscar.setActionCommand("buscar");
          this.vistaReporteInsumos.btn_buscar.addActionListener(this);
          this.vistaReporteInsumos.comboDestino.addItemListener(this);
          this.vistaReporteInsumos.grupoBotones.add(this.vistaReporteInsumos.boton_destino);
          this.vistaReporteInsumos.grupoBotones.add(this.vistaReporteInsumos.boton_insumos);

          this.vistaReporteInsumos.boton_destino.setSelected(true);
          this.vistaReporteInsumos.boton_insumos.setSelected(false);
          
          llenarCombo();

          
      }
      
      
            /*llenar lista*/
     public void llenarCombo()
     {
         
         listaRuta=modeloFactura.obtenerRutas2();
         this.vistaReporteInsumos.comboDestino.removeAllItems();

         for(int i=0; i<listaRuta.size(); i++)
         {
             //this.vistaFactura.comboRutas.addItem(listaRuta.get(i).getDestino());
             this.vistaReporteInsumos.comboDestino.addItem(listaRuta.get(i).getDestino());
             //this.vistaRuta.comboConductor.addItem(listaChofer.get(i).getNombre());
         }
         this.vistaReporteInsumos.comboDestino.addItem("Todos");
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
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        String accion=e.getActionCommand();
       
       switch(accion)
       {
           case "buscar":
               
               if(vistaReporteInsumos.fechafinal.getDate()!=null  && vistaReporteInsumos.fechainicial.getDate()!=null)
               {
                   
                   System.out.println("Boton: "+this.vistaReporteInsumos.boton_destino.isSelected());
                    System.out.println("Boton: "+this.vistaReporteInsumos.boton_insumos.isSelected());
                   
                   Date fecha1=this.vistaReporteInsumos.fechainicial.getDate();
                   Date fecha2=this.vistaReporteInsumos.fechafinal.getDate();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                   
                   String fechaIni=sdf.format(fecha1);
                   String fechaFin=sdf.format(fecha2);
                   
                   if(this.vistaReporteInsumos.boton_insumos.isSelected()==true && this.vistaReporteInsumos.boton_destino.isSelected()==false)
                   {
                     ModeloReporteInsumo x=new ModeloReporteInsumo();
                 
                    x.generarticket(destino, fechaIni, fechaFin);
                   }
                   else if(this.vistaReporteInsumos.boton_insumos.isSelected()==false && this.vistaReporteInsumos.boton_destino.isSelected()==true)
                   {
                        ModeloReporteInsumo xy=new ModeloReporteInsumo();
                 
                    xy.generarReporteGanancia(destino, fechaIni, fechaFin);
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null, "Opcion no válida");
                       System.exit(0);
                   }
                   
                 
                   
                   
                   
              
               }
               else
               {
                    JOptionPane.showMessageDialog(principal,"Favor de escribir ambas fechas");
               }
               
               /*
             
                    Date date=this.vistaImpresionRutas.txt_fecha.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    String fecha=sdf.format(date);
               */
               break;
/*-------------------------------------------------------------------------------------*/
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
              if(e.getSource().equals(this.vistaReporteInsumos.comboDestino))
                {
                  //idDestino=listaRuta.get(vistaReporteInsumos.comboDestino.getSelectedIndex()).getId();
                  //  System.out.println("destino: "+destino);
                   destino=this.vistaReporteInsumos.comboDestino.getSelectedItem().toString();
                    
                }
       
       }
    }
    
    
}
