/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloMovimientoFactura;
import Vista.MovimientoFactura;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

/**
 *
 * @author jhon
 */
public class ControladorMovimientoFactura  implements ActionListener,MouseListener
{
    private Principal principal;
    private MovimientoFactura vistaMovimientoFactura;
    private ModeloMovimientoFactura modeloMovimientoFactura;
    private String folio="";
    public ControladorMovimientoFactura(Principal principal,MovimientoFactura vistaMovimientoFactura)
    {
        this.principal=principal;
        this.vistaMovimientoFactura=vistaMovimientoFactura;
        modeloMovimientoFactura=new ModeloMovimientoFactura();
    }
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaMovimientoFactura);
        vistaMovimientoFactura.setLocation(centradoXY(vistaMovimientoFactura));
        vistaMovimientoFactura.setVisible(true);
        vistaMovimientoFactura.setTitle("Movimiento de Factura");
        
        vistaMovimientoFactura.tablaFacturas.addMouseListener(this);
        vistaMovimientoFactura.tablaFacturas.setModel(modeloMovimientoFactura.obtenerFacturas());
        vistaMovimientoFactura.btn_imprimir.setActionCommand("imprimir");
        vistaMovimientoFactura.btn_imprimir.addActionListener(this);
        
        
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
          case "imprimir":
              System.out.println("Folio: "+folio);
              modeloMovimientoFactura.generarticket(folio);
              break;
/*-----------------------------------------*/              
          default:
              break;
              
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila=this.vistaMovimientoFactura.tablaFacturas.rowAtPoint(e.getPoint());

             if (fila > -1)
             {                
         
                 folio=String.valueOf(this.vistaMovimientoFactura.tablaFacturas.getValueAt(fila,1));
        
             }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
