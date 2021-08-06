/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reja;
import Modelo.Usuario;
import Vista.CRUDReja;
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
public class ControladorReja implements ActionListener,MouseListener
{
    
    Principal principal;    
    CRUDReja vistaReja;
    Reja modeloReja;
    int idReja;
    
    public ControladorReja(Principal principal, CRUDReja vistaReja)
    {
            this.principal=principal;
            this.vistaReja=vistaReja;
            this.modeloReja=new Reja();
    }
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaReja);
        this.vistaReja.setLocation(centradoXY(vistaReja));
        this.vistaReja.setVisible(true);
        
        this.vistaReja.txt_numero.setValue(new Integer(1));
        this.vistaReja.txt_buscar.setActionCommand("buscar");
        this.vistaReja.btn_actualizar.setActionCommand("actualizar");
        this.vistaReja.btn_agrear.setActionCommand("agregar");
        this.vistaReja.btn_eliminar.setActionCommand("eliminar");
        this.vistaReja.btn_limpiar.setActionCommand("limpiar");
        
        /*Escuchas*/
        this.vistaReja.btn_actualizar.addActionListener(this);
        this.vistaReja.btn_agrear.addActionListener(this);
        this.vistaReja.btn_eliminar.addActionListener(this);
        this.vistaReja.btn_limpiar.addActionListener(this);
        this.vistaReja.txt_buscar.addActionListener(this);
        
        this.vistaReja.tablaRejas.addMouseListener(this);
        this.vistaReja.tablaRejas.setModel(this.modeloReja.obtenerCarros(""));
        this.ocultarId();
        modeloReja.limpiarCampos(vistaReja,false,true,false,true );
  
        
    }
    
    private void ocultarId()
    {
        this.vistaReja.tablaRejas.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vistaReja.tablaRejas.getColumnModel().getColumn(0).setMinWidth(0);
        this.vistaReja.tablaRejas.getColumnModel().getColumn(0).setPreferredWidth(0);
        /*
        vistaChofer.tablaChofer.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setMinWidth(0);
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setPreferredWidth(0);
        */
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
         int numero=1;
            try
                {
                    numero=Integer.parseInt(vistaReja.txt_numero.getText());
                }
            catch(Exception ex)
                {
                    numero=1;
                }
        
         String descripcion=vistaReja.txt_descripcion.getText();
         
         System.out.println("ACCION; "+accion);
           switch(accion)
           {
               case "buscar":
                   String buscar=this.vistaReja.txt_buscar.getText();
                    this.vistaReja.tablaRejas.setModel(this.modeloReja.obtenerCarros(buscar));
                    this.ocultarId();
                    modeloReja.limpiarCampos(vistaReja,false,true,false,true );
                   break;
/*___________________________________________________________________________________________________________________________________________________________________*/  
               case "eliminar":
                   if(Usuario.eliminar == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                    break;
                }
                    int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar la reja?","AVISO",JOptionPane.YES_NO_OPTION);

                    Reja eliminar=new Reja();
                    if(dialogButton == JOptionPane.YES_OPTION) 
                        {
                            if(eliminar.eliminarReja(idReja))
                             {
                                 this.vistaReja.tablaRejas.setModel(this.modeloReja.obtenerCarros(""));
                                this.ocultarId();
                                modeloReja.limpiarCampos(vistaReja,false,true,false,true);
                                 JOptionPane.showMessageDialog(principal, "Reja eliminada correctamente");
                                 
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(principal, "Ocurrio un error al eliminar la reja, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    principal.remove(dialogButton);
                }
                   break;
/*___________________________________________________________________________________________________________________________________________________________________*/                    
               case "actualizar":
                    if(Usuario.modificar == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                    break;
                    }
                   if(!vistaReja.txt_numero.getText().equals("0") && !vistaReja.txt_descripcion.getText().equalsIgnoreCase(""))
                   {
                       Reja up=new Reja();
                       up.setNumero(Integer.parseInt(vistaReja.txt_numero.getText()));
                       up.setDescripcion(descripcion);
                       up.setId(idReja);
                       if(up.actualizarReja(up))
                       {
                        this.vistaReja.tablaRejas.setModel(this.modeloReja.obtenerCarros(""));
                    this.ocultarId();
                    modeloReja.limpiarCampos(vistaReja,false,true,false,true );
                            JOptionPane.showMessageDialog(principal,"Reja actualizada correctamente");
                       }else
                       {
                            JOptionPane.showMessageDialog(principal,"Ocurrio un error al registrar la reja");
                       }
                       
                       
                   }else
                   {
                   JOptionPane.showMessageDialog(principal,"No se permiten espacios vacios o el número de reja es incorrecto");     
                   }
                   break;
/*___________________________________________________________________________________________________________________________________________________________________*/                     
                   
               case "limpiar":
                   modeloReja.limpiarCampos(vistaReja, false,true,false,true);
                   this.vistaReja.tablaRejas.setModel(this.modeloReja.obtenerCarros(""));
                     this.ocultarId();
                   break;
/*___________________________________________________________________________________________________________________________________________________________________*/                     
               case "agregar":
                   
                    if(Usuario.crear == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                        break;
                    }
                   if(numero!=0 && !descripcion.equals(""))
                   {
                       Reja alta=new Reja();
                       alta.setNumero(numero);
                       alta.setDescripcion(descripcion);
                       if(alta.registrarReja(alta))
                       {
                           this.vistaReja.tablaRejas.setModel(this.modeloReja.obtenerCarros(""));
                          this.ocultarId();
                                    modeloReja.limpiarCampos(vistaReja,false,true,false,true );
  
                           JOptionPane.showMessageDialog(principal,"Reja agregado correctamente");
                       }else
                       {
                           JOptionPane.showMessageDialog(principal,"Ocurrion un error al registrar una reja");
                       }
                   }
                   else
                   {
                     JOptionPane.showMessageDialog(principal,"No se permiten espacios vacios o el número de reja es incorrecto");
                   }
                   
                   break;
/*___________________________________________________________________________________________________________________________________________________________________*/                   
               
               default:
                   
                   JOptionPane.showMessageDialog(principal,"Opción no válida, el sistema se cerrará");
                   System.exit(0);
                   break;
                   
           }
           
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = this.vistaReja.tablaRejas.rowAtPoint(e.getPoint());

             if (fila > -1){                
           
            vistaReja.btn_actualizar.setEnabled(true);
        vistaReja.btn_agrear.setEnabled(false);
        vistaReja.btn_eliminar.setEnabled(true);
        vistaReja.btn_limpiar.setEnabled(false);
             //this.idProveedor=String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 0));
             this.idReja=Integer.parseInt(String.valueOf(this.vistaReja.tablaRejas.getValueAt(fila, 0)));
             this.vistaReja.txt_numero.setText(String.valueOf(this.vistaReja.tablaRejas.getValueAt(fila, 1)));
             this.vistaReja.txt_descripcion.setText(String.valueOf(this.vistaReja.tablaRejas.getValueAt(fila, 2)));
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
