/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.EstadosFacturas;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author machine
 */
public class ControladorEstadoFactura  implements ActionListener,MouseListener,ItemListener
{
    private Principal principal;
    private EstadosFacturas vistaEstadoFacturas;
    private int valor;
    private String descripcion;
    
    
    public ControladorEstadoFactura(Principal principal,EstadosFacturas vistaEstadosFacturas)
    {
        this.principal=principal;
        this.vistaEstadoFacturas=vistaEstadosFacturas;
        
    }
    
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaEstadoFacturas);
        this.vistaEstadoFacturas.setLocation(centradoXY(vistaEstadoFacturas));
        this.vistaEstadoFacturas.setVisible(true);
        this.vistaEstadoFacturas.setTitle("Estados de Facturas: Tránsito,Pagadas,Canceladas y Crédito");
       this.llenarComboEstados();
        this.vistaEstadoFacturas.btn_buscar.setActionCommand("buscar");
        this.vistaEstadoFacturas.btn_buscar.addActionListener(this);
        this.vistaEstadoFacturas.comboEstado.addItemListener(this);
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
    //lleba combo
       public void llenarComboEstados()
     {
         this.vistaEstadoFacturas.comboEstado.removeAllItems();
         this.vistaEstadoFacturas.comboEstado.addItem("Elegir");
         this.vistaEstadoFacturas.comboEstado.addItem("Tránsito");
         this.vistaEstadoFacturas.comboEstado.addItem("Pagada");
         this.vistaEstadoFacturas.comboEstado.addItem("Cancelada");
         this.vistaEstadoFacturas.comboEstado.addItem("Crédito (Vigente)");
         this.vistaEstadoFacturas.comboEstado.addItem("Crédito (Pagado)");
        
         //this.vistaFactura.comboRutas
     }

       private int getValor(String cadena)
       {
           int i=0;
           switch(cadena)
           {
               case "Elegir":
                   i=0;
                   break;
               case "Tránsito":
                   i=1;
                   break;
               case "Pagada":
                   i=2;
                   break;
               case "Cancelada":
                   i=4;
                   break;
               case "Crédito (Vigente)":
                   i=3;
                   break;
               case "Crédito (Pagado)":
                   i=5;
                   break;
           }
           
           return i;
       }
       
    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         String accion=e.getActionCommand();
      
      
      switch(accion)
      {
      
          case "buscar":
              if(this.vistaEstadoFacturas.txt_fechainicial.getDate()!=null && this.vistaEstadoFacturas.txt_fechafinal.getDate()!=null)
              {
                  
                   
                    Date date=this.vistaEstadoFacturas.txt_fechainicial.getDate();
                    Date date2=this.vistaEstadoFacturas.txt_fechafinal.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    String fecha1=sdf.format(date);
                    String fecha2=sdf.format(date2);
                    int opcion=getValor(descripcion);
                    
                  
                  
              }else
              {
                    JOptionPane.showMessageDialog(principal,"Favor de escribir ambas fechas");
              }
              break;
              
          default:
              JOptionPane.showMessageDialog(null, "Opción no válida");
              System.exit(0);
              break;
      }
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
             if (e.getStateChange() == ItemEvent.SELECTED) {
            Object item = e.getItem();
        // System.out.println("Seleccion: "+item);
              if(e.getSource().equals(this.vistaEstadoFacturas.comboEstado))
                {
                    
                    this.descripcion=this.vistaEstadoFacturas.comboEstado.getSelectedItem().toString(); 
                System.out.println("destino: "+descripcion);
                    
                }
       
       }
    }
}