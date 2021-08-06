/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Chofer;
import Modelo.Usuario;
import Vista.CRUDChofer;
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
public class ControladorChofer implements ActionListener,MouseListener
{
    //ControladorPrincipal main;
    CRUDChofer vistaChofer;
    Principal principal;
    Chofer modeloChofer;
    int idChofer;
    public ControladorChofer(Principal principal,CRUDChofer vistaChofer)
    {
        this.principal=principal;
        this.vistaChofer=vistaChofer;
        modeloChofer=new Chofer();
      //  this.main=main;
       // abrirChofer();
    }
    
    
    //Abrir ventana CRUD chofer
    public void abrirChofer()
    {
        this.principal.contenedor.add(vistaChofer);
        vistaChofer.setLocation(centradoXY(vistaChofer));
        vistaChofer.setVisible(true);
        vistaChofer.btn_registroChofer.setActionCommand("alta");
        vistaChofer.btn_registroChofer.addActionListener(this);
        vistaChofer.txt_busquedaChofer.setActionCommand("buscar");
        vistaChofer.txt_busquedaChofer.addActionListener(this);
        vistaChofer.btn_actualizarChofer.setActionCommand("actualizar");
        vistaChofer.btn_actualizarChofer.addActionListener(this);
        vistaChofer.btn_limpiarChofer.setActionCommand("limpiar");
        vistaChofer.btn_limpiarChofer.addActionListener(this);
        vistaChofer.btn_eliminarChofer.setActionCommand("eliminar");
        vistaChofer.btn_eliminarChofer.addActionListener(this);
        
        
        /*Enlazar Tabla*/
        vistaChofer.tablaChofer.addMouseListener(this);     
        vistaChofer.tablaChofer.setModel(this.modeloChofer.obtenerConductores(""));
        /*Oculto la Columna ID de la tabla Proveedor*/
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setMinWidth(0);
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setPreferredWidth(0);
         modeloChofer.limpiarCajas(vistaChofer);
         modeloChofer.habilitarBotones(vistaChofer, true,false,false);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         String accion=e.getActionCommand();
   String nombre=vistaChofer.txt_nombreChofer.getText();
   String direccion=vistaChofer.txt_direccionChofer.getText();
   String telefono=vistaChofer.txt_telefonoChofer.getText();
    
    switch(accion)
    {
        case "alta":
            if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
            }
            if(modeloChofer.validarEspacios(nombre,direccion,telefono))
            {
               
                Chofer alta=new Chofer(0,nombre,direccion,telefono,"");
                
                if(modeloChofer.registrarChofer(alta))
                {
                     vistaChofer.tablaChofer.setModel(this.modeloChofer.obtenerConductores(""));
                     this.cargarTabla();
                      JOptionPane.showMessageDialog(principal, "Registro actualizado correctamente");
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(principal, "Error al registrar un automovilista");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(principal, "Existe una casilla vacia");
            }

            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        case "buscar":
        String busqueda=vistaChofer.txt_busquedaChofer.getText();
     
        vistaChofer.tablaChofer.setModel(this.modeloChofer.obtenerConductores(busqueda));
        this.cargarTabla();
        
        break;
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        case "eliminar":
            
            if(Usuario.eliminar == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
             int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el usuario","AVISO",JOptionPane.YES_NO_OPTION);

                Chofer eliminar=new Chofer();
                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarChofer(idChofer))
                             {
                                    vistaChofer.tablaChofer.setModel(this.modeloChofer.obtenerConductores(""));
                                    this.cargarTabla(); 
                                 JOptionPane.showMessageDialog(principal, "Conductor eliminado correctamente");
                                 
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(principal, "Ocurrio un error al eliminar el Conductor, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    principal.remove(dialogButton);
                }
            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        case "actualizar":
            
            if(Usuario.modificar == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
            if(modeloChofer.validarEspacios(nombre,direccion,telefono))
            {
                 /*public Chofer(int id, String nombre, String direccion, String telefono,String fecha) {*/
                Chofer update=new Chofer();
                update.setId(idChofer);
                update.setNombre(nombre);
                update.setDireccion(direccion);
                update.setTelefono(telefono);
                if(update.actualizarChofer(update))
                {
                    vistaChofer.tablaChofer.setModel(this.modeloChofer.obtenerConductores(""));
                    this.cargarTabla();
                }else
                {
                    JOptionPane.showMessageDialog(principal, "Error al actualizar datos");
                }
                
                
            }else
            {
                JOptionPane.showMessageDialog(principal, "Existe una casilla en blanco, Verifique");   
            }
            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        case "limpiar":
            modeloChofer.limpiarCajas(vistaChofer);
            modeloChofer.habilitarBotones(vistaChofer, true,false,false);
            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/            
        default:
             JOptionPane.showMessageDialog(principal, "Opción no válida, el sistema se cerrará");
             System.exit(0);
            break;
    }
        
        
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
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = this.vistaChofer.tablaChofer.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
             modeloChofer.habilitarBotones(vistaChofer, false,true,true);   
             
             //this.idProveedor=String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 0));
             this.idChofer=Integer.parseInt(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 0)));
             this.vistaChofer.txt_nombreChofer.setText(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 1)));
             this.vistaChofer.txt_direccionChofer.setText(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 2)));
             this.vistaChofer.txt_telefonoChofer.setText(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 3)));
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
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    private void cargarTabla()
    {
         
        /*Oculto la Columna ID de la tabla Proveedor*/
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setMinWidth(0);
         vistaChofer.tablaChofer.getColumnModel().getColumn(0).setPreferredWidth(0);
         modeloChofer.limpiarCajas(vistaChofer);
         modeloChofer.habilitarBotones(vistaChofer, true,false,false);
    }
    
}
