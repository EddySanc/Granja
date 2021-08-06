/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Carro;
import Modelo.Usuario;
import Vista.CRUDCarros;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class ControladorCarro implements ActionListener,MouseListener
{
     ControladorPrincipal main;
     Principal principal;
     int idCarro;
     CRUDCarros vistaCarro;
     Carro modeloCarro;
     
     public ControladorCarro(Principal principal,ControladorPrincipal main,CRUDCarros vistaCarro)
     {
         this.principal=principal;
         this.main=main;
         this.vistaCarro=vistaCarro;
         //this.vistaCarro.setMaximizable(true);
         modeloCarro=new Carro();
     }
     
     /*Ventana Inicial*/
     public void abrirVentana()
     {
         this.principal.contenedor.add(vistaCarro);
     
         this.vistaCarro.setLocation(centradoXY(vistaCarro));
         this.vistaCarro.setVisible(true);
         
         
         /*Enlace*/
         this.vistaCarro.txt_buscar.setActionCommand("buscar");
         this.vistaCarro.btn_agregar.setActionCommand("agregar");
         this.vistaCarro.btn_eliminar.setActionCommand("eliminar");
         this.vistaCarro.btn_actualizar.setActionCommand("actualizar");
         this.vistaCarro.btn_limpiar.setActionCommand("limpiar");
         this.vistaCarro.btn_reportes.setActionCommand("reportes");
         
         /*Escuchas*/
         this.vistaCarro.txt_buscar.addActionListener(this);
         this.vistaCarro.btn_agregar.addActionListener(this);
         this.vistaCarro.btn_eliminar.addActionListener(this);
         this.vistaCarro.btn_actualizar.addActionListener(this);
         this.vistaCarro.btn_limpiar.addActionListener(this);
         this.vistaCarro.btn_reportes.addActionListener(this);
         
         /*Enlazar Tabla*/
         this.vistaCarro.tablaCarro.addMouseListener(this);
         this.vistaCarro.tablaCarro.setModel(this.modeloCarro.obtenerCarros(""));
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setMaxWidth(0);
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setMinWidth(0);
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setPreferredWidth(0);
         this.modeloCarro.limpiarCajas(vistaCarro);
         this.modeloCarro.habilitar(vistaCarro, true,false,false);
         
         
         
     }
     
     
     
     

    @Override
    public void actionPerformed(ActionEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       String accion=e.getActionCommand();
       String placas=vistaCarro.txt_placas.getText();
       String descripcion=vistaCarro.txt_descripcion.getText();
       
       switch(accion)
       {
           case "buscar":
               String busqueda=vistaCarro.txt_buscar.getText();
               vistaCarro.tablaCarro.setModel(this.modeloCarro.obtenerCarros(busqueda));
               this.valoresTabla();
               break;           
/*___________________________________________________________________________________________________________________________________________________________________*/             
           case "agregar":
               
               if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
               if(modeloCarro.validarEspacios(placas,descripcion))
               {
                   Carro alta=new Carro();
                   alta.setPlacas(placas);
                   alta.setDescripcion(descripcion);
                   if(alta.registrarCarro(alta))
                   {
                       vistaCarro.tablaCarro.setModel(this.modeloCarro.obtenerCarros(""));
                       this.valoresTabla();
                       JOptionPane.showMessageDialog(principal, "Registro actualizado correctamente");
                   }else
                   {
                       JOptionPane.showMessageDialog(principal, "Error al registrar un automovil");
                   }
                   
               }else
               {
                   JOptionPane.showMessageDialog(principal,"Existen casillas sin rellenar datos");
               }
               break;
/*___________________________________________________________________________________________________________________________________________________________________*/  
           case "actualizar":
               if(Usuario.modificar == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
               if(modeloCarro.validarEspacios(placas,descripcion))
               {
                   Carro update=new Carro();
                   update.setPlacas(placas);
                   update.setDescripcion(descripcion);
                   update.setId(idCarro);
                   if(update.actualizarCarro(update))
                   {
                       vistaCarro.tablaCarro.setModel(this.modeloCarro.obtenerCarros(""));
                       this.valoresTabla();
                       JOptionPane.showMessageDialog(principal, "Registro actualizado correctamente");
                   }else
                   {
                       JOptionPane.showMessageDialog(principal, "Error al actualizar datos");
                   }
                   
               }else
               {
                    JOptionPane.showMessageDialog(principal, "Existe una casilla en blanco, Verifique");   
               }
               break;
/*___________________________________________________________________________________________________________________________________________________________________*/                 
           case "eliminar":
              
              if(Usuario.eliminar == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                } 
              int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el Carro?","AVISO",JOptionPane.YES_NO_OPTION);

                Carro eliminar=new Carro();
                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarCarro(idCarro))
                             {
                                vistaCarro.tablaCarro.setModel(this.modeloCarro.obtenerCarros(""));
                                this.valoresTabla();
                                 JOptionPane.showMessageDialog(principal, "Coche eliminado correctamente");
                                 
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(principal, "Ocurrio un error al eliminar el Carro, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    principal.remove(dialogButton);
                }
               break;
/*___________________________________________________________________________________________________________________________________________________________________*/  
           default:
               JOptionPane.showMessageDialog(principal,"Opción no válida, El sistema se cerrará");
               System.exit(0);
               break;
       }
      
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = this.vistaCarro.tablaCarro.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
             this.modeloCarro.habilitar(vistaCarro, false,true,true);
             
             //this.idProveedor=String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 0));
             this.idCarro=Integer.parseInt(String.valueOf(this.vistaCarro.tablaCarro.getValueAt(fila, 0)));
             this.vistaCarro.txt_placas.setText(String.valueOf(this.vistaCarro.tablaCarro.getValueAt(fila, 1)));
             this.vistaCarro.txt_descripcion.setText(String.valueOf(this.vistaCarro.tablaCarro.getValueAt(fila, 2)));
             }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
        /*cargar Tabla*/
        private void valoresTabla()
        {
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setMaxWidth(0);
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setMinWidth(0);
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setPreferredWidth(0);
         this.modeloCarro.limpiarCajas(vistaCarro);
         this.modeloCarro.habilitar(vistaCarro, true,false,false);
         
        }
}
